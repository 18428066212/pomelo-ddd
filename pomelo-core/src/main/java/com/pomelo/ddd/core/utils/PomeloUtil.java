package com.pomelo.ddd.core.utils;

import com.pomelo.ddd.core.Pomelo;

public class PomeloUtil {

    public static <T> Pomelo<T> peel(Class<T> clazz) {

        Pomelo<T> pomelo = new Pomelo<>(clazz);

        pomelo.injectFiled();

        return pomelo;
    }
}
