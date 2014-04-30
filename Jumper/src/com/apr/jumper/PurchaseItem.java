package com.apr.jumper;

public class PurchaseItem {
	public String sku;
	public String name;
	public String price;
	public  float coins;
	
	public PurchaseItem(String sku, String name, String price){
		this.sku = sku;
		this.name = name;
		this.price = price;
		
		this.coins = Float.parseFloat(sku.replace("buy_", ""));

	}
}
