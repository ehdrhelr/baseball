package team06.baseball.dto;

import lombok.Getter;

@Getter
public class ApiResult<T> {
    private T data;
    private String error;

    private ApiResult (T data, String error) {
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResult<T> succeed(T data) {
        return new ApiResult<>(data, null);
    }

    public static ApiResult<?> failed(String errorMessage) {
        return new ApiResult<>(null, errorMessage);
    }

    public static ApiResult<?> failed(Throwable throwable) {
        return failed(throwable.getMessage());
    }
}
