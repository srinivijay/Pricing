package com.pricingservice.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.Map;

import com.pricingservice.businessobjects.Pricing;
import com.pricingservice.businessobjects.Products;


public class PricingService {

	public void getProdprice(String prodInfo){

		
		ArrayList<Pricing> pricingList = new ArrayList<Pricing>();
		HashMap<String,Products> productMap = new HashMap<>();
		String[] productDetails = prodInfo.split(System.lineSeparator());
		String[] productItemDetails;

		for(int i=0 ; i<productDetails.length ; i++){

			productItemDetails = productDetails[i].split(" ");
			if(productItemDetails.length>2){
				Products products = new Products();
				Pricing pricing = new Pricing();
				if(productItemDetails[1].equalsIgnoreCase("L") ||productItemDetails[1].equalsIgnoreCase("H"))	{

					String productName = String.valueOf(productItemDetails[0]);
					String productSupply = String.valueOf(productItemDetails[1]);
					String productDemand = String.valueOf(productItemDetails[2]);
					products.setProductName(String.valueOf(productItemDetails[0]));
					products.setProductSupply(productSupply);
					products.setProductDemand(productDemand);
					productMap.put(productName, products);

				}	
				else
				{
					pricing.setCompetitorName( String.valueOf(productItemDetails[1]));
					pricing.setProductName(String.valueOf(productItemDetails[0]));
					pricing.setCompetitorPrice(String.valueOf(productItemDetails[2]));
					pricingList.add(pricing);
				}
			}
		}
		// logic to select the lowest product price
		char alphabet = 'a';
		for (Map.Entry<String, Products> productsValue : productMap.entrySet()) {
			Products productsIter = (Products)productsValue.getValue();
			String prodDemand =productsIter.getProductDemand();
			String prodSupply= productsIter.getProductSupply();
			double SumofPrice=0;
			double lowestPrice=0;
			double finalPrice=0;
			int counter=0;
			ArrayList priceList  = new ArrayList<>();
			for (Pricing price: pricingList) {

				if(productsValue.getKey().equalsIgnoreCase(price.getProductName())){
					priceList.add( Double.parseDouble(price.getCompetitorPrice()));
					SumofPrice = SumofPrice +  Double.parseDouble(price.getCompetitorPrice());
					counter++;
				}

			}
			Collections.sort(priceList);
			double avgPrice = SumofPrice/counter;

			if(false == (((double)priceList.get(0)) <=avgPrice/2)){
				lowestPrice = (double)priceList.get(0);
			}
			else {
				lowestPrice = (double)priceList.get(1);
			}
			if(prodSupply.equalsIgnoreCase("H")&& prodDemand.equalsIgnoreCase("H"))
			{
				finalPrice =lowestPrice;
			}
			else if(prodSupply.equalsIgnoreCase("L")&& prodDemand.equalsIgnoreCase("L")){
				finalPrice =lowestPrice + ((lowestPrice*10)/100) ;
			}
			else if ((prodSupply.equalsIgnoreCase("L")&& prodDemand.equalsIgnoreCase("H"))){
				finalPrice =lowestPrice + ((lowestPrice*5)/100) ;
			}
			else if(prodSupply.equalsIgnoreCase("H")&& prodDemand.equalsIgnoreCase("L")){
				finalPrice =lowestPrice - ((lowestPrice*5)/100) ;
			}

			System.out.println(alphabet+"  "+finalPrice);
			alphabet++;
		}	        
	}

}

