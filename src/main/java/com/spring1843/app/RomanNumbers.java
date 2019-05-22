package com.spring1843.app;


import java.util.HashMap;
import java.util.Map;

class RomanNumbers {
    private final long maxNumberInTens = 1000000000L;
    private Map<Long, String> known = new HashMap<Long, String>();

    RomanNumbers() {
        known.put(0L, "");
        populateMapWithKnownDecimalToRomanConversions(romanNumeralSequence());
    }

    public String FromDecimal(long num) {
        StringBuilder output = new StringBuilder();

        String knownNum = known.get(num);
        if (knownNum != null) {
            return knownNum;
        }

        long i = tenPowDigits(num);
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

    private void populateMapWithKnownDecimalToRomanConversions(String romanNumeralSequence) {
        int l = 0;
        for (long i = 1; i <= maxNumberInTens; i *= 10L) {
            put(known, i, romanNumeralSequence.substring(l, l + 4));
            put(known, i * 5L, getFormOfLeighThree(romanNumeralSequence, l));
            l += 4;
        }
    }

    private String getFormOfLeighThree(String romanNumeralSequence, int l) {
        StringBuilder output = new StringBuilder();
        output.append(romanNumeralSequence, l + 2, l + 4);
        output.append(romanNumeralSequence, l, l + 2);
        output.append(romanNumeralSequence, l + 4, l + 6);
        return output.toString();
    }

    /**
     * Using space, and other unicode connecting characters to represent larger numbers not fitting in roman letters
     */
    private String romanNumeralSequence() {
        final String romanLetters = "IVXLCDM";

        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char combiningCharacter = connectingChar(i);
            for (int j = 0; j < romanLetters.length(); j++) {
                if (i > 0 && j == 0) {
                    continue;
                }
                letters.append(romanLetters, j, j + 1);
                letters.append(combiningCharacter);
            }
        }
        return letters.toString();
    }

    public char connectingChar(int index) {
        final String[] combinationPrefixes = {
                "0020", // Space https://unicode-table.com/en/0020/
                "0307", //   Combining Dot Above https://unicode-table.com/en/0307/
                "0308", //   Combining Diaeresis https://unicode-table.com/en/0308/
                "20DB" //    Combining Three Dots Above https://unicode-table.com/en/20DB/
        };
        return (char) Integer.parseInt(combinationPrefixes[index], 16);
    }

    private void put(Map<Long, String> map, long start, String form) {
        String sub1 = form.substring(0, 2).trim();

        long increment = tenPowDigits(start);
        if (form.length() == 4) {
            known.put(start, sub1);
            start += increment;
            known.put(start, repeat(sub1, 2));
            start += increment;
            known.put(start, repeat(sub1, 3));
            start += increment;
            known.put(start, form.replace(" ", ""));
        } else if (form.length() == 6) {
            String sub2 = form.substring(2, 4).trim();
            String sub3 = form.substring(4, 6).trim();

            known.put(start, sub1);
            start += increment;
            known.put(start, sub1.concat(sub2));
            start += increment;
            known.put(start, sub1.concat(repeat(sub2, 2)));
            start += increment;
            known.put(start, sub1.concat(repeat(sub2, 3)));
            start += increment;
            known.put(start, sub2.concat(sub3));
        }
    }

    private String repeat(String b, int repeat) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < repeat; i++) {
            output.append(b);
        }
        return output.toString();
    }

    private long tenPowDigits(long num) {
        long i = 1;
        while (num > 0) {
            i *= 10;
            num /= 10L;
        }
        return i / 10L;
    }

}
