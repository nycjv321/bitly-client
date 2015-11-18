package com.nycjv321;

import com.nycjv321.utilities.http.SimpleHttpClient;
import com.nycjv321.utilities.http.SimpleHttpClientBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.message.BasicHeader;

/**
 * Created by fedora on 11/18/15.
 */
public class TokenGenerator {
    private final String authorizationToken;
    private SimpleHttpClient simpleHttpClient;

    public TokenGenerator(String userName, String password) {
        simpleHttpClient = SimpleHttpClientBuilder.create().build();
        authorizationToken = new String(Base64.encodeBase64(String.format("%s:%s", userName, password).getBytes()));
    }

    public String get() {
        return SimpleHttpClient.consume(
                simpleHttpClient.post(
                        "https://api-ssl.bitly.com/oauth/access_token",
                        new BasicHeader("Authorization", "Basic " + authorizationToken)
                )
        );
    }


}
