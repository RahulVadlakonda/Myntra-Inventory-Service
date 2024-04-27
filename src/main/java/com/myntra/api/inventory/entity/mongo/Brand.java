package com.myntra.api.inventory.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;

@Document(collection = "brands")
@Data
public class Brand {

	@Id
	private String id;
	
	@Field(name = "brandNum")
	private Long brandNum;
	
	private String brandName;
}
