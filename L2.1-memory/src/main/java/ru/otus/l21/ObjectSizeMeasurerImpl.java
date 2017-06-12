package ru.otus.l21;

import java.math.BigDecimal;
import java.util.function.Supplier;

public class ObjectSizeMeasurerImpl implements ObjectSizeMeasurer {

    public static final int DEFAULT_ARRAY_SIZE = 10_000_000;

    private int arraySize = DEFAULT_ARRAY_SIZE;

    public ObjectSizeMeasurerImpl() {
    }

    public ObjectSizeMeasurerImpl(int arraySize) {
        this.arraySize = arraySize;
    }

    @Override
    public <T> long measure(Supplier<T> supplier) {
        try {
            System.gc();
            Thread.sleep(10);

            Object[] array = new Object[arraySize];

            Runtime runtime = Runtime.getRuntime();
            long occupiedMemoryBefore;
            occupiedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();

            for (int i = 0; i < array.length; i++) {
                array[i] = supplier.get();
            }

            long occupiedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
            long difference = occupiedMemoryAfter - occupiedMemoryBefore;

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

    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }
}
