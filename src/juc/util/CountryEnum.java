package juc.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-07 22:31
 * @Description:
 */

public enum CountryEnum {
    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "魏"),
    FIVE(5, "赵"),
    SIX(6, "韩"),
    ;

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    CountryEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public static String getByCode(int id) {
        return Arrays.stream(CountryEnum.values()).filter(x -> x.getId() == id).map(x -> x.name).collect(Collectors.joining());
    }
}
