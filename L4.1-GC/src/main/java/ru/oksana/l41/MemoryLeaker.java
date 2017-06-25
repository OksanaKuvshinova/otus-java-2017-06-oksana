package ru.oksana.l41;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeaker {

    private int addSize = 5_000_000;
    private double clearenceIndex = 0.95;

    private List<String> pool = new ArrayList<>();

    public void doWork() throws InterruptedException {
        fillPool();
        System.out.println("After fill Pool Size = " + pool.size());
        Thread.sleep(75000);
        clearPoolWithLeak();
        System.out.println("After clear Pool Size = " + pool.size());
    }

    private void fillPool() {
        for (int i = 0; i < addSize; i++) {
            pool.add(new String(new char[0]));
        }
    }

    private void clearPoolWithLeak() {
        int initialSize = pool.size();
        for (int i = initialSize - 1; i > (initialSize - (addSize * clearenceIndex)); i--) {
            pool.remove(i);
        }
    }
}
