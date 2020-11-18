package com.epam.university.java.core.task057;

public class WindowImpl implements Window {

    private final int levelNumber;
    private final int numberOfWindow;
    private final SideType side;

    /**
     * Default constructor.
     * @param levelNumber - floor number of the flat.
     * @param numberOfWindow - number of the window.
     * @param side - side of the window.
     */
    public WindowImpl(int levelNumber, int numberOfWindow,
                      SideType side) {
        this.levelNumber = levelNumber;
        this.numberOfWindow = numberOfWindow;
        this.side = side;
    }

    @Override
    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public int getNumberOfWindow() {
        return numberOfWindow;
    }

    @Override
    public SideType getSide() {
        return side;
    }
}
