package com.RTB.abhishekrawat.springreactive;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
class Profile {

	Profile(String id, String email) {
		this.id = id;
		this.email = email;
	}
	
	@Id
	private String id;
	@NotNull
	private String email;

	private double investments;

	private double assets;
	
	private double premiums;
	
	private double debts;
}
