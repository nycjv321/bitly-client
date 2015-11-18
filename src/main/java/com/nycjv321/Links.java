package com.nycjv321;

import com.nycjv321.utilities.http.SimpleHttpClient;
import com.nycjv321.utilities.http.SimpleHttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fedora on 11/18/15.
 */
public class Links {

    private final BitlyClient bitlyClient;
    private final SimpleHttpClient httpClient = SimpleHttpClientBuilder.create().build();

    Links(BitlyClient bitlyClient) {
        this.bitlyClient = bitlyClient;
    }

    public URL expand(URL url) {
        try {
            JSONObject json = httpClient.getJSON(String.format("%s/expand?access_token=%s&shortUrl=%s", BitlyClient.getBaseUrl(), bitlyClient.getAccessToken(), url.toString()));
            return new URL(json.getJSONObject("data").getJSONArray("expand").getJSONObject(0).getString("long_url"));
        } catch (JSONException e) {
            throw new CouldNotExpandException(url, e);
        } catch (MalformedURLException e) {
            throw new CouldNotExpandException(url, e);
        }
    }

    class CouldNotExpandException extends RuntimeException {
        public CouldNotExpandException(URL url, Throwable t) {
            super(String.format("Could not expand %s. See %s", url, t));
        }
    }

}
