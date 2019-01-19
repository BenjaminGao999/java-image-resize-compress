package org.benjamin.image;

import org.benjamin.image.utils.ConvertUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author gaozhiqiang
 * created at 2019/1/19
 */
public class ConvertUtilTest {

    @Test
    public void convert() throws IOException {

        File file = new File("./images", "watch.png");
        BufferedImage bi = ImageIO.read(file);
        BufferedImage toJPG = ConvertUtil.convertPNGToJPG(bi);
        File output = new File("./dest", file.getName());
        ImageIO.write(toJPG, "jpg", output);

    }
}
