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

    private final InningsRepository inningsRepository;
    private final TeamsRepository teamsRepository;
    private final PlayersRepository playersRepository;
    private final DefenseRepository defenseRepository;
    private final OffenseRepository offenseRepository;

    public InningsService(InningsRepository inningsRepository
            , TeamsRepository teamsRepository
            , PlayersRepository playersRepository
            , DefenseRepository defenseRepository
            , OffenseRepository offenseRepository) {
        this.inningsRepository = inningsRepository;
        this.teamsRepository = teamsRepository;
        this.playersRepository = playersRepository;
        this.defenseRepository = defenseRepository;
        this.offenseRepository = offenseRepository;
    }

    /**
     * Defense 만들고 inning 업데이트해서 new pitch dto 보낼 예정
     *
     * @param gameId
     * @return
     */
    public TotalPitchResultResponseDto pitch(Long gameId) {
        Inning inning = inningsRepository.findNewestInningByGameId(gameId);
        // 이닝 초 : 홈 - 수비, 어웨이 - 공격 일때
        Long offenseTeamId = 1L;
        Long defenseTeamId = 2L;
        if ("BOTTOM".equalsIgnoreCase(inning.getTopBottom())) { // 이닝 말 : 홈 - 공격 , 어웨이 - 수비
            offenseTeamId = 2L;
            defenseTeamId = 1L;
        }
        Team defenseTeam = teamsRepository.findById(defenseTeamId)
                .orElseThrow(() -> new IllegalStateException("팀을 찾을 수 없습니다."));
        Team offenseTeam = teamsRepository.findById(offenseTeamId)
                .orElseThrow(() -> new IllegalStateException("팀을 찾을 수 없습니다."));
        Player pitcher = playersRepository.findPlayingPitcherByTeamId(defenseTeam.getId());
        /**
         *  일단 한 팀이 한 게임만 한다고 가정
         *  defense는 팀id, 이닝id, 플레이어id를 가짐
         */

        Offense offense = offenseRepository.findByTeamIdAndInningIdAndOnTurn(
                offenseTeamId, inning.getId());
        Defense defense = defenseRepository.findByTeamId(defenseTeamId);
        logger.info("offense : {}", offense);
        Player batter = playersRepository.findById(offense.getPlayerId())
                .orElseThrow(() -> new IllegalStateException("해당 선수를 찾을 수 없습니다."));

        if (inning.isBatterChanged()) {
            // todo : 다음 타자로 어떻게 바꿀지 생각해보자.
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
        BallChangedResponseDto ballChangedResponseDto = null;
        BaseChangedResponseDto baseChangedResponseDto = null;

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
                // todo : 공수 교대
            }
        } else {
            inning.oneBall();
            ballChangedResponseDto = BallChangedResponseDto.ball();
            if (inning.isFourBall()) {
                // 타자 진루
                baseChangedResponseDto = BaseChangedResponseDto.of(inning);
                scoring = inning.runOneBase();
                if (scoring) {
                    scoreResponseDto = ScoreResponseDto.of(0, inning.getScore());
                    baseChangedResponseDto = BaseChangedResponseDto.scoring();
                }
                offense.setAtBat((offense.getAtBat() + 1));
                offenseRepository.save(offense);
            }
        }

        log = inning.getStrike() + "-" + inning.getBall();

        NewPitchResponseDto newPitchResponseDto = NewPitchResponseDto
                .of(defense.getPitch(), pitchResult, log);

        /**
         * todo :
         * out 일때 타자 교체
         * 3 out 일 때 공수 교대
         * hit
         *
         * 투구수 pitcher에 저장
         * 타석 안타 현황 batter에 저장
         *
         */

        inningsRepository.save(inning);

        return TotalPitchResultResponseDto.of(scoreResponseDto
                , PlayerStartResponseDto.of(batter.getName(), "백엔드 천재")
                , PlayerStartResponseDto.of(pitcher.getName(), "iOS 천재")
                , newPitchResponseDto
                , ballChangedResponseDto
                , baseChangedResponseDto);
    }

    private String pitchResult() {
        int rate = (int) (Math.random() * 10 + 1);
        System.out.println("rate : " + rate);
        if (0 < rate && rate <= 5) {
            return "스트라이크";
        }
        return "볼";
    }
}
