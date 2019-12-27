package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.spring.service.FortuneService;

@Component
public class CricketCoach implements Coach {

	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	// define a default constructor
	public CricketCoach() {
		System.out.println(">> CricketCoach: inside default constructor");
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
