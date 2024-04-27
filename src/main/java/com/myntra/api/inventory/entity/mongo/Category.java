package com.myntra.api.inventory.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;

@Document(collection = "categories")
@Data
public class Category {
	
	@Id
	private String id;

	@Field(name = "categoryNum")
	private Long categoryNum;
	
	private String categoryName;
}
