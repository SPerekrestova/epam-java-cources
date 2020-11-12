package com.epam.university.java.core.task054;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Task054Impl implements Task054 {

    /**
     * Convert an image to black-and-white.
     * Tip: red, green and blue values of pixel have to have the same value.
     * @param inputFilePath - image to apply filter.
     * @param outputFilePath - image after filter applying.
     */
    @Override
    public BufferedImage grayscaleFilter(String inputFilePath, String outputFilePath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = img.getWidth();
        int height = img.getHeight();
        int color = (int) (Math.random() * 100);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                img.setRGB(j, i, color);
            }
        }
        try {
            ImageIO.write(img, "jpg", new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Implement Sepia filter that make an image old-like looking.
     * Tip: to implement Sepia filter you need to set pixels value by this formulas:
     * Red = 0.393 * originalRed + 0.769 * originalGreen + 0.189 * originalBlue.
     * Green = 0.349 * originalRed + 0.686 * originalGreen + 0.168 * originalBlue.
     * Blue = 0.272 * originalRed + 0.534 * originalGreen + 0.131 * originalBlue.
     * @param inputFilePath - image to apply filter.
     * @param outputFilePath - image after filter applying.
     */
    @Override
    public BufferedImage sepiaFilter(String inputFilePath, String outputFilePath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = img.getWidth();
        int height = img.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = img.getRGB(j, i);
                int r = (int) (0.393 * getRed(pixel) + 0.769 * getGreen(pixel) + 0.189 * getBlue(pixel));
                int g = (int) (0.349 * getRed(pixel) + 0.686 * getGreen(pixel) + 0.168 * getBlue(pixel));
                int b = (int) (0.272 * getRed(pixel) + 0.534 * getGreen(pixel) + 0.131 * getBlue(pixel));
                Color color = new Color(r, g, b);
                int cpixel = color.getRGB();
                img.setRGB(j, i, cpixel);
            }
        }
        try {
            ImageIO.write(img, "jpg", new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public BufferedImage reflectFilter(String inputFilePath, String outputFilePath) {
        return null;
    }

    /**
     * Return original image from path.
     * @param inputFilePath - image to return.
     * @return - original image.
     */
    @Override
    public BufferedImage originalImage(String inputFilePath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Return value of red in pixel.
     * @param pixel - integer that represents pixel from ColorModel.class.
     * @return - integer that represents value of red in pixel.
     */
    @Override
    public int getRed(int pixel) {
        return (pixel >> 16) & 0x000000FF;
    }

    /**
     * Return value of green in pixel.
     * @param pixel - integer that represents pixel from ColorModel.class.
     * @return - integer that represents value of green in pixel.
     */
    @Override
    public int getGreen(int pixel) {
        return (pixel >> 8) & 0x000000FF;
    }

    /**
     * Return value of blue in pixel.
     * @param pixel - integer that represents pixel from ColorModel.class.
     * @return - integer that represents value of blue in pixel.
     */
    @Override
    public int getBlue(int pixel) {
        return (pixel) & 0x000000FF;
    }
}
