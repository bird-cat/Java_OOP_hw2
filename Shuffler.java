package ntu.r09922114.util;

import java.util.*;

public class Shuffler {
    public static void shuffleArray(Object [] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i >= 0; i--) {
            int j = rnd.nextInt(i + 1);
            Object tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
