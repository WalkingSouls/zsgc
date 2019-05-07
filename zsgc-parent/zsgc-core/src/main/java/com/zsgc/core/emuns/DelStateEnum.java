package com.zsgc.core.emuns;

import java.util.HashMap;
import java.util.Map;

public enum DelStateEnum {
	DEL1(1, "想删除1"), DEL2(2, "想删除2"), DEL3(3, "想删除3"),DEL4(4, "想删除4"),DEL5(5, "想删除5"),DEL6(6, "其他");

	private Integer state;

	private String desc;

	DelStateEnum(Integer state, String desc) {
		this.state = state;
		this.desc = desc;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getDesc(Integer state) {
		DelStateEnum[] values = DelStateEnum.values();
		for (DelStateEnum delStateEnum : values) {
			if (state == delStateEnum.getState()) {
				return delStateEnum.getDesc();
			}
		}
		return null;
	}

	public static Map<Integer, String> getAllDesc() {
		Map<Integer, String> map = new HashMap<>();
		for (DelStateEnum delStateEnum : DelStateEnum.values()) {
			map.put(delStateEnum.getState(), delStateEnum.getDesc());
		}
		return map;
	}
}
