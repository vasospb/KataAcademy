import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<String, Integer> romeDigitsMap = new HashMap<>();

    static {
        romeDigitsMap.put("I", 1);
        romeDigitsMap.put("II", 2);
        romeDigitsMap.put("III", 3);
        romeDigitsMap.put("IV", 4);
        romeDigitsMap.put("V", 5);
        romeDigitsMap.put("VI", 6);
        romeDigitsMap.put("VII", 7);
        romeDigitsMap.put("VIII", 8);
        romeDigitsMap.put("IX", 9);
        romeDigitsMap.put("X", 10);
    }
    public static void main(String[] args) throws Exception {

        System.out.println("Input: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(calc(input));

    }

    public static String calc(String input) throws Exception {
        boolean romeDigits = false;
        Task task;

        String[] strings = null;

        if (input.contains("+")) {
            strings = input.split("\\+");
            task = Task.ADD;
        } else if (input.contains("-")) {
            strings = input.split("-");
            task = Task.MINUS;
        } else if (input.contains("*")) {
            strings = input.split("\\*");
            task = Task.MULT;
        } else if (input.contains("/")) {
            strings = input.split("/");
            task = Task.DEVIDE;
        } else {
            task = null;
            throw new Exception("There are no required operation");
        }

        if (strings.length > 2)
            throw new Exception("Should be only two operand");

        String xStr = strings[0].trim();
        String yStr = strings[1].trim();

        int x;
        int y;

        try {
            x = Integer.parseInt(xStr);
            y = Integer.parseInt(yStr);

        } catch (NumberFormatException e) {
            try {
                x = romeDigitsMap.get(xStr);
                y = romeDigitsMap.get(yStr);
            } catch (Exception ex) {
                throw new Exception(ex  + ": Digits should be in the same format");
            }
            romeDigits = true;
        }

        if (x > 10 || x < 0 || y > 10 || y < 0)
            throw new Exception("Wrong number");

        int res = 0;
        switch (task) {
            case DEVIDE:
                res = (x / y);
                break;
            case ADD:
                res = (x + y);
                break;
            case MINUS:
                res = (x - y);
                break;
            case MULT:
                res = (x * y);
                break;
        }
        if (!romeDigits) {
            return String.valueOf(res);
        } else if (res < 1)
            throw new Exception("Should be more then Zero");
        else {
            return convertToRoman(res);
        }
    }
    public static String convertToRoman(int number) {
        String[] romanNumeral = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] decimalValue = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < romanNumeral.length; i++) {
            while (number >= decimalValue[i]) {
                number -= decimalValue[i];
                roman.append(romanNumeral[i]);
            }
        }

        return roman.toString();
    }


}



