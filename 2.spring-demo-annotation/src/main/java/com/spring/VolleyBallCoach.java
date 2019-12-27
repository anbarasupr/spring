package com.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // default
public class VolleyBallCoach implements Coach {

	
	@Override
	public String getDailyWorkout() {
		return "Practice your volley ball daily";
	}

	@Override
	public String getDailyFortune() {
		return "volley ball coach";
	}
	
	// define my init method
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println(">> VolleyBallCoach @PostConstruct: inside of doMyStartupStuff()");
	}
	
	// define my destroy method
	@PreDestroy
	public void doMyCleanupStuff() {
		System.out.println(">> VolleyBallCoach @PreDestroy: inside of doMyCleanupStuff()");		
	}

}
