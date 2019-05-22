package com.spring1843.app;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RomanNumbersTest extends TestCase {
    private RomanNumbers romanNumbers = new RomanNumbers();
    private final char char1 = romanNumbers.connectingChar(1);
    private final char char2 = romanNumbers.connectingChar(2);
    private final char char3 = romanNumbers.connectingChar(3);

    public void testIrregularRomanNumbersLessThanFourThousand() {
        testInteger(1L, "I");
        testInteger(2L, "II");
        testInteger(3L, "III");
        testInteger(4L, "IV");
        testInteger(5L, "V");
        testInteger(6L, "VI");
        testInteger(7L, "VII");
        testInteger(8L, "VIII");
        testInteger(9L, "IX");
        testInteger(10L, "X");
        testInteger(11L, "XI");
        testInteger(12L, "XII");
        testInteger(13L, "XIII");
        testInteger(14L, "XIV");
        testInteger(15L, "XV");
        testInteger(16L, "XVI");
        testInteger(17L, "XVII");
        testInteger(18L, "XVIII");
        testInteger(19L, "XIX");
        testInteger(20L, "XX");
        testInteger(30L, "XXX");
        testInteger(40L, "XL");
        testInteger(50L, "L");
        testInteger(60L, "LX");
        testInteger(70L, "LXX");
        testInteger(80L, "LXXX");
        testInteger(90L, "XC");
        testInteger(100L, "C");
        testInteger(200L, "CC");
        testInteger(400L, "CD");
        testInteger(500L, "D");
        testInteger(600L, "DC");
        testInteger(700L, "DCC");
        testInteger(800L, "DCCC");
        testInteger(900L, "CM");
        testInteger(1000L, "M");
        testInteger(2000L, "MM");
        testInteger(3000L, "MMM");
        testInteger(4000L, "MV" + char1);
        testInteger(5000L, "V" + char1);
        testInteger(6000L, "V" + char1 + "M");
        testInteger(7000L, "V" + char1 + "MM");
        testInteger(8000L, "V" + char1 + "MMM");
        testInteger(9000L, "MX" + char1);
        testInteger(10000L, "X" + char1);
        testInteger(20000L, "X" + char1 + "X" + char1);
        testInteger(30000L, "X" + char1 + "X" + char1 + "X" + char1);
        testInteger(40000L, "X" + char1 + "L" + char1);
        testInteger(50000L, "L" + char1);
        testInteger(100000L, "C" + char1);
        testInteger(500000L, "D" + char1);
        testInteger(1000000L, "M" + char1);
        testInteger(5000000L, "V" + char2);
        testInteger(10000000L, "X" + char2);
        testInteger(50000000L, "L" + char2);
        testInteger(100000000L, "C" + char2);
        testInteger(500000000L, "D" + char2);
        testInteger(1000000000L, "M" + char2);
        testInteger(5000000000L, "V" + char3);
        testInteger(6000000000L, "V" + char3 + "M" + char2);
        testInteger(7000000000L, "V" + char3 + "M" + char2 + "M" + char2);
        testInteger(8000000000L, "V" + char3 + "M" + char2 + "M" + char2 + "M" + char2);
        testInteger(9000000000L, "M" + char2 + "X" + char3);
    }

    public void testRegularNumbersLessThanFourThousand() {
        testInteger(0L, "");
        testInteger(25L, "XXV");
        testInteger(79L, "LXXIX");
        testInteger(99L, "XCIX");
        testInteger(101L, "CI");
        testInteger(199L, "CXCIX");
        testInteger(254L, "CCLIV");
        testInteger(111L, "CXI");
        testInteger(963L, "CMLXIII");
        testInteger(999L, "CMXCIX");
        testInteger(1001L, "MI");
        testInteger(1110L, "MCX");
        testInteger(1111L, "MCXI");
        testInteger(1945L, "MCMXLV");
        testInteger(1984L, "MCMLXXXIV");
        testInteger(1999L, "MCMXCIX");
        testInteger(1234L, "MCCXXXIV");
        testInteger(3432L, "MMMCDXXXII");
        testInteger(3900L, "MMMCM");
        testInteger(3999L, "MMMCMXCIX");
        testInteger(9999L, "MX" + char1 + "CMXCIX");
    }

    private void testInteger(long i, String expectedRoman) {
        Assert.assertEquals(expectedRoman, romanNumbers.FromDecimal(i));
    }
}
