package test;

/**
 * @Author 秒度
 * @Email fangxin.md@Gmail.com
 * @Date 2020/12/9 3:10 下午
 * @Description
 */
public class JvmTest {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			byte[] bytes = new byte[1024 * 10*10*10];
			System.out.println(bytes);
		}

	}
}
