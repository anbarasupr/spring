package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.spring.service.FortuneService;

@Component
public class CricketCoach implements Coach {

	private FortuneService fortuneService;
	
	@Autowired
	public void doMethodInjection(FortuneService fortuneService) {
		System.out.println("CricketCoach doMethodInjection");
		this.fortuneService=fortuneService;
	} 
	
	@Override
	public String getDailyWorkout() {
		return "Practice your cricket";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
