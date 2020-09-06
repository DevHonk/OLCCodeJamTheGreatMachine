package com.devhonk.olccodejam;

@FunctionalInterface
public interface TwoInputs<T1, T2> {
    void apply(T1 a, T2 b);
}
