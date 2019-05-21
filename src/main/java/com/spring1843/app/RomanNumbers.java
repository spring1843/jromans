package com.spring1843.app;

import java.util.HashMap;
import java.util.Map;

class RomanNumbers {
    private Map<Integer, String> known = new HashMap<Integer, String>();

    RomanNumbers() {
        known.put(0, "");
        known.put(1, "I");
        known.put(2, "II");
        known.put(3, "III");
        known.put(4, "IV");
        known.put(5, "V");
        known.put(6, "VI");
        known.put(7, "VII");
        known.put(8, "VIII");
        known.put(9, "IX");
        known.put(10, "X");
        known.put(20, "XX");
        known.put(30, "XXX");
        known.put(40, "XL");
        known.put(50, "L");
        known.put(60, "LX");
        known.put(70, "LXX");
        known.put(80, "LXXX");
        known.put(90, "XC");
        known.put(100, "C");
        known.put(200, "CC");
        known.put(300, "CCC");
        known.put(400, "CD");
        known.put(500, "D");
        known.put(600, "DC");
        known.put(700, "DCC");
        known.put(800, "DCCC");
        known.put(900, "CM");
        known.put(1000, "M");
        known.put(2000, "MM");
        known.put(3000, "MMM");
        known.put(3999, "MMMCMXCIX");
    }

    public String FromDecimal(int num) {
        StringBuilder output = new StringBuilder();

        String knownNum = known.get(num);
        if (knownNum != null) {
            return knownNum;
        }

        int i = 1000;
        while (i > 0) {
            if (num / i > 0) {
                output.append(known.get((num / i) * i));
                num %= i;
            }
            i /= 10;
        }

        output.append(known.get(num));

        return output.toString();
    }
}
