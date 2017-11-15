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

	public boolean addPartner(String partnerId, String name, String apiKey, String password) throws Exception {
		// to be implemented
		HttpResponse<String> response = Unirest
				.post(Paths.BILLING_BASE
						+ "addPartner/partner/{partnerId}/name/{name}/apiKey/{apiKey}/password/{password}")
				.routeParam("partnerId", partnerId).routeParam("name", name).routeParam("apiKey", apiKey)
				.routeParam("password", password).asString();

		return response.getStatus() == 200;
	}

	public boolean addProductToPartner(String partnerId, String name, String productId) throws Exception {
		// to be implemented
		HttpResponse<String> response = Unirest
				.post(Paths.BILLING_BASE + "addProductToPartner/partner/{partnerId}/name/{name}/product/{productId}")
				.routeParam("partnerId", partnerId).routeParam("name", name).routeParam("productId", productId)
				.asString();

		return response.getStatus() == 200;
	}

	public boolean addQuota(String partnerId, String productId, String quotaId, String name, String type)
			throws Exception {
		// to be implemented
		HttpResponse<String> response = Unirest
				.post(Paths.BILLING_BASE
						+ "addQuota/partner/{partnerId}/product/{productId}/quota/{quotaId}/name/{name}/type/{type}")
				.routeParam("partnerId", partnerId).routeParam("productId", productId).routeParam("name", name)
				.routeParam("type", type).asString();

		return response.getStatus() == 200;
	}

	public boolean addTier(String partnerId, String productId, String quotaId, String tierId, String name, String max,
			String price) throws Exception {
		// to be implemented
		HttpResponse<String> response = Unirest.post(Paths.BILLING_BASE
				+ "addTier/partner/{partnerId}/product/{productId}/quota/{quotaId}/tier/{tierId}/name/{name}/max/{max}/price/{price}")
				.routeParam("partnerId", partnerId).routeParam("productId", productId).routeParam("quotaId", quotaId)
				.routeParam("tierId", tierId).routeParam("name", name).routeParam("max", max).routeParam("price", price)
				.asString();

		return response.getStatus() == 200;
	}
	
	public boolean deleteUser(String partnerId, String productId, String userId) {
		    // to be implemented
		    return true;
	}
}
