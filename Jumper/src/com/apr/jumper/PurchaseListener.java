package com.apr.jumper;

/**
 * LoginListener
 * ---------------------------------------
 * Callack to be implemented when a login
 * process is succesful
 * 
 */
public interface PurchaseListener {

	/**
	 * Method to call when login succeeds
	 */
	public void purchaseSuceed(String sku);
}
