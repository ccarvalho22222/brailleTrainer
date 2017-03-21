/*
Cory Carvalho
Human Computer Interaction HCI Project
Fall 2016
*/

package braille;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    private Map<Integer, String> gradeOneMap;

    public Interpreter() {
        gradeOneMap = new HashMap<>();
        gradeOneMap.put(1, "a");
        gradeOneMap.put(3, "b");
        gradeOneMap.put(9, "c");
        gradeOneMap.put(25, "d");
        gradeOneMap.put(17, "e");
        gradeOneMap.put(11, "f");
        gradeOneMap.put(27, "g");
        gradeOneMap.put(19, "h");
        gradeOneMap.put(10, "i");
        gradeOneMap.put(26, "j");
        gradeOneMap.put(5, "k");
        gradeOneMap.put(7, "l");
        gradeOneMap.put(13, "m");
        gradeOneMap.put(29, "n");
        gradeOneMap.put(21, "o");
        gradeOneMap.put(15, "p");
        gradeOneMap.put(31, "q");
        gradeOneMap.put(23, "r");
        gradeOneMap.put(14, "s");
        gradeOneMap.put(30, "t");
        gradeOneMap.put(37, "u");
        gradeOneMap.put(39, "v");
        gradeOneMap.put(58, "w");
        gradeOneMap.put(45, "x");
        gradeOneMap.put(61, "y");
        gradeOneMap.put(53, "z");

        //Numbers 0-9, accessible when # is entered.
        gradeOneMap.put(60, "#");
        gradeOneMap.put(65, "1");
        gradeOneMap.put(67, "2");
        gradeOneMap.put(73, "3");
        gradeOneMap.put(89, "4");
        gradeOneMap.put(81, "5");
        gradeOneMap.put(75, "6");
        gradeOneMap.put(91, "7");
        gradeOneMap.put(83, "8");
        gradeOneMap.put(74, "9");
        gradeOneMap.put(90, "0");

        //Basic punctuation.
        gradeOneMap.put(50, ".");
        gradeOneMap.put(22, "!");
        gradeOneMap.put(38, "?");
        gradeOneMap.put(2, ",");
        gradeOneMap.put(6, ";");
        gradeOneMap.put(18, ":");
    }

    public String returnCharacter(int[] inputs, int number) {
        //Sum of braille inputs.
        int total = 0;
        total += number;

        //The character that will be returned.
        String returnedCharacter = null;

        //The alphabet, accessed by the sum of braille inputs in HashMap.
        for (int i = 0; i < 6; i++) {
            total += inputs[i];
            inputs[i] = 0;
        }

        System.out.println(total + ": " + gradeOneMap.get(total));
        if (gradeOneMap.get(total) != null) {
            returnedCharacter = gradeOneMap.get(total);
        }

        //If the map gets a null we have nothing happen.
        if (returnedCharacter == null) {
            returnedCharacter = "";
        }

        return returnedCharacter;
    }
}
