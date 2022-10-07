package com.inventory.prosta.bot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Response {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public Response(String message, HttpStatus httpStatus, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }
}
