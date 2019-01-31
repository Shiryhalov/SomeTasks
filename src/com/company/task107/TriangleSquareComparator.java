package com.company.task107;

import java.util.Comparator;

public class TriangleSquareComparator implements Comparator<Triangle> {
    @Override
    public int compare(Triangle t1, Triangle t2) {
        return Double.compare(t2.square(), t1.square());
    }
}
