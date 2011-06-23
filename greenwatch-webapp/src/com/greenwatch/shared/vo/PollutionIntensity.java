package com.greenwatch.shared.vo;

public enum PollutionIntensity {
	low, high;

	public static PollutionIntensity fromString(String pIntensity) {
		if ("high".equals(pIntensity)) {
			return high;
		} else {
			return low;
		}
	}
}
