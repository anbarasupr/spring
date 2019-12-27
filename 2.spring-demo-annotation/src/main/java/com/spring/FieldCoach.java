package com.spring;

import org.springframework.stereotype.Component;

import com.spring.service.FortuneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Component
@Scope("prototype") 
public class FieldCoach implements Coach {

	@Autowired
	private FortuneService fortuneService;
	
	// define a default constructor
	public FieldCoach() {
		System.out.println(">> FieldCoach: inside default constructor");
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your fielding daily";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
