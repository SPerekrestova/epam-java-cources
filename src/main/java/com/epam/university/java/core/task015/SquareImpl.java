package com.epam.university.java.core.task015;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SquareImpl implements Square {

    private PointImpl first;
    private PointImpl second;
    private final PointImpl center;
    private final PointImpl third;
    private final PointImpl fourth;
    private List<PointImpl> pointList;

    /**
     * Public constructor which calculate rest of points.
     *
     * @param first first point
     * @param second second point
     */
    public SquareImpl(Point first, Point second) {
        this.first = (PointImpl) first;
        this.second = (PointImpl) second;

        double bufX = (first.getX() + second.getX()) / 2;
        double bufY = (first.getY() + second.getY()) / 2;

        center = new PointImpl(bufX, bufY); // Center point

        double xd = (first.getX() - second.getX()) / 2;
        double yd = (first.getY() - second.getY()) / 2;    // Half-diagonal

        double x3 = bufX - yd;
        double y3 = bufY + xd;    // Third corner
        third = new PointImpl(x3, y3);

        double x4 = bufX + yd;
        double y4 = bufY - xd;  // Fourth corner
        fourth = new PointImpl(x4, y4);

        pointList = new ArrayList<>();
        pointList.add(this.first);
        pointList.add(this.second);
        pointList.add(third);
        pointList.add(fourth);
    }

    @Override
    public Point getFirst() {
        return first;
    }

    @Override
    public Point getSecond() {
        return second;
    }

    @Override
    public void setFirst(Point first) {
        this.first = (PointImpl) first;
    }

    @Override
    public void setSecond(Point second) {
        this.second = (PointImpl) second;
    }

    public Collection<PointImpl> getAll() {
        return pointList;
    }

    public Point getCenter() {
        return center;
    }

    /**
     * Method for calculating width of the current square.
     *
     * @return width
     */
    public double getWidth() {
        return Math.sqrt(
                Math.pow(
                        (first.getX() - third.getX()), 2
                )
                        + Math.pow(
                        (first.getY() - third.getY()), 2
                )
        );
    }

    public double getArea() {
        return getWidth() * getWidth();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SquareImpl square = (SquareImpl) o;
        return Objects.equals(getFirst(), square.getFirst())
                && Objects.equals(getSecond(), square.getSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond());
    }
}
