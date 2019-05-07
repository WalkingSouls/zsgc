package com.zsgc.core.emuns;

import java.util.HashMap;
import java.util.Map;

public enum IndustryEnum {

	Industry1(1, "互联网"), Industry2(2, "金融"), Industry3(3, "房地产"), Industry4(4, "房地产");

	private Integer num;

	private String desc;

	IndustryEnum(Integer num, String desc) {
		this.num = num;
		this.desc = desc;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getDesc(Integer num) {
		IndustryEnum[] values = IndustryEnum.values();
		for (IndustryEnum industryEnum : values) {
			if (num == industryEnum.getNum()) {
				return industryEnum.getDesc();
			}
		}
		return null;
	}

	public static Map<Integer, String> getAllDesc() {
		Map<Integer, String> map = new HashMap<>();
		for (IndustryEnum industryEnum : IndustryEnum.values()) {
			map.put(industryEnum.getNum(), industryEnum.getDesc());
		}
		return map;
	}
}
