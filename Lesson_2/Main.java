package Lesson_2;

        /*1.Есть строка вида: "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0"; (другими словами матрица 4x4)
                    10 3 1 2
                    2 3 2 2
                    5 6 7 1
                    300 3 1 0
                    Написать метод, на вход которого подаётся такая строка, метод должен преобразовать строку в двумерный массив типа String[][];
            - Готово

          1.Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и вернуть результат;
          - Готово
          2.Ваши методы должны бросить исключения в случаях: Если размер матрицы, полученной из строки, не равен 4x4;
            Если в одной из ячеек полученной матрицы не число; (например символ или слово)
          - Готово. Пытался сделать, чтобы при обоих ошибках выбрасывалось 2 исключения,
            но при ошибке с массивом метод прекращает работу и вывести следующую ошибку не выходит, надеюсь это не требовалось.

          3.В методе main необходимо вызвать полученные методы, обработать возможные исключения и вывести результат расчета.
          - Готово

          4.* Написать собственные классы исключений для каждого из случаев
          - Написал класс для исключения по размеру массива
          - Класс для исключения при проверке типа значения не понял как написать. Скорее всего нужна другая реализация , без parseInt , так как в нем уже защит NumberFormatException.
            */

public class Main {

    final static String stringEnter = new String("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0");
    final static int fieldSizeX = 4;
    final static int fieldSizeY = 4;
    final static String [][] arrayString = new String[fieldSizeY][fieldSizeX];

    private  static String[][] arrayMaker (String enter) throws CustomArrayOutOfBoundException {
        String delimiter1 = ("\n");
        String delimiter2 = " ";
        String [] cash1 = enter.split(delimiter1);
        for (int i = 0; i < cash1.length; i++){
            CustomArrayOutOfBoundException.indexCheck(i,arrayString.length);
            String[] cash2 = cash1[i].split(delimiter2);
                for (int b = 0; b < cash2.length; b++) {
                    CustomArrayOutOfBoundException.indexCheck(b,arrayString.length);
                    arrayString[i][b] = cash2[b];
                }
        }
        return arrayString;
    }

    public static int arrayConverter (String[][] enter) throws NumberFormatException {
        int [][] arrayInt = new int[4][4];
        int totalSumm = 0;
            for (int i = 0; i < enter.length; i++) {
                for (int b = 0; b < enter.length; b++) {
                    arrayInt[i][b] = Integer.parseInt(enter[i][b]);
                    totalSumm += arrayInt[i][b];
                }
            }
            return totalSumm / 2;
    }

    public static void main(String[] args) {

        try {
            System.out.println(arrayConverter(arrayMaker(stringEnter)));
        }
        catch (NumberFormatException n){
            throw new CustomRuntimeException(n);

        }
        catch (CustomArrayOutOfBoundException e) {
            e.printStackTrace();
        }
    }


}
class CustomArrayOutOfBoundException extends ArrayIndexOutOfBoundsException{
    CustomArrayOutOfBoundException(int a){
        super(a);
    }
    public static void indexCheck(int a, int b) {
        if (a == b)
            throw new CustomArrayOutOfBoundException(a);

    }
}
class CustomRuntimeException extends RuntimeException {
    CustomRuntimeException(Throwable cause){
        super(cause);
    }

}