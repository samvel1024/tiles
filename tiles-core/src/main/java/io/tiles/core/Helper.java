package io.tiles.core;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */
public class Helper {

    private Helper(){
        throw new UnsupportedOperationException();
    }

    public static <T> Optional<T> tryTo(Supplier<T> supplier, Predicate<T> predicate, int attempts) {
        for (int count = 0; count < attempts; ++count) {
            T object = supplier.get();
            if (predicate.test(object))
                return Optional.of(object);
        }
        return Optional.empty();
    }

}
