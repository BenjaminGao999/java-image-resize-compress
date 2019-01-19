package org.benjamin.image;

import org.benjamin.image.utils.ResizeUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author gaozhiqiang
 * created at 2019/1/19
 */
public class ResizeUtilTest {

    @Test
    public void resize() throws IOException {

        File file = new File("./images", "1464x862.png");

        BufferedImage sbi = ImageIO.read(file);

        int width = sbi.getWidth();
        int height = sbi.getHeight();

        float v1 = 1024f / width;
        float v2 = 1024f / height;

        float fractor = Math.min(v1, v2) * 0.99f;

        int dwidth = (int) (width * fractor);
        int dheight = (int) (height * fractor);

        BufferedImage image = ResizeUtil.scale(sbi, BufferedImage.TYPE_INT_RGB, dwidth, dheight, fractor, fractor);

        File output = new File("./dest", file.getName());
        ImageIO.write(image, "png", output);
    }
}
