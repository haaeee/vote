package com.example.vote.global.util;

import java.util.Objects;
import java.util.Optional;

public class ClassUtils {

    public static <T> Optional<T> getSafeCastInstance(Object o, Class<T> clazz) {
        return !Objects.isNull(clazz) && clazz.isInstance(o) ? Optional.of(clazz.cast(o)) : Optional.empty();
    }

}
