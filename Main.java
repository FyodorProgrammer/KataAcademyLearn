import java.util.Scanner;
import static java.lang.System.in;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(in);
        System.out.println("Калькулятор работает только с арабской или римской системой счисления\n" +
                "Калькулятор принимает только целые числа от 1 до 10 включительно\n" +
                "Чтобы снять ограничение диапазона необходимо установить переменную limitedVersion в положение false (в методе calc)\n" +
                "Умеет складывать(+), вычитать(-), умножать(*) и делить(/)\n" +
                "Введенное выражение должно соответствовать одному из форматов a+b, a-b, a*b, a/b\n" +
                "Приятного использования!\n");
        System.out.print("Введите математическое выражение для расчета:");
        String expression = scanner.nextLine(); // Принимаем строку от пользователя для обработки
        System.out.println(calc(expression)); //Отправляем введенное выражение на анализ и расчет в калькулятор
        scanner.close();

    }



    public static String calc(String input) throws Exception {

        CalcTools calcTools = new CalcTools(); //Создаем экземпляр класса для использования инструментов калькулятора
        // Объявляем необходимые переменные
        String result;
        char operator;
        String operand_a;
        String operand_b;

        boolean limitedVersion = true; // возможность отключения ограничений диапазона 1 до 10 (очень уж хотелось попробовать реализовать именно такой вариант)

        operator = calcTools.definitionOperator(input); // Получаем операнд, либо выбрасываем исключение в случае нарушение каких-либо условий
        operand_a = input.split("["+operator+"]")[0]; // Получаем первый операнд
        operand_b = input.split("["+operator+"]")[1]; // Получаем второй операнд

        result = calcTools.arithmetic(operand_a, operand_b, operator, limitedVersion); // Получаем результат математической операции

        return result;
    }

}

class CalcTools{

