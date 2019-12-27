package com.spring;

import org.springframework.stereotype.Component;

import com.spring.service.FortuneService;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ThrowBallCoach implements Coach {

	private FortuneService fortuneService;
	
	// define a default constructor
	public ThrowBallCoach() {
		System.out.println(">> ThrowBallCoach: inside default constructor");
	}

	// define a setter method
	@Autowired
	public void setFortuneService(FortuneService theFortuneService) {
		System.out.println(">> ThrowBallCoach: inside setFortuneService() method");
		this.fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Practice your throw ball";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
