package org.benjamin.image.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author gaozhiqiang
 * created at 2019/1/19
 */
public class ConvertUtil {

    /*
        将PNG格式的图片转化为JPG格式的图片
     */
    public static BufferedImage convertPNGToJPG(BufferedImage pngImage) {
        BufferedImage newImage = new BufferedImage(pngImage.getWidth(), pngImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        newImage.createGraphics().drawImage(pngImage, 0, 0, Color.BLACK, null);
        return newImage;
    }


}
