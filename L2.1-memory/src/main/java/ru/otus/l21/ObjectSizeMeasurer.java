package ru.otus.l21;

import java.util.function.Supplier;

public interface ObjectSizeMeasurer {

    <T> long measure(Supplier<T> supplier);

}
