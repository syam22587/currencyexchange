package com.nosto.currencyconverter.dto;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SyamVoleti
 * 
 *         This DTO is responsible for receiving the response from Exchange API
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceivedResponse {

	/**
	 * Getters, setters and other constructors are loaded by @Data annotation
	 * provided by Lombok jar
	 * 
	 * @see <a href="https://projectlombok.org/">Project Lombok</a> for more
	 *      information.
	 * 
	 */
	private JsonNode rates;
	private String base;
	private String date;

}
