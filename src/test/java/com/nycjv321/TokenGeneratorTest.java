package com.nycjv321;

import com.google.common.base.Strings;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class TokenGeneratorTest {

    @Test
    public void testGet() throws Exception {
        TokenGenerator tokenGenerator = new TokenGenerator(
                System.getProperty("BITLY_API_USERNAME"),
                System.getProperty("BITLY_API_PASSWORD")
        );
        assertFalse(Strings.isNullOrEmpty(tokenGenerator.get()));
    }
}