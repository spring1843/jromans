package com.spring1843.app;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RomanNumbersTest extends TestCase {
    RomanNumbers romanNumbers = new RomanNumbers();

    public void testIrregularRomanNumbersLessThanFourThousand() {
        testInteger(0, "");
        testInteger(1, "I");
        testInteger(2, "II");
        testInteger(3, "III");
        testInteger(4, "IV");
        testInteger(5, "V");
        testInteger(6, "VI");
        testInteger(7, "VII");
        testInteger(8, "VIII");
        testInteger(9, "IX");
        testInteger(10, "X");
        testInteger(11, "XI");
        testInteger(12, "XII");
        testInteger(13, "XIII");
        testInteger(14, "XIV");
        testInteger(15, "XV");
        testInteger(16, "XVI");
        testInteger(17, "XVII");
        testInteger(18, "XVIII");
        testInteger(19, "XIX");
        testInteger(20, "XX");
        testInteger(30, "XXX");
        testInteger(40, "XL");
        testInteger(50, "L");
        testInteger(60, "LX");
        testInteger(70, "LXX");
        testInteger(80, "LXXX");
        testInteger(90, "XC");
        testInteger(100, "C");
        testInteger(200, "CC");
        testInteger(400, "CD");
        testInteger(500, "D");
        testInteger(600, "DC");
        testInteger(700, "DCC");
        testInteger(800, "DCCC");
        testInteger(900, "CM");
        testInteger(1000, "M");
        testInteger(2000, "MM");
        testInteger(3000, "MMM");
        testInteger(3999, "MMMCMXCIX");
    }


    public void testRegularNumbersLessThanFourThousand() {
        testInteger(25, "XXV");
        testInteger(79, "LXXIX");
        testInteger(99, "XCIX");
        testInteger(101, "CI");
        testInteger(199, "CXCIX");
        testInteger(254, "CCLIV");
        testInteger(111, "CXI");
        testInteger(963, "CMLXIII");
        testInteger(999, "CMXCIX");
        testInteger(1001, "MI");
        testInteger(1110, "MCX");
        testInteger(1111, "MCXI");
        testInteger(1945, "MCMXLV");
        testInteger(1984, "MCMLXXXIV");
        testInteger(1999, "MCMXCIX");
        testInteger(1234, "MCCXXXIV");
        testInteger(3432, "MMMCDXXXII");
        testInteger(3900, "MMMCM");
    }

    private void testInteger(int i, String expectedRoman) {
        Assert.assertEquals(expectedRoman, romanNumbers.FromDecimal(i));
    }
}
