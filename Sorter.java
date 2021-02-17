package ntu.r09922114.util;

import ntu.r09922114.gambling.Card;

public class Sorter {
    public static void bubbleSort(Card [] items) {
        for (int i = items.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (items[j].compare(items[j + 1]) > 0) {
                    Card tmp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = tmp;
                }
            }
        }
    }
}
