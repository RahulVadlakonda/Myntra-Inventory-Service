package com.myntra.api.inventory.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "genders")
@Data
@NoArgsConstructor
public class Genders {

	@Id
	private String id;
	
	@Indexed(unique = true)
	private String code;
	
	private String description;
}
