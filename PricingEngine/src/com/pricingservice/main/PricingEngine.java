package com.pricingservice.main;

import java.util.Scanner;

import com.pricingservice.service.PricingService;

public class PricingEngine {

	public static void main(String[] args) {
		String line;
		StringBuilder productInfo = new StringBuilder();
		System.out.println("Please enter product Demand and Supply and Competitor price .\n");
		System.out.println("Enter Done when finished .\n");
		Scanner input = new Scanner(System.in);

		while ((line = input.nextLine()) != null && !line.equals("done")) {
			productInfo.append(line + System.lineSeparator());
		}
		input.close();
		PricingService service = new PricingService();
		service.getProdprice(productInfo.toString());
	}

}
