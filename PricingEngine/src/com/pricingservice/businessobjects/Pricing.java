package com.pricingservice.businessobjects;
public class Pricing {

	private String productName; 
    private String competitorName;
    private String competitorPrice;
    
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompetitorName() {
		return competitorName;
	}
	public void setCompetitorName(String competitorName) {
		this.competitorName = competitorName;
	}
	public String getCompetitorPrice() {
		return competitorPrice;
	}
	public void setCompetitorPrice(String competitorPrice) {
		this.competitorPrice = competitorPrice;
	}
	
	
}
