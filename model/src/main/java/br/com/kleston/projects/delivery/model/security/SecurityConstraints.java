package br.com.kleston.projects.delivery.model.security;

public class SecurityConstraints {
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String SECRET = "nAnDapArbAT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
