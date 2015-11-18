package com.nycjv321;

import org.jetbrains.annotations.NotNull;

/**
 * Hello world!
 */
public class BitlyClient {

    private static String apiAddress = "https://api-ssl.bitly.com";
    private static String versionString = "v3";
    private final String accessToken;

    private BitlyClient(@NotNull String userName, @NotNull String password) {
        accessToken = new TokenGenerator(userName, password).get();
        if (accessToken.contains("RATE_LIMIT_EXCEEDED")) {
            throw new RateLimitExceeded();
        }
    }

    public static BitlyClient create() {
        return create(System.getProperty("BITLY_API_USERNAME"), System.getProperty("BITLY_API_PASSWORD"));
    }

    public static BitlyClient create(String userName, String password) {
        return new BitlyClient(userName, password);
    }

    protected static String getBaseUrl() {
        return String.format("%s/%s", apiAddress, versionString);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Links links() {
        return new Links(this);
    }

    private class RateLimitExceeded extends RuntimeException {
    }
}
