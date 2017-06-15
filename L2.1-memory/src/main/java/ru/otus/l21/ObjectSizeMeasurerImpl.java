package ru.otus.l21;

import java.math.BigDecimal;
import java.util.function.Supplier;

public class ObjectSizeMeasurerImpl implements ObjectSizeMeasurer {

    public static final int DEFAULT_ARRAY_SIZE = 10_000_000;

    private int arraySize = DEFAULT_ARRAY_SIZE;

    private Object[] array;

    public ObjectSizeMeasurerImpl() {
    }

    public ObjectSizeMeasurerImpl(int arraySize) {
        this.arraySize = arraySize;
    }

    @Override
    public <T> long measure(Supplier<T> supplier) {
        try {
            array = new Object[arraySize];

            long occupiedMemoryBefore, occupiedMemoryAfter, difference;

            occupiedMemoryBefore = calculateOccupiedMemory();

            for (int i = 0; i < array.length; i++) {
                array[i] = supplier.get();
            }

            occupiedMemoryAfter = calculateOccupiedMemory();
            difference = occupiedMemoryAfter - occupiedMemoryBefore;

            return new BigDecimal(difference)
                    .divide(
                            new BigDecimal(arraySize),
                            0,
                            BigDecimal.ROUND_HALF_UP
                    ).longValue();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private long calculateOccupiedMemory() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }
}
