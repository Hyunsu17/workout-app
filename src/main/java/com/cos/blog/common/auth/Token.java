package com.cos.blog.common.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class Token {
    String token;
    String refreshToken;

    public Token(String token, String refreshToken){
        this.token=token;
        this.refreshToken=refreshToken;
    }
}
