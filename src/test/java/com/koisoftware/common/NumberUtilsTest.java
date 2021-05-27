package com.koisoftware.common;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class NumberUtilsTest {

    @Test
    public void testAtoi_EmptyString() {
        boolean thrown = false;

        try {
            NumberUtils.atoi("");
        } catch (NumberFormatException e) {
            // This is expected.
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testAtoi_MalformedNumberSign() {
        boolean thrown = false;

        try {
            NumberUtils.atoi("*");
        } catch (NumberFormatException e) {
            // This is expected.
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testAtoi_MalformedNumberWithSign() {
        boolean thrown = false;

        try {
            NumberUtils.atoi("+12.3");
        } catch (NumberFormatException e) {
            // This is expected.
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testAtoi_MalformedNumberWithoutSign() {
        boolean thrown = false;

        try {
            NumberUtils.atoi("12.3");
        } catch (NumberFormatException e) {
            // This is expected.
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void testAtoi_NumberWithPositiveSign() {
        Assert.assertEquals(123, NumberUtils.atoi("+123"));
    }

    @Test
    public void testAtoi_NumberWithNegativeSign() {
        Assert.assertEquals(-123, NumberUtils.atoi("-123"));
    }

    @Test
    public void testAtoi_NumberWithoutSign() {
        Assert.assertEquals(123, NumberUtils.atoi("123"));
    }

    @Test
    public void testAtoi_NumberOnlyWithSign() {
        boolean thrown = false;

        try {
            NumberUtils.atoi("+");
        } catch (NumberFormatException e) {
            // This is expected.
            thrown = true;
        }

        assertTrue(thrown);
    }
}
