// Ожидается, что во круг операторов будет пробел
// В примерах было так - значит будет так)

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {

    public static String[] romanianArray = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
    public static List<String> romanianList = Arrays.asList(romanianArray);

    public static void main(String[] args) {
        int operandOne = 0;
        int operandTwo = 0;
        boolean romanianMode = false;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter expression: ");

        String[] expression = input.nextLine().trim().split(" ");

        if (expression.length != 3) {
            throw new RuntimeException("only allowed 'a (+*/-) b' format");
        }

        String regex = "[0-9]+[.]?[0-9]*";
        if (romanianList.contains(expression[0]) && romanianList.contains(expression[2])){
            romanianMode = true;
        } else if (Pattern.matches(regex, expression[0]) && Pattern.matches(regex, expression[2])) {
            operandOne = Integer.parseInt(expression[0]);
            operandTwo = Integer.parseInt(expression[2]);

            if ((operandOne > 10) || operandTwo > 10) {
                throw new RuntimeException("invalid input");
            }
            if ((operandOne == 0) || operandTwo == 0){
                throw new RuntimeException("invalid input");
            }
        } else {
            throw new RuntimeException("invalid input");
        }

        if (romanianMode){
            operandOne = romanianList.indexOf(expression[0]) + 1;
            operandTwo = romanianList.indexOf(expression[2]) + 1;
        }

        String operator = expression[1];
        switch (operator) {
            case "+":
                if (romanianMode) {
                    System.out.println(converter(operandOne + operandTwo));
                } else {
                    System.out.println(operandOne + operandTwo);
                }
                break;
            case "-":
                if (romanianMode) {
                    System.out.println(converter(operandOne - operandTwo));
                } else {
                    System.out.println(operandOne - operandTwo);
                }
                break;
            case "*":
                if (romanianMode) {
                    System.out.println(converter(operandOne * operandTwo));
                } else {
                    System.out.println(operandOne * operandTwo);
                }
                break;
            case "/":
                if (romanianMode) {
                    System.out.println(converter((operandOne / operandTwo)));
                } else {
                    System.out.println((operandOne / operandTwo));
                }
                break;
            default:
                throw new RuntimeException("invalid input");
        }
    }

    public static String converter(int num) {
        String romeNum = "";
        if (num <= 0){
            throw new RuntimeException("< 0 doesn't exist in Romanian numbers");
        }

        // Т.к. ввод ограничен до 10, то при имеющихся операциях максимально возможное число = 100

        if (num == 100) {
            romeNum += "M";
            num -= 100;
            return romeNum;
        }
        if (num >= 50) {
            romeNum += "L";
            num -= 50;
        }
        if (num > 10){
            int dec = (num / 10);
            num -= dec * 10;
            for (int i=0; i<dec ; i++){
                romeNum += "X";
            }
        }
        if (num > 0){
            romeNum += romanianArray[num-1];
        }

        return romeNum;
    }
}

