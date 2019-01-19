package org.benjamin.image;

import org.benjamin.image.utils.JPGCompressUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author gaozhiqiang
 * created at 2019/1/19
 */
public class CompressUtilTest {
    int threshold = 1 * 1024 * 1024;


    @Test
    public void compress() throws Exception {
        File file = new File("./images", "road-18.98MB.jpg");
        BufferedImage bi = ImageIO.read(file);

        File destFile = new File("./dest", file.getName());
        destFile.createNewFile();

        JPGCompressUtil.reduceImageQuality(threshold, file.getAbsolutePath(), destFile.getAbsolutePath());

    }

    @Test
    public void compress2() throws Exception {
        File file = new File("./images", "road-18.98MB.jpg");
        FileInputStream is = new FileInputStream(file);
        byte[] bytes = JPGCompressUtil.reduceImageQuality(threshold, file.length(), is);

        File destFile = new File("./dest", file.getName());
        destFile.createNewFile();

        FileOutputStream os = new FileOutputStream(destFile);

        os.write(bytes);

        os.close();
        is.close();
    }



}
