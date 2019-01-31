package com.company.task107;

import java.util.Objects;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle() {
    }

    public double square() {
        return 0.25 * Math.sqrt((a + b - c) * (a - b + c) * (-a + b + c) * (a + b + c));
    }

    public double perimeter() {
        return a + b + c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.a, a) == 0 &&
                Double.compare(triangle.b, b) == 0 &&
                Double.compare(triangle.c, c) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "Triangle: a = " + a + ", b = " + b + ", c = " + c +
                ", square = " + square() + ", perimeter = " + perimeter() + "\n";
    }
}
