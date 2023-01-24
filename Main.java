import java.util.Scanner;
import java.util.TreeMap;


public class Main {


    public static void main(String[] args) {
        String s1;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите пример с целыми числами от 1 до 10");
        s1 = scanner.nextLine();

        System.out.println(calc(s1));
    }


    public static String calc(String input){

        trans trans = new trans();

        String[] rimChis = {"I","II","III","IV","V","VI","VII","VIII","XI","X"};
        String[] ar = {"0","1","2","3","4","5","6","7","8","9","10"};

        String[] simvols = {"+","-","*","/"};
        String[] regexActions = {"\\+", "-","\\*","/"};

        boolean t=false;
        int res=0;
        String ress=null;
        String lol=null;
        int arrindex=0;

        int countNulls=0, countOnes = 0, countym=0,countdel=0;
        for (char element : input.toCharArray()){
            if (element == '+') countNulls++;
            if (element == '-') countOnes++;
            if (element == '*') countym++;
            if (element == '/') countdel++;
        }

        if (countNulls>1 || countOnes>1 || countym>1|| countdel>1){ //проверка на повторение символов математической операции
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System. exit(0);
        } else if (countNulls>0 && countOnes>0){
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System. exit(0);
        } else if (countNulls>0 && countym>0){
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System. exit(0);
        }else if (countNulls>0 && countdel>0){
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System. exit(0);
        }else if (countOnes>0 && countym>0){
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System. exit(0);
        }else if (countOnes>0 && countdel>0){
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System. exit(0);
        }else if (countym>0 && countdel>0){
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System. exit(0);
        }


        for(int i=0; i<simvols.length; i++) {
            if(input.contains(simvols[i])){
                lol=simvols[i];
                arrindex=i;
            }
        }

        try {
            if (lol == null) { //проверка на математическую операцию
                System.out.println("Строка не является математической операцией");
                System. exit(0);
            }
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Строка не является математической операцией");
            System. exit(0);
        }

        String a1=null;
        String b1=null;
        String a2=null;

        String a =null;
        String b =null;

        String[] d = input.split(regexActions[arrindex]);


        try {

            a = d[0];
            a1 = a.replaceAll(" ", ""); //удвление пробелов
            a2 = a1;
            b = d[1];
            b1 = b.replaceAll(" ", "");

        }catch (Exception ex){
            System.out.println("Строка не является математической операцией"); //в любой непонятной ситуации обработка ошибки
            System. exit(0);
        }

        int al=0;
        int bl=0;

        try {
            al = Integer.parseInt(a1);
            bl = Integer.parseInt(b1);   //перевод в int, или при ошибке перевод из римских

        }
        catch (Exception ex){
            a1 = trans.RomanToArabic(a1);
            b1 = trans.RomanToArabic(b1);

        }

        boolean qw=false, qa=false;
        try{
            for (int i=0; i<ar.length; i++){ //проверка целого числа
                if (ar[i].equals(a1)){
                    qa=true;
                }
            }
            for (int i=0; i<ar.length; i++) {
                if (ar[i].equals(b1)) {
                    qw = true;
                }
            }
            if (!qw && !qa){
                System.out.println("Строка не является математической операцией");  System. exit(0);
            }

            al = Integer.parseInt(a1); //перевод в int для подсчёта
            bl = Integer.parseInt(b1);
        }
        catch (Exception ex){
            System.out.println("Ошибка используются одновременно разные системы счисления");
            System. exit(0);
        }


        if (al>10 || bl>10){
            System.out.println("я так не могу, введите числа от единицы до десяти");
            System. exit(0);
        }
        
        try{ //подсчёт ответа с учётом выбранной операции
            if(lol == "+"){
                res = al+bl;
            }else if(lol == "-"){
                res = al-bl;
            }else if(lol == "*"){
                res = al*bl;
            }else{
                res = al/bl;
            }
        } catch (Exception ex){
            System.out.println(" ");
        }



        String s=null;
        try {
            for (int i = 0; i < rimChis.length; i++) {
                if (rimChis[i].equals(a2)) {
                    ress = trans.IntToRoman(res);
                    t = true;
                    break;
                }
            }
            if (t==true) {  //вывод ответа в римских или арабских
                //System.out.println(ress);
                 s = ress;
            } else {
                 s = Integer.toString(res);
               // System.out.println(res);
            }
        }
        catch (Exception ex){
            System.out.println("Отрицательных чисел в римских нет");
            System. exit(0);
        }

        return s;
    }
}


     class trans {      //класс для перевода из римских в арабские и наоборот
        TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();

        public String RomanToArabic(String a1) {
            String a=null;
            switch (a1){
                case "I": a = "1"; break;
                case "II": a = "2";break;
                case "III": a = "3";break;
                case "IV": a = "4";break;
                case "V": a = "5";break;
                case "VI": a = "6";break;
                case "VII": a = "7";break;
                case "VIII": a = "8";break;
                case "IX": a = "9";break;
                case "X": a = "10";break;
            }
            return a;
        }

        public trans() {
            arabianKeyMap.put(1000, "M");
            arabianKeyMap.put(900, "CM");
            arabianKeyMap.put(500, "D");
            arabianKeyMap.put(400, "CD");
            arabianKeyMap.put(100, "C");
            arabianKeyMap.put(90, "XC");
            arabianKeyMap.put(50, "L");
            arabianKeyMap.put(40, "XL");
            arabianKeyMap.put(10, "X");
            arabianKeyMap.put(9, "IX");
            arabianKeyMap.put(5, "V");
            arabianKeyMap.put(4, "IV");
            arabianKeyMap.put(1, "I");
            arabianKeyMap.put(0, "nulla");

        }
        public String IntToRoman(int num) {
            String roman = "";
            int arabianKey;
            do {
                arabianKey = arabianKeyMap.floorKey(num);
                roman += arabianKeyMap.get(arabianKey);
                num -= arabianKey;
            } while (num != 0);
            return roman;
        }

    }
