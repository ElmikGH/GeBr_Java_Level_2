package Lesson_5;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr;
    static float[] a1 = new float[h];
    static float[] a2 = new float[h];
    static long timeChecker;

    public static void method1() {
        arrayMaker();
        timeChecker = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - timeChecker);
    }

    public static void method2() {
        arrayMaker();
        timeChecker = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        threadCalculate(a1);
        threadCalculate(a2);
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - timeChecker);
    }

    public static float[] arrayMaker() {
        arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        return arr;
    }

    public static float[] arrayCalculate(float[] entryArray) {
        for (int i = 0; i < h; i++) {
            entryArray[i] = (float) (entryArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return entryArray;
    }
    public static void threadCalculate(float [] array){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                arrayCalculate(array);
            }
        });
        thread.interrupt();
    }
        public static void main (String[]args){
            method1();
            method2();
        }

}
