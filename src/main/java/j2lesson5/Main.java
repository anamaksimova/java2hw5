package j2lesson5;

import java.util.Arrays;

public class Main {
    static final int size = 1000000;
    static final int h = size / 2;


    public static void main(String[] args) {
        mas();
        mas2();


    }

    public static void mas() {

        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);

    }

    public static void mas2() {
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];


        for (int i = 0; i < h; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < h; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }

        });
        t1.start();
        t2.start();
        try {
            t1.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.currentTimeMillis();

        System.out.println(System.currentTimeMillis() - a);
    }
}
