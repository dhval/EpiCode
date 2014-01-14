package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ObjectWrapper<T> {
    private T object;

    public ObjectWrapper(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }

    public void set(T object) {
        this.object = object;
    }
}
