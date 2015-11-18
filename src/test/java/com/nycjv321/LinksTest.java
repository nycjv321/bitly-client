package com.nycjv321;

import org.testng.annotations.Test;

import java.net.URL;

import static org.testng.Assert.assertEquals;

/**
 * Created by fedora on 11/18/15.
 */
public class LinksTest {

    @Test
    public void testExpand() throws Exception {
        URL actual = BitlyClient.create().links().expand(new URL("http://bit.ly/ze6poY"));
        URL expected = new URL("http://google.com/");
        assertEquals(actual.toString(), expected.toString());
    }

    @Test(expectedExceptions = Links.CouldNotExpandException.class)
    public void testUnableToExpand() throws Exception {
        URL actual = BitlyClient.create().links().expand(new URL("http://bit.ly/invalid_link_dude"));
        URL expected = new URL("http://google.com/");
        assertEquals(actual.toString(), expected.toString());
    }
}