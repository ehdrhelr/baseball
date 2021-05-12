package team06.baseball.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import team06.baseball.domain.*;
import team06.baseball.dto.response.game.process.*;
import team06.baseball.dto.response.game.start.PlayerStartResponseDto;
import team06.baseball.repository.*;

@Service
public class InningsService {

    private Logger logger = LoggerFactory.getLogger(InningsService.class);

    private final GamesService gamesService;

    private final GamesRepository gamesRepository;
    private final InningsRepository inningsRepository;
    private final TeamsRepository teamsRepository;
    private final PlayersRepository playersRepository;
    private final DefenseRepository defenseRepository;
    private final OffenseRepository offenseRepository;

    public InningsService(
            GamesService gamesService
            , GamesRepository gamesRepository
            , InningsRepository inningsRepository
            , TeamsRepository teamsRepository
            , PlayersRepository playersRepository
            , DefenseRepository defenseRepository
            , OffenseRepository offenseRepository) {
        this.gamesService = gamesService;
        this.gamesRepository = gamesRepository;
        this.inningsRepository = inningsRepository;
        this.teamsRepository = teamsRepository;
        this.playersRepository = playersRepository;
        this.defenseRepository = defenseRepository;
        this.offenseRepository = offenseRepository;
    }

    public TotalPitchResultResponseDto pitch(Long gameId) {
        Inning inning = inningsRepository.findNewestInningByGameId(gameId);
        Game game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new IllegalStateException(gameId + "게임을 찾을 수 없습니다."));
        Long homeTeamId = game.getHomeTeamId();
        Long awayTeamId = game.getAwayTeamId();
        Long offenseTeamId = awayTeamId;
        Long defenseTeamId = homeTeamId;
        if ("BOTTOM".equalsIgnoreCase(inning.getTopBottom())) { // 이닝 말 : 홈 - 공격 , 어웨이 - 수비
            offenseTeamId = homeTeamId;
            defenseTeamId = awayTeamId;
        }
        Team defenseTeam = teamsRepository.findById(defenseTeamId)
                .orElseThrow(() -> new IllegalStateException("팀을 찾을 수 없습니다."));
        Team offenseTeam = teamsRepository.findById(offenseTeamId)
                .orElseThrow(() -> new IllegalStateException("팀을 찾을 수 없습니다."));
        Player pitcher = playersRepository.findPlayingPitcherByTeamId(defenseTeam.getId());

        Offense offense = offenseRepository.findByTeamIdAndInningIdAndOnTurn(
                offenseTeamId, inning.getId());
        Defense defense = defenseRepository.findNewestDefenseByTeamId(defenseTeamId);
        logger.info("offense : {}", offense);
        Player batter = playersRepository.findById(offense.getPlayerId())
                .orElseThrow(() -> new IllegalStateException("해당 선수를 찾을 수 없습니다."));

        if (inning.isBatterChanged()) {
            int order = offense.getOrder();
            int nextOrder = order + 1;
            if (nextOrder == 10) {
                nextOrder = 1;
            }
            Offense nextOffense = offenseRepository.findByTeamIdAndInningIdAndOrder(
                    offenseTeamId, inning.getId(), nextOrder);
            offense.turnOff();
            nextOffense.turnOn();
            offenseRepository.save(offense);
            offenseRepository.save(nextOffense);

            batter = playersRepository.findById(nextOffense.getPlayerId())
                    .orElseThrow(() -> new IllegalStateException("해당 선수를 찾을 수 없습니다."));
        }

        int pitches = defense.getPitch();
        defense.setPitch(++pitches);
        defenseRepository.save(defense);
        String log = "";

        String pitchResult = pitchResult();

        ScoreResponseDto scoreResponseDto = null;
        NewPitchResponseDto newPitchResponseDto = null;
        BallChangedResponseDto ballChangedResponseDto = null;
        BaseChangedResponseDto baseChangedResponseDto = null;
        InningChangedResponseDto inningChangedResponseDto = null;

        boolean scoring = false;

        if (pitchResult.equals("스트라이크")) {
            inning.oneStrike();
            ballChangedResponseDto = BallChangedResponseDto.strike();
            if (inning.isThreeStrike()) {
                inning.oneOut();
                pitchResult = "아웃";
                ballChangedResponseDto = BallChangedResponseDto.out();
                offense.setAtBat((offense.getAtBat() + 1));
                offenseRepository.save(offense);
            }
            if (inning.isThreeOut()) {
                int counter = inning.getRound();
                String topBottom = inning.getTopBottom();
                if (topBottom.equalsIgnoreCase("TOP")) {
                    topBottom = "BOTTOM";

                } else {
                    topBottom = "TOP";
                    counter++;
                }
                inningsRepository.save(new Inning(gameId, counter, topBottom));
                Inning newInning = inningsRepository.findNewestInningByGameId(gameId);
                inningChangedResponseDto = InningChangedResponseDto.of(counter, topBottom);

                gamesService.saveOffense(defenseTeamId, newInning.getId());
                Offense startOffense = offenseRepository.findByTeamIdAndInningIdAndOnTurn(defenseTeamId, newInning.getId());
                Player newBatter = playersRepository.findById(startOffense.getPlayerId())
                        .orElseThrow(() -> new IllegalStateException());
                Player newPitcher = playersRepository.findPlayingPitcherByTeamId(offenseTeamId);

                Integer pitch = defenseRepository.findBeforePitchByPlayerId(newPitcher.getId())
                        .orElse(0);

                defenseRepository.save(Defense.of(offenseTeamId, newInning.getId(), newPitcher.getId(), pitch));

                int order = offense.getOrder();
                int nextOrder = order + 1;
                if (nextOrder == 10) {
                    nextOrder = 1;
                }

                Offense nextOffense = offenseRepository.findByTeamIdAndInningIdAndOrder(
                        offenseTeamId, inning.getId(), nextOrder);
                offense.turnOff();
                nextOffense.turnOn();
                offenseRepository.save(offense);
                offenseRepository.save(nextOffense);

                newPitchResponseDto = NewPitchResponseDto.of(defense.getPitch(), pitchResult, log);

                return TotalPitchResultResponseDto.of(
                        inningChangedResponseDto
                        , scoreResponseDto
                        , PlayerStartResponseDto.of(newBatter.getName(), "백엔드 천재")
                        , PlayerStartResponseDto.of(newPitcher.getName(), "iOS 천재")
                        , newPitchResponseDto
                        , ballChangedResponseDto
                        , baseChangedResponseDto);
            }
        } else {
            inning.oneBall();
            ballChangedResponseDto = BallChangedResponseDto.ball();
            if (inning.isFourBall()) {
                // 타자 진루
                baseChangedResponseDto = BaseChangedResponseDto.of(inning);
                scoring = inning.runOneBase();

                int homeTotalScore = getHomeTotalScore(gameId);
                int awayTotalScore = getAwayTotalScore(gameId);

                if (scoring) {
                    if ("TOP".equalsIgnoreCase(inning.getTopBottom())) { // 어웨이팀 득점
                        awayTotalScore++;
                    } else {
                        homeTotalScore++;
                    }
                    scoreResponseDto = ScoreResponseDto.of(homeTotalScore, awayTotalScore);
                    baseChangedResponseDto = BaseChangedResponseDto.scoring();
                }
                offense.setAtBat((offense.getAtBat() + 1));

                int order = offense.getOrder();
                int nextOrder = order + 1;
                if (nextOrder == 10) {
                    nextOrder = 1;
                }

                Offense nextOffense = offenseRepository.findByTeamIdAndInningIdAndOrder(
                        offenseTeamId, inning.getId(), nextOrder);
                offense.turnOff();
                nextOffense.turnOn();
                offenseRepository.save(offense);
                offenseRepository.save(nextOffense);
                offenseRepository.save(offense);
            }
        }

        log = inning.getStrike() + "-" + inning.getBall();

        newPitchResponseDto = NewPitchResponseDto
                .of(defense.getPitch(), pitchResult, log);

        /**
         * todo:
         * hit
         *
         * 타석 안타 현황 batter에 저장
         *
         */

        inningsRepository.save(inning);

        return TotalPitchResultResponseDto.of(
                inningChangedResponseDto
                , scoreResponseDto
                , PlayerStartResponseDto.of(batter.getName(), "백엔드 천재")
                , PlayerStartResponseDto.of(pitcher.getName(), "iOS 천재")
                , newPitchResponseDto
                , ballChangedResponseDto
                , baseChangedResponseDto);
    }

    private String pitchResult() {
        int rate = (int) (Math.random() * 10 + 1);
        System.out.println("rate : " + rate);
        if (0 < rate && rate <= 3) {
            return "스트라이크";
        }
        return "볼";
    }

    private int getAwayTotalScore(Long gameId) {
        return inningsRepository.findAwayTeamTotalScoreByGameId(gameId)
                .orElse(0);
    }

    private int getHomeTotalScore(Long gameId) {
        return inningsRepository.findHomeTeamTotalScoreByGameId(gameId)
                .orElse(0);
    }
}
