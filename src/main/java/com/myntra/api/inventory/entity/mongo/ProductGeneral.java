package com.myntra.api.inventory.entity.mongo;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Document(collection = "product_general")
@Data
public class ProductGeneral {
	
	@MongoId(FieldType.OBJECT_ID)
	@Field(name = "id")
	private String id;

	
	@Indexed(name = "productName")
	private String productName;
	
	@DBRef
	private Category category;
	
	@DBRef
	private Brand brand;
	
	@Field(targetType =  FieldType.DECIMAL128)
	private BigDecimal salePrice;
	
	@Field(targetType =  FieldType.DECIMAL128)
	private BigDecimal mrpPrice;
	
	private Double rating;
	
	private int ratingCount;
	
	@Field(targetType =  FieldType.DATE_TIME)
	private LocalDate addedDate;
	
	@Field(targetType =  FieldType.BOOLEAN)
	private Boolean active;
	
	@Field
	@DBRef
	private Genders gender;
}
