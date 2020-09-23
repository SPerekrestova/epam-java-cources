package com.epam.university.java.core.task015;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Task015Impl implements Task015 {
    @Override
    public double getArea(Square first, Square second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        SquareImpl firstImpl = (SquareImpl) first;
        SquareImpl secondImpl = (SquareImpl) second;
        List<PointImpl> firstPointsList = new ArrayList<>(firstImpl.getAll());
        List<PointImpl> secondPointsList = new ArrayList<>(secondImpl.getAll());

        List<Line> firstLines;
        firstLines = drawLines(firstImpl);
        List<Line> secondLines;
        secondLines = drawLines(secondImpl);
        List<PointImpl> intersectionPoints = new LinkedList<>();
        int count = 0;
        while (count < firstLines.size()) {
            for (Line secondLine : secondLines) {
                PointImpl point = findIntersectionPoints(firstLines.get(count), secondLine);
                PointImpl startFirst = firstLines.get(count).getOnePoint();
                PointImpl endFirst = firstLines.get(count).getAnotherPoint();
                PointImpl startSecond = secondLine.getOnePoint();
                PointImpl endSecond = secondLine.getAnotherPoint();
                if (point == null) {
                    continue;
                }
                if (onSegment(startFirst, point, endFirst)
                        && onSegment(startSecond, point, endSecond)) {
                    intersectionPoints.add(point);
                }
            }
            count++;
        }
        if (intersectionPoints.size() == 0
                && firstImpl.getCenter().equals(secondImpl.getCenter())) {
            return Math.min(firstImpl.getArea(), secondImpl.getArea());
        } else if (intersectionPoints.size() == 0) {
            return 0;
        }
        intersectionPoints.sort(Comparator.comparing(PointImpl::getX));
        HashSet<PointImpl> seen = new HashSet<>();
        intersectionPoints.removeIf(e -> !seen.add(e));

        if (intersectionPoints.size() <= 4) {
            SquareImpl newSquare = new SquareImpl(
                    intersectionPoints.get(0), intersectionPoints.get(1)
            );
            PointImpl center = (PointImpl) newSquare.getCenter();
            intersectionPoints.addAll(findClosest(center, firstPointsList, secondPointsList));
        }

        intersectionPoints.sort(Comparator.comparing(PointImpl::getX));
        HashSet<PointImpl> seen1 = new HashSet<>();
        intersectionPoints.removeIf(e -> !seen1.add(e));

        boolean seen2 = false;
        PointImpl best = null;
        Comparator<PointImpl> comparator = Comparator.comparing(PointImpl::getX);
        for (PointImpl intersectionPoint : intersectionPoints) {
            if (!seen2 || comparator.compare(intersectionPoint, best) < 0) {
                seen2 = true;
                best = intersectionPoint;
            }
        }
        PointImpl startPoint = (seen2 ? Optional.of(best) : Optional.<PointImpl>empty())
                .get();
        intersectionPoints.sort(new SortPoints(startPoint.getX(), startPoint.getY()));
        int size = intersectionPoints.size();
        if (size == 2) {
            return 0;
        }
        double area = 0;

        for (int i = 1; i < intersectionPoints.size() - 1; i++) {
            area += getBufArea(intersectionPoints.get(0),
                    intersectionPoints.get(i),
                    intersectionPoints.get(i + 1));
        }
        return Math.abs(area);
    }

    private List<Line> drawLines(SquareImpl shape) {
        List<PointImpl> allPoints = new LinkedList<>(shape.getAll());
        PointImpl startPoint = allPoints.stream()
                .min(Comparator.comparing(PointImpl::getX))
                .get();
        allPoints.sort(new SortPoints(startPoint.getX(), startPoint.getY()));
        List<Line> lines = new LinkedList<>();
        for (int i = 0; i < allPoints.size() - 1; i++) {
            Line line = new Line(allPoints.get(i), allPoints.get(i + 1));
            lines.add(line);
        }
        Line line = new Line(allPoints.get(allPoints.size() - 1), allPoints.get(0));
        lines.add(line);
        return lines;
    }

    private PointImpl findIntersectionPoints(Line l1, Line l2) {
        double a1 = l1.getAnotherPoint().getY() - l1.getOnePoint().getY();
        double b1 = l1.getOnePoint().getX() - l1.getAnotherPoint().getX();
        double c1 = a1 * l1.getOnePoint().getX() + b1 * l1.getOnePoint().getY();

        double a2 = l2.getAnotherPoint().getY() - l2.getOnePoint().getY();
        double b2 = l2.getOnePoint().getX() - l2.getAnotherPoint().getX();
        double c2 = a2 * l2.getOnePoint().getX() + b2 * l2.getOnePoint().getY();

        double delta = a1 * b2 - a2 * b1;
        if (delta == 0) {
            return null;
        }
        return new PointImpl((b2 * c1 - b1 * c2) / delta, (a1 * c2 - a2 * c1) / delta);
    }

    private boolean onSegment(PointImpl start, PointImpl p, PointImpl end) {
        return p.getX() <= Math.max(start.getX(), end.getX())
                && p.getX() >= Math.min(start.getX(), end.getX())
                && p.getY() <= Math.max(start.getY(), end.getY())
                && p.getY() >= Math.min(start.getY(), end.getY());
    }

    private List<PointImpl> findClosest(PointImpl c, List<PointImpl> f, List<PointImpl> s) {
        PointImpl point = new PointImpl(Double.MAX_VALUE, Double.MAX_VALUE);
        List<PointImpl> list = new ArrayList<>();
        checkCorners(c, f, point, list);
        point = new PointImpl(Double.MAX_VALUE, Double.MAX_VALUE);
        checkCorners(c, s, point, list);
        return list;
    }

    private void checkCorners(PointImpl c, List<PointImpl> s, PointImpl p, List<PointImpl> l) {
        for (PointImpl value : s) {
            double x = value.getX();
            double y = value.getY();
            double xBufHad = Math.abs(p.getX() - c.getX());
            double xBufProp = Math.abs(x - c.getX());
            double yBufHad = Math.abs(p.getY() - c.getY());
            double yBufProp = Math.abs(y - c.getY());
            if (xBufHad > xBufProp || yBufHad > yBufProp) {
                p.setX(x);
                p.setY(y);
            }
        }
        l.add(p);
    }

    static class SortPoints implements Comparator<PointImpl> {
        private final double startX;
        private final double startY;

        public SortPoints(double startX, double startY) {
            this.startX = startX;
            this.startY = startY;
        }

        @Override
        public int compare(PointImpl o1, PointImpl o2) {
            if (o1.getX() - startX == 0 && o1.getY() - startY == 0) {
                return -1;
            }
            if (o2.getX() - startX == 0 && o2.getY() - startY == 0) {
                return 1;
            }
            double tg1 = (o1.getY() - startY) / (o1.getX() - startX);
            double tg2 = (o2.getY() - startY) / (o2.getX() - startX);
            if (tg1 > tg2) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private double getLineLength(Point first, Point second) {
        double deltaX = first.getX() - second.getX();
        double deltaY = first.getY() - second.getY();
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    private double getBufArea(Point p1, Point p2, Point p3) {
        double a = getLineLength(p1, p2);
        double b = getLineLength(p2, p3);
        double c = getLineLength(p3, p1);
        double hp = (a + b + c) / 2;
        return Math.sqrt(hp * (hp - a) * (hp - b) * (hp - c));
    }
}
