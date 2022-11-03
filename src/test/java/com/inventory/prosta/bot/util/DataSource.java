package com.inventory.prosta.bot.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface DataSource<T> {

     List<T> getList(T instance, int size);

     T getSingleInstance();
}
