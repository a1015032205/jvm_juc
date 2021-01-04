/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-26 22:48
 * @Description:
 */

public class Heart {
    public static void main(String[] args) throws InterruptedException {
        char[] chars = " 春雨 Little lovely ❤".toCharArray();
        int length = 0;
        int num = chars.length;
        int len = 0;
        for (float i = 1.5f; i > -1.5f; i -= 0.15f) {
            for (float j = -1.5f; j < 1.5f; j += 0.037f) {
                float a = i * i + j * j - 1;
                if ((a * a * a - j * j * i * i * i) <= 0.0f) {
                    if (length >= num) {
                        length = 0;
                    } else {
                        System.out.print(chars[length]);
                        length++;
                    }
                } else {
                    System.out.print(" ");
                }
            }
            Thread.sleep(600);
            len++;
            if (len <= 18) {
                System.out.println();
            } else {
                System.exit(0);
            }
        }
    }
}
