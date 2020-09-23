package com.epam.university.java.core.task015;

public class Line {

    PointImpl onePoint;
    PointImpl anotherPoint;

    Line(PointImpl onePoint, PointImpl anotherPoint) {
        this.onePoint = onePoint;
        this.anotherPoint = anotherPoint;
    }

    public PointImpl getOnePoint() {
        return onePoint;
    }

    public void setOnePoint(PointImpl onePoint) {
        this.onePoint = onePoint;
    }

    public PointImpl getAnotherPoint() {
        return anotherPoint;
    }

    public void setAnotherPoint(PointImpl anotherPoint) {
        this.anotherPoint = anotherPoint;
    }
}