    char definitionOperator(String expression) throws Exception{
        int count = 0;
        char operator = 63;
        char[] chars = expression.toCharArray();
        for (int i=0; i<chars.length; i++){

            if (chars[i] == 43 || chars[i] == 45 || chars[i] == 42 || chars[i] == 47){// + (43), - (45), * (42), / (47)
                count += 1;
                String op = String.valueOf(chars[i]);
                operator = op.charAt(0);
            }

        }
        if (count == 0){
            throw new Exception("throws Exception //т.к. строка не является математической операцией");
        }
        if (count != 1){
            throw new Exception("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        return operator;

    }

    int arithmeticArabic(int a,int b,char operator) throws Exception {// + (43), - (45), * (42), / (47)
        if (operator == 43){
            return a+b;
        }
        if (operator == 45){
            return a-b;
        }
        if (operator == 42){
            return a*b;
        }
        if (operator == 47){
            return a/b;
        }
        throw new Exception("throws Exception //т.к. на вход подали недопустимый оператор");
    }

    boolean checkRoman(String operand) throws Exception {
        char[] chars = operand.toCharArray();
        int count = 0;
        Roman I = Roman.I;
        Roman V = Roman.V;
        Roman X = Roman.X;
        Roman L = Roman.L;
        Roman C = Roman.C;
        Roman D = Roman.D;
        Roman M = Roman.M;
        for (int i=0; i<chars.length; i++){
            if (chars[i] == I.getUniCode() || chars[i] == V.getUniCode() || chars[i] == X.getUniCode() || chars[i] == L.getUniCode() || chars[i] == C.getUniCode() || chars[i] == D.getUniCode() || chars[i] == M.getUniCode()){
                count +=1;
            }
        }
        if (count == chars.length){
            return true;
        }
        return false;

    }
    boolean checkArabic(String operand){
        try {
            Integer.parseInt(operand);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    int romanToArabic(String operand) {
        operand = operand.toUpperCase();
        char[] chars = operand.toCharArray();
        Roman I = Roman.I;
        Roman V = Roman.V;
        Roman X = Roman.X;
        Roman L = Roman.L;
        Roman C = Roman.C;
        Roman D = Roman.D;
        Roman M = Roman.M;
        int arabicNumber = 0;
        for (int i=0; i<chars.length; i++){
            int arabicCount = 1;
            if (chars[i] == I.getUniCode()) {
                if ((i != chars.length-1) && (chars[i+1] != I.getUniCode())){
                    arabicCount = -arabicCount;
                }
                arabicNumber = arabicNumber + arabicCount*I.getArabicNumber();
            } else if ((chars[i] == V.getUniCode())) {
                if ((i != chars.length-1) && (chars[i+1] != V.getUniCode()) && (chars[i+1] != I.getUniCode())){
                    arabicCount = -arabicCount;
                }
                arabicNumber = arabicNumber + arabicCount*V.getArabicNumber();
            } else if ((chars[i] == X.getUniCode())) {
                if ((i != chars.length-1) && (chars[i+1] != X.getUniCode()) && (chars[i+1] != V.getUniCode()) && (chars[i+1] != I.getUniCode())){
                    arabicCount = -arabicCount;
                }
                arabicNumber = arabicNumber + arabicCount*X.getArabicNumber();
            } else if ((chars[i] == L.getUniCode())) {
                if ((i != chars.length-1) && (chars[i+1] != L.getUniCode()) && (chars[i+1] != X.getUniCode()) && (chars[i+1] != V.getUniCode()) && (chars[i+1] != I.getUniCode())){
                    arabicCount = -arabicCount;
                }
                arabicNumber = arabicNumber + arabicCount*L.getArabicNumber();
            } else if ((chars[i] == C.getUniCode())) {
                if ((i != chars.length-1) && (chars[i+1] != C.getUniCode()) && (chars[i+1] != L.getUniCode()) && (chars[i+1] != X.getUniCode()) && (chars[i+1] != V.getUniCode()) && (chars[i+1] != I.getUniCode())){
                    arabicCount = -arabicCount;
                }
                arabicNumber = arabicNumber + arabicCount*C.getArabicNumber();
            } else if ((chars[i] == D.getUniCode())) {
                if ((i != chars.length-1) && (chars[i+1] != D.getUniCode()) && (chars[i+1] != C.getUniCode()) && (chars[i+1] != L.getUniCode()) && (chars[i+1] != X.getUniCode()) && (chars[i+1] != V.getUniCode()) && (chars[i+1] != I.getUniCode())){
                    arabicCount = -arabicCount;
                }
                arabicNumber = arabicNumber + arabicCount*D.getArabicNumber();
            } else if ((chars[i] == M.getUniCode())) {
                if ((i != chars.length-1) && (chars[i+1] != M.getUniCode()) && (chars[i+1] != D.getUniCode()) && (chars[i+1] != C.getUniCode()) && (chars[i+1] != L.getUniCode()) && (chars[i+1] != X.getUniCode()) && (chars[i+1] != V.getUniCode()) && (chars[i+1] != I.getUniCode())){
                    arabicCount = -arabicCount;
                }
                arabicNumber = arabicNumber + arabicCount*M.getArabicNumber();
            }
        }
        return arabicNumber;
    }
    String arabicToRoman(int arabicNumber){
        String romanNumber = "";
        Roman I = Roman.I;
        Roman V = Roman.V;
        Roman X = Roman.X;
        Roman L = Roman.L;
        Roman C = Roman.C;
        Roman D = Roman.D;
        Roman M = Roman.M;
        int count = 1;

        if (arabicNumber >= M.getArabicNumber()){
            count = arabicNumber%M.getArabicNumber();
            arabicNumber = arabicNumber/M.getArabicNumber();
            for (int i=0; i<arabicNumber; i++){
                romanNumber = romanNumber.concat("M");
            }
            arabicNumber = count;
        }

        if (arabicNumber >= D.getArabicNumber()){
            count = count % D.getArabicNumber();
            if (count / C.getArabicNumber() <= 3){
                if (arabicNumber / D.getArabicNumber() > 3){
                    romanNumber = romanNumber.concat("DM");
                    arabicNumber = arabicNumber - romanToArabic("DM");
                }else{
                    arabicNumber = arabicNumber / D.getArabicNumber();
                    for (int i=0; i<arabicNumber; i++){
                        romanNumber = romanNumber.concat("D");
                    }
                    arabicNumber = count;
                }
            }else{
                romanNumber = romanNumber.concat("CM");
                arabicNumber = arabicNumber - romanToArabic("CM");
            }
        }

        if (arabicNumber >= C.getArabicNumber()){
            count = arabicNumber % C.getArabicNumber();
            if (count / L.getArabicNumber() <= 3){
                if (arabicNumber / C.getArabicNumber() > 3){
                    romanNumber = romanNumber.concat("CD");
                    arabicNumber = arabicNumber - romanToArabic("CD");
                }else{
                    arabicNumber = arabicNumber / C.getArabicNumber();
                    for (int i=0; i<arabicNumber; i++){
                        romanNumber = romanNumber.concat("C");
                    }
                    arabicNumber = count;
                }

            }else{
                romanNumber = romanNumber.concat("LD");
                arabicNumber = arabicNumber - romanToArabic("LD");
            }
        }

        if (arabicNumber >= L.getArabicNumber()){
            count = arabicNumber % L.getArabicNumber();
            if (count / X.getArabicNumber() <= 3){
                if (arabicNumber / L.getArabicNumber() > 3){
                    romanNumber = romanNumber.concat("LC");
                    arabicNumber = arabicNumber - romanToArabic("LC");
                }else{
                    arabicNumber = arabicNumber / L.getArabicNumber();
                    for (int i=0; i<arabicNumber; i++){
                        romanNumber = romanNumber.concat("L");
                    }
                    arabicNumber = count;
                }
            }else{
                romanNumber = romanNumber.concat("XC");
                arabicNumber = arabicNumber - romanToArabic("XC");
            }

        }

        if (arabicNumber >= X.getArabicNumber()){
            count = arabicNumber % X.getArabicNumber();
            if (count / V.getArabicNumber() <= 3){
                if (arabicNumber / X.getArabicNumber() > 3){
                    romanNumber = romanNumber.concat("XL");
                    arabicNumber = arabicNumber - romanToArabic("XL");
                }else{
                    arabicNumber = arabicNumber / X.getArabicNumber();
                    for (int i=0; i<arabicNumber; i++){
                        romanNumber = romanNumber.concat("X");
                    }
                    arabicNumber = count;
                }
            }else{
                romanNumber = romanNumber.concat("VL");
                arabicNumber = arabicNumber - romanToArabic("VL");
            }
        }

        if (arabicNumber >= V.getArabicNumber()){
            count = arabicNumber % V.getArabicNumber();
            if (count / I.getArabicNumber() <= 3){
                if (arabicNumber / V.getArabicNumber() > 3){
                    romanNumber = romanNumber.concat("VX");
                    arabicNumber = arabicNumber - romanToArabic("VX");
                }else{
                    arabicNumber = arabicNumber / V.getArabicNumber();
                    for (int i=0; i<arabicNumber; i++){
                        romanNumber = romanNumber.concat("V");
                    }
                    arabicNumber = count;
                }
            }else{
                romanNumber = romanNumber.concat("IX");
                arabicNumber = arabicNumber - romanToArabic("IX");
            }
        }

        if (arabicNumber >= I.getArabicNumber()){
            count = arabicNumber % I.getArabicNumber();
            if (count / I.getArabicNumber() <= 3){
                if (arabicNumber / I.getArabicNumber() > 3){
                    romanNumber = romanNumber.concat("IV");
                }else{
                    arabicNumber = arabicNumber / I.getArabicNumber();
                    for (int i=0; i<arabicNumber; i++){
                        romanNumber = romanNumber.concat("I");
                    }
                }
            }else{
                romanNumber = romanNumber.concat("IV");
            }

        }
        return romanNumber;
    }
    String arithmetic(String operand_a, String operand_b, char operator, boolean limitedVersion) throws Exception {
        int a;
        int b;
        String result;
        if (checkArabic(operand_a) && checkArabic(operand_b)){
            a = Integer.parseInt(operand_a);
            b = Integer.parseInt(operand_b);
            if ((a < 1||a > 10||b < 1||b > 10) && limitedVersion){
                throw new Exception("throws Exception //т.к. один из операндов или оба не соответствует заданному диапазону от 1 до 10 включительно");
            }
            result = String.valueOf(arithmeticArabic(a, b, operator));
        }else{
            if ((checkArabic(operand_a) && checkRoman(operand_b)) || (checkRoman(operand_a) && checkArabic(operand_b))){
                throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");
            }
            if (checkRoman(operand_a) && checkRoman(operand_b)){
                a = romanToArabic(operand_a);
                b = romanToArabic(operand_b);
                if (a <= b && operator == 45){
                    throw new Exception("throws Exception //т.к. в римской системе нет отрицательных чисел и ноля");
                }
                if ((a < 1||a > 10||b < 1||b > 10) && limitedVersion){
                    throw new Exception("throws Exception //т.к. один из операндов не соответствует заданному диапазону от 1 до 10 включительно ");
                }
                int arabicResult = arithmeticArabic(a, b, operator);
                result = arabicToRoman(arabicResult);
            }else{
                throw new Exception("throws Exception //Система счисления одного из операндов не определена. Калькулятор работает только с целыми числами, с Римской или Арабской системой счисления");
            }

        }
        return result;
    }
}