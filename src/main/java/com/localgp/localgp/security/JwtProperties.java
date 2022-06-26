package com.localgp.localgp.security;

public class JwtProperties {
    public static final String SECRET = "SimpleSecret";
    public static final int EXPIRATION_TIME = 864_000_00;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
