package com.epam.university.java.core.task054;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Task054Impl implements Task054 {

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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(img.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                Color newColor = new Color(red + green + blue,
                        red + green + blue, red + green + blue);
                img.setRGB(j, i, newColor.getRGB());
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
                int r = (int) (0.393 * getRed(pixel)
                        + 0.769 * getGreen(pixel)
                        + 0.189 * getBlue(pixel));
                int g = (int) (0.349 * getRed(pixel)
                        + 0.686 * getGreen(pixel)
                        + 0.168 * getBlue(pixel));
                int b = (int) (0.272 * getRed(pixel)
                        + 0.534 * getGreen(pixel)
                        + 0.131 * getBlue(pixel));
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
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
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);
        try {
            ImageIO.write(img, "jpg", new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

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

    @Override
    public int getRed(int pixel) {
        return (pixel >> 16) & 0x000000FF;
    }

    @Override
    public int getGreen(int pixel) {
        return (pixel >> 8) & 0x000000FF;
    }

    @Override
    public int getBlue(int pixel) {
        return (pixel) & 0x000000FF;
    }
}
