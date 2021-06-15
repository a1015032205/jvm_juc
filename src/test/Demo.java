package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-03 22:07
 * @Description: hello world
 */

public class Demo {

	static class Dto{
		private Integer type;
		private String name;
		private Integer id;

		private Dto(){}

		public Dto(Integer type, String name, Integer id) {
			this.type = type;
			this.name = name;
			this.id = id;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "Dto{" +
					"type=" + type +
					", name='" + name + '\'' +
					", id=" + id +
					'}';
		}
	}
	public static void main(String[] args) {

		List<Dto> list = new ArrayList<>();
		list.add(new Dto(1, "a", 1));
		list.add(new Dto(1, "b", 2));
		list.add(new Dto(1, "a", 3));
		list.add(new Dto(1, "b", 4));
		list.add(new Dto(2, "a", 5));
		list.add(new Dto(2, "b", 6));
		list.add(new Dto(2, "a", 7));
		list.add(new Dto(2, "b", 8));


		Map<Integer, List<Dto>> collect = list.stream().collect(Collectors.groupingBy(Dto::getType));
	}
}
