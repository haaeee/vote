package com.example.vote.global.dto;

import java.util.Objects;
import lombok.Builder;
import lombok.Data;

@Data
public class Result<T> {

    private Code code;
    private String message;
    private T data;

    @Builder
    private Result(Code code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result createErrorResult(String message) {
        return Result.builder()
                .code(Code.ERROR)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> Result createSuccessResult(T data) {
        return Result.builder()
                .code(Code.SUCCESS)
                .message("")
                .data(data)
                .build();
    }

    public String toStream() {
        if (Objects.isNull(data)) {
            return "{" +
                    "\"code\":" + "\"" + code + "\"," +
                    "\"data\":" + null + "}";
        }

        return "{" +
                "\"code\":" + "\"" + code + "\"," +
                "\"data\":" + data + "}";
    }
}