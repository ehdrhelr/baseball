package team06.baseball.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerStartResponseDto {

    private String name;
    private String info;

    public static PlayerStartResponseDto of(String name, String info) {
        return PlayerStartResponseDto.builder()
                .name(name)
                .info(info)
                .build();
    }
}
