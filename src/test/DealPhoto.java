package test;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2021-12-25 14:08
 * @Description:
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class DealPhoto {
    static String str = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;: \"^`'.,";
    static int unit = 256 / str.length() + 1;

    public static void getImagePixel(String image) throws Exception {
        int[] rgb = new int[3];
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = bi.getRGB(j, i);
                rgb[0] = (pixel & 0xff0000) >> 16; //r值
                rgb[1] = (pixel & 0xff00) >> 8;  //g值
                rgb[2] = (pixel & 0xff);  //b值
                System.out.print(getChar(rgb[0], rgb[1], rgb[2]));
            }
            System.out.println("");
        }
    }

    public static String getChar(int r, int g, int b) {
        int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b); //计算灰度
        String result = str.substring(gray / unit, (gray / unit) + 1); //计算出对应的字符
        return result;
    }

    public static void main(String[] args) {
        try {
            DealPhoto.getImagePixel("I:\\HD\\1.JPG");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}