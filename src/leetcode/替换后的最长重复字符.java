package leetcode;

import java.util.*;

/**
 * @Author 秒度
 * @Email fangxin.md@Gmail.com
 * @Date 2021/2/2 14:20
 * @Description
 */
//给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
//
//交换字母的定义是取两个下标 i 和 j （下标从 0 开始），只要 i!=j 就交换 A[i] 和 A[j] 处的字符。例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/buddy-strings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 替换后的最长重复字符 {

	private static final String A = "abcaa";
	private static final String B = "abcbb";

	public static void main(String[] args) {
		System.out.println(buddyStrings(A, B));
	}

	public static boolean buddyStrings(String A, String B) {
		if ("".equals(A) || null == A || (A.length() != B.length())) {
			return false;
		}
		Map<String, Object> map = new HashMap<>(4);
		char[] aChars = A.toCharArray();
		char[] bChars = B.toCharArray();
		for (int i = 0; i < bChars.length; i++) {
			if (!map.isEmpty() && map.size() > 2) {
				return false;
			}
			String a = String.valueOf(aChars[i]);
			String b = String.valueOf(bChars[i]);
			if (!a.equals(b)) {
				map.put(a, b);
			}
		}
		String[] split = A.split(String.valueOf(aChars[0]));
		if (map.size() == 0 && split.length == 0) {
			return true;
		}
		boolean flag = map.size() == 2;
		if (flag) {
			Set<Map.Entry<String, Object>> entries = map.entrySet();
			List<Map.Entry<String, Object>> list = new ArrayList<>(entries);
			boolean f1 = list.get(0).getKey().equals(list.get(1).getValue());
			boolean f2 = list.get(1).getKey().equals(list.get(0).getValue());
			return f1 && f2;
		}

		return false;

	}
}
