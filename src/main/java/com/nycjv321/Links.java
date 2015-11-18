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
            JSONObject json = httpClient.getJSON(
                    String.format(
                            "%s/expand?access_token=%s&shortUrl=%s",
                            BitlyClient.getBaseUrl(),
                            bitlyClient.getAccessToken(),
                            url.toString()
                    )
            );
            return new URL(json.getJSONObject("data").getJSONArray("expand").getJSONObject(0).getString("long_url"));
        } catch (JSONException e) {
            throw new CouldNotExpand(url, e);
        } catch (MalformedURLException e) {
            throw new CouldNotExpand(url, e);
        }
    }

    public URL lookup(URL url) {
        try {
            JSONObject json = httpClient.getJSON(
                    String.format(
                            "%s/link/lookup?url=%s&access_token=%s",
                            BitlyClient.getBaseUrl(),
                            url.toString(),
                            bitlyClient.getAccessToken()
                    )
            );
            String string = json.getJSONObject("data").getJSONArray("link_lookup").getJSONObject(0).getString("aggregate_link");
            if (string.equals("INVALID_ARG_URL")) {
                throw new InvalidLookup(url);
            }
            return new URL(string);
        } catch (JSONException e) {
            throw new InvalidLookup(url, e);
        } catch (MalformedURLException e) {
            throw new InvalidLookup(url, e);
        }
    }

    public URL shorten(URL url) {
        try {
            JSONObject json = httpClient.getJSON(
                    String.format(
                            "%s/shorten?access_token=%s&longUrl=%s",
                            BitlyClient.getBaseUrl(),
                            bitlyClient.getAccessToken(),
                            url.toString()
                    )
            );
            if (json.getJSONArray("data").length() > 0) {
                return new URL(json.getJSONObject("data").getString("url"));
            } else {
                if (json.getString("status_txt").equals("ALREADY_A_BITLY_LINK")) {
                    throw new AlreadyABitLink(url);
                } else {
                    throw new CouldNotShorten(url);
                }
            }
        } catch (JSONException e) {
            throw new CouldNotShorten(url, e);
        } catch (MalformedURLException e) {
            throw new CouldNotShorten(url, e);
        }
    }

    class CouldNotExpand extends RuntimeException {
        public CouldNotExpand(URL url, Throwable t) {
            super(String.format("Could not expand %s. See %s", url, t));
        }
    }

    class InvalidLookup extends RuntimeException {
        public InvalidLookup(URL url, Throwable t) {
            super(String.format("%s does not have a bit.ly link. See %s", url, t));
        }
        public InvalidLookup(URL url) {
            super(String.format("%s does not have a bit.ly link.", url));
        }

    }

    class AlreadyABitLink extends RuntimeException {
        public AlreadyABitLink(URL url) {
            super(String.format("%s is already a bit.ly.", url));
        }

    }

    class CouldNotShorten extends RuntimeException {
        public CouldNotShorten(URL url) {
            super(String.format("Unable to Shorten %s", url));
        }
        public CouldNotShorten(URL url, Throwable t) {
            super(String.format("Unable to Shorten %s. See: %s", url, t));
        }
    }
}
