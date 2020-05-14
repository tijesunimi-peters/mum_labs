package com.company;

@FunctionalInterface
public interface ITask<A, B> {
    void apply(A a, B b);
}
