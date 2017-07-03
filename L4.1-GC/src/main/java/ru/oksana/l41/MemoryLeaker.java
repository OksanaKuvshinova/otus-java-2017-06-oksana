package ru.oksana.l41;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeaker {

    private int addSize = 5_000_000;
    private double clearenceIndex = 0.95;
    private long sleepTime = 5000;

    private List<String> pool = new ArrayList<>();

    public void doWork() throws InterruptedException {
        fillPool();
        Thread.sleep(sleepTime);
        clearPoolWithLeak();
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