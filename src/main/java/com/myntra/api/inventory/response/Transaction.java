package com.myntra.api.inventory.response;

import lombok.Data;

@Data
public class Transaction {

	private String requestTimeStamp;

	private String responseTimeStamp;

	private long duration;
}
