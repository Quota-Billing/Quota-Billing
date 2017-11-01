package edu.rosehulman.quotabilling;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class BillingClient {

	private static BillingClient instance;

	private BillingClient() {
	}

	public static synchronized BillingClient getInstance() {
		if (instance == null) {
			instance = new BillingClient();
		}
		return instance;
	}

	public boolean addUser(String partnerId, String productId, String userId) throws Exception {
		// Send Billing the user

		HttpResponse<String> response = Unirest
				.post(Paths.BILLING_BASE + "addUser/partner/{partnerId}/product/{productId}/user/{userId}")
				.routeParam("partnerId", partnerId).routeParam("productId", productId).routeParam("userId", userId)
				.asString();

		return response.getStatus() == 200;

	}

	public boolean addPartner(String partnerId, String name, String apiKey, String password) {
		// to be implemented
		// HttpResponse<String> response = Unirest.post(Paths.BILLING_BASE +
		// "addUser/partner/{partnerId}/product/{productId}/user/{userId}")
		// .routeParam("partnerId", partnerId)
		// .routeParam("productId", productId)
		// .routeParam("userId", userId)
		// .asString();
		//
		// return response.getStatus() == 200;
		return true;
	}

	public boolean addProductToPartner(String partnerId, String name, String productId) {
		// to be implemented
		return true;
	}

	public boolean addQuota() {
		// to be implemented
		return true;
	}

	public boolean addTier() {
		// to be implemented
		return true;
	}
}
