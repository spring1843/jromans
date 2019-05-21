package com.spring1843.app;


import java.util.HashMap;
import java.util.Map;

class RomanNumbers {
    private Map<Integer, String> known = new HashMap<Integer, String>();
    private static final String romanLetters = "IVXLCDMṼẋĹĆĎḾ";

    RomanNumbers() {
        known.put(0, "");

        int l = 0;
        for (int i = 1; i<=100000; i*=10){
            put(known,i, romanLetters.substring(l, l+2));
            put(known, i*5, romanLetters.substring(l+1,l+2) + romanLetters.substring(l,l+1) + romanLetters.substring(l+2,l+3));
            l+=2;
        }
    }

    private void put( Map<Integer, String> map, int start, String form){
        String sub1 = form.substring(0,1);

        int increment = tenPowDigits(start);
        if (form.length() == 2) {
            known.put(start, sub1);
            start+=increment;
            known.put(start, repeat(sub1,2));
            start+=increment;
            known.put(start, repeat(sub1,3));
            start+=increment;
            known.put(start, form);
        }else if (form.length() == 3) {
            String sub2 = form.substring(1,2);
            String sub3 = form.substring(2,3);
            known.put(start, sub1);
            start+=increment;
            known.put(start, sub1.concat(repeat(sub2,1)));
            start+=increment;
            known.put(start, sub1.concat(repeat(sub2,2)));
            start+=increment;
            known.put(start, sub1.concat(repeat(sub2,3)));
            start+=increment;
            known.put(start, sub2.concat(sub3));
        }
    }

    private String repeat(String b, int repeat){
        StringBuilder output = new StringBuilder();
        for (int i=0; i<repeat;i++){
            output.append(b);
        }
        return output.toString();
    }

    private int tenPowDigits(int num){
        int i = 1;
        while (num > 0) {
            i*=10;
            num /= 10;
        }
        return i/10;
    }

    public String FromDecimal(int num) {
        StringBuilder output = new StringBuilder();

        String knownNum = known.get(num);
        if (knownNum != null) {
            return knownNum;
        }

        int i = tenPowDigits(num);
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
