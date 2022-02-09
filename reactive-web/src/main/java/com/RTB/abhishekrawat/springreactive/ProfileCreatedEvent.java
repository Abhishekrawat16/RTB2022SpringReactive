package com.RTB.abhishekrawat.springreactive;

import org.springframework.context.ApplicationEvent;

public class ProfileCreatedEvent extends ApplicationEvent {

	private static final long serialVersionUID = -1245249975193924998L;

	public ProfileCreatedEvent(Profile source) {
        super(source);
    }
}
