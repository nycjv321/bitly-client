package com.nycjv321;

import org.testng.annotations.Test;

import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by fedora on 11/18/15.
 */
public class LinksTest {

    @Test
    public void expand() throws Exception {
        URL actual = BitlyClient.create().links().expand(new URL("http://bit.ly/ze6poY"));
        URL expected = new URL("http://google.com/");
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void lookup() throws Exception {
        URL actual = BitlyClient.create().links().lookup(new URL("http://google.com"));
        URL expected = new URL("http://bit.ly/LmvF");
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void shorten() throws Exception {
        URL actual = BitlyClient.create().links().shorten(new URL("http://google.com"));
        assertTrue(actual.toString().matches("http://bit.ly/[a-zA-Z0-9]+"));
    }

    @Test(expectedExceptions = Links.CouldNotShorten.class)
    public void invalidShorten() throws Exception {
        BitlyClient.create().links().shorten(new URL("http://bit.ly/1LmwMiK"));
    }

    @Test(expectedExceptions = Links.InvalidLookup.class)
    public void invalidLookup() throws Exception {
        BitlyClient.create().links().lookup(new URL("http://google.com/invalid_link_dude"));
    }

    @Test(expectedExceptions = Links.CouldNotExpand.class)
    public void unableToExpand() throws Exception {
        BitlyClient.create().links().expand(new URL("http://bit.ly/invalid_link_dude"));
    }
}