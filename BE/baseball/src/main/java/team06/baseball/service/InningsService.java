package team06.baseball.service;

import org.springframework.stereotype.Service;
import team06.baseball.domain.Defense;
import team06.baseball.domain.Inning;
import team06.baseball.domain.Player;
import team06.baseball.domain.Team;
import team06.baseball.dto.response.game.process.BallChangedResponseDto;
import team06.baseball.dto.response.game.process.BaseChangedResponseDto;
import team06.baseball.dto.response.game.process.NewPitchResponseDto;
import team06.baseball.dto.response.game.process.TotalPitchResultResponseDto;
import team06.baseball.repository.DefenseRepository;
import team06.baseball.repository.InningsRepository;
import team06.baseball.repository.PlayersRepository;
import team06.baseball.repository.TeamsRepository;

@Service
public class InningsService {

    private final InningsRepository inningsRepository;
    private final TeamsRepository teamsRepository;
    private final PlayersRepository playersRepository;
    private final DefenseRepository defenseRepository;

    public InningsService(InningsRepository inningsRepository
            , TeamsRepository teamsRepository
            , PlayersRepository playersRepository
            , DefenseRepository defenseRepository) {
        this.inningsRepository = inningsRepository;
        this.teamsRepository = teamsRepository;
        this.playersRepository = playersRepository;
        this.defenseRepository = defenseRepository;
    }

    /**
     * Defense 만들고 inning 업데이트해서 new pitch dto 보낼 예정
     *
     * @param gameId
     * @return
     */
    public TotalPitchResultResponseDto pitch(Long gameId) {
        Inning inning = inningsRepository.findNewestInningByGameId(gameId);
        Long homeTeamId = 1L;
        Long awayTeamId = 2L;
        if ("TOP".equalsIgnoreCase(inning.getTopBottom())) { // 1회 초면 home팀 수비 , away팀 공격
            homeTeamId = 2L;
            awayTeamId = 1L;
        }
        Team defenseTeam = teamsRepository.findById(homeTeamId)
                .orElseThrow(() -> new IllegalStateException("2번 팀을 찾을 수 없습니다."));
        Team offenseTeam = teamsRepository.findById(awayTeamId)
                .orElseThrow(() -> new IllegalStateException("1번 팀을 찾을 수 없습니다."));
        Player pitcher = playersRepository.findPlayingPitcherByTeamId(defenseTeam.getId());

        /**
         *  일단 한 팀이 한 게임만 한다고 가정
         *  defense는 팀id, 이닝id, 플레이어id를 가짐
         */
//        Defense defense = defenseRepository.findByTeamId(defenseTeam.getId());
        Defense defense = defenseRepository.findByTeamId(2L);
        int pitches = defense.getPitch();
        defense.setPitch(++pitches);
        defenseRepository.save(defense);
        String log = "";

        String pitchResult = pitchResult();

        BallChangedResponseDto ballChangedResponseDto = null;
        BaseChangedResponseDto baseChangedResponseDto = null;


        if (pitchResult.equals("스트라이크")) {
            inning.oneStrike();
            ballChangedResponseDto = BallChangedResponseDto.strike();
            if (inning.isThreeStrike()) {
                inning.oneOut();
                pitchResult = "아웃";
                ballChangedResponseDto = BallChangedResponseDto.out();
            }
            if(inning.isThreeOut()) {
                // todo : 공수 교대
            }
        } else {
            inning.oneBall();
            ballChangedResponseDto = BallChangedResponseDto.ball();
            if (inning.isFourBall()) {
                // 타자 진루
                baseChangedResponseDto = BaseChangedResponseDto.of(inning);
                inning.runOneBase();
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
         * 점수 냈을 때 scoring 데이터 전달
         */

        inningsRepository.save(inning);

        return TotalPitchResultResponseDto.of(newPitchResponseDto, ballChangedResponseDto, baseChangedResponseDto);
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
