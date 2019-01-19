package org.benjamin.image.utils;

import java.io.*;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;


/**
 * @author gaozhiqiang
 * created at 2019/1/19
 * Reduce image quality without resizing
 * http://blog.codejava.net/nam/reduce-image-quality-without-resizing/
 * 仅支持jpg格式的图片压缩，不支持png格式的图片压缩
 */
public class JPGCompressUtil {


    public static void reduceImageQuality(int sizeThreshold, String srcImg, String destImg) throws Exception {
        float quality = 1.0f;

        File file = new File(srcImg);

        long fileSize = file.length();

        if (fileSize <= sizeThreshold) {
            System.out.println("Image file size is under threshold");
            return;
        }

        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");

        ImageWriter writer = (ImageWriter) iter.next();

        ImageWriteParam iwp = writer.getDefaultWriteParam();

        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        FileInputStream inputStream = new FileInputStream(file);

        BufferedImage originalImage = ImageIO.read(inputStream);
        IIOImage image = new IIOImage(originalImage, null, null);

        float percent = 0.1f;   // 10% of 1

        while (fileSize > sizeThreshold) {
            if (percent >= quality) {
                percent = percent * 0.1f;
            }

            quality -= percent;

            File fileOut = new File(destImg);
            if (fileOut.exists()) {
                fileOut.delete();
            }
            FileImageOutputStream output = new FileImageOutputStream(fileOut);

            writer.setOutput(output);

            iwp.setCompressionQuality(quality);

            writer.write(null, image, iwp);

            File fileOut2 = new File(destImg);
            long newFileSize = fileOut2.length();
            if (newFileSize == fileSize) {
                // cannot reduce more, return
                break;
            } else {
                fileSize = newFileSize;
            }
            System.out.println("quality = " + quality + ", new file size = " + fileSize);
            output.close();
        }

        writer.dispose();

    }


    public static byte[] reduceImageQuality(int sizeThreshold, long fileSize, InputStream is) throws Exception {
        float quality = 1.0f;
        byte[] bytes = null;

        if (fileSize <= sizeThreshold) {
            System.out.println("Image file size is under threshold");
            return bytes;
        }

        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");

        ImageWriter writer = (ImageWriter) iter.next();

        ImageWriteParam iwp = writer.getDefaultWriteParam();

        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);


        BufferedImage originalImage = ImageIO.read(is);
        IIOImage image = new IIOImage(originalImage, null, null);

        float percent = 0.1f;   // 10% of 1

        while (fileSize > sizeThreshold) {
            if (percent >= quality) {
                percent = percent * 0.1f;
            }

            quality -= percent;

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            MemoryCacheImageOutputStream output = new MemoryCacheImageOutputStream(bos);

            writer.setOutput(output);

            iwp.setCompressionQuality(quality);

            writer.write(null, image, iwp);

            if (bos.size() == fileSize) {
                // cannot reduce more, return
                break;
            } else {
                fileSize = bos.size();
            }

            System.out.println("quality = " + quality + ", new file size = " + fileSize);
            bytes = bos.toByteArray();
            output.close();
        }

        writer.dispose();

        return bytes;
    }


}
