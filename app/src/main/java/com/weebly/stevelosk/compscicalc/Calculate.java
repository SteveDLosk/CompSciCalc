package com.weebly.stevelosk.compscicalc;
import android.util.Log;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.weebly.stevelosk.compscicalc.R.string.equals;

/**
 * Created by steve on 5/6/2017.
 */

public class Calculate {


    public static String[] processString (String s) {
        // at this point, only accepts one operator.  Will expand later.

        // TODO:
        // pre-process string, remove trailing and leading operators
        char[] str = s.toCharArray();

        StringBuilder digits = new StringBuilder();
        String operator = null;
        String operand1 = "";
        String operand2;

        for (int i = 0; i < s.length(); i++) {
            char next = str[i];
            // if it is a digit
            if (next >= 48 && next <= 57) {
                digits.append(next);
            }
            else {
                operator = Character.toString(next);
                operand1 = digits.toString();
                // reset for the next operand
                digits.setLength(0);
            }
        }
        // make second operand
        operand2 = digits.toString();

        String[] result = new String[3];
        result[0] = operand1;
        result[1] = operator.toString();
        result[2] = operand2;
        String debug = result[0] + " " + result[1] + " " + result[2];

        Log.d("processString", debug);
        return result;
    }

    public static long calculateString (String[] inputs) {
        long op1 = Long.parseLong(inputs[0]);
        long op2 = Long.parseLong(inputs[2]);

        if (inputs[1].equals("+"))
            return op1 + op2;
        else if (inputs[1].equals("-"))
            return op1 - op2;
        else if (inputs[1].equals("*"))
            return op1 * op2;
        else if (inputs[1].equals("/"))
            return op1 / op2;
        else if (inputs[1].equals("^"))
            return exponent(op1, op2);

        // should be unreachable
        return Long.MIN_VALUE;
    }

    public static long factorial(long n) {

        // TODO:
        // handle excessively long-running processes and overflow
        // by handling too large inputs.  Maybe return -1

        long result = 1;
        while (n > 1) {
            result *= n;
            n--;
        }

        return result;
    }

    public static long square (long n) {
        return n * n;
    }
    public static double square (double n) {
        return n * n;
    }

    public static long exponent (long n, long x) {

        long result = n;
        while (x > 1) {
            result *= n;
            x--;
        }
        return result;
    }

    public static double exponent (double n, double x) {

        double result = n;
        while (x > 1) {
            result *= n;
            x--;
        }
        return result;
    }

}
