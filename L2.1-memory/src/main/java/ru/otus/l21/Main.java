package ru.otus.l21;

import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        ObjectSizeMeasurer measurer = new ObjectSizeMeasurerImpl(5_000_000);

        long objectSize = measurer.measure(() -> {
            return new String(new char[]{});
        });
        System.out.println("Empty string size = " + objectSize);


        objectSize = measurer.measure(() -> {
            return new Object();
        });
        System.out.println("Object size = " + objectSize);


        objectSize = measurer.measure(() -> {
            return new SizeTestObject();
        });
        System.out.println("Test object size = " + objectSize);

    }
}
