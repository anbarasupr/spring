package com.spring;

import org.springframework.stereotype.Component;

@Component("sillySwimCoach")
public class SwimmingCoach implements Coach {
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on swim practice";
	}

	@Override
	public String getDailyFortune() {
		return "silly swim coach";
	}
}
