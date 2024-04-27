package com.myntra.api.inventory.entity.mongo;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Getter;
import lombok.Setter;

@Document(value = "inventory")
@Getter
@Setter
public class Inventory {

	@MongoId(FieldType.OBJECT_ID)
	private String id;
	
	@DBRef
	@Field(value = "product")
	private ProductGeneral product;
	
	private Long quantity;
}
