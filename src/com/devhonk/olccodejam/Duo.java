package com.devhonk.olccodejam;

public class Duo<T1, T2> {
    private T1 elementA;
    private T2 elementB;


    public Duo(T1 elementA, T2 elementB) {
        this.elementA = elementA;
        this.elementB = elementB;
    }

    public T1 getElementA() {
        return elementA;
    }

    public T2 getElementB() {
        return elementB;
    }

    public void setElementA(T1 elementA) {
        this.elementA = elementA;
    }

    public void setElementB(T2 elementB) {
        this.elementB = elementB;
    }

    @Override
    public String toString() {
        return "Duo{" +
                "elementA=" + elementA +
                ", elementB=" + elementB +
                '}';
    }
}
