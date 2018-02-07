package edu.rosehulman.quotabilling.clients;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import edu.rosehulman.quotabilling.Paths;

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
				.post(Paths.BILLING_BASE + "partner/{partnerId}/product/{productId}/addUser/{userId}")
				.routeParam("partnerId", partnerId).routeParam("productId", productId).routeParam("userId", userId)
				.asString();

		return response.getStatus() == 200;
	}

	public boolean addPartner(String partnerId) throws Exception {
		// to be implemented
		HttpResponse<String> response = Unirest
				.post(Paths.BILLING_BASE
						+ "partner/{partnerId}")
				.routeParam("partnerId", partnerId)
				.asString();

		return response.getStatus() == 200;
	}

	public boolean addProductToPartner(String partnerId, String productId) throws Exception {
		// to be implemented
		HttpResponse<String> response = Unirest
				.post(Paths.BILLING_BASE + "partner/{partnerId}/product/{productId}")
				.routeParam("partnerId", partnerId).routeParam("productId", productId)
				.asString();

		return response.getStatus() == 200;
	}

	public boolean addQuota(String partnerId, String productId, String quotaId)
			throws Exception {
		// to be implemented
		HttpResponse<String> response = Unirest
				.post(Paths.BILLING_BASE +
								"partner/{partnerId}/product/{productId}/quota/{quotaId}")
				.routeParam("partnerId", partnerId).routeParam("productId", productId).routeParam("quotaId", quotaId)
				.asString();

		return response.getStatus() == 200;
	}

	public boolean addTier(String partnerId, String productId, String quotaId, String tierId) throws Exception {
		// to be implemented
		HttpResponse<String> response = Unirest.post(Paths.BILLING_BASE +
						"partner/{partnerId}/product/{productId}/quota/{quotaId}/tier/{tierId}")
				.routeParam("partnerId", partnerId).routeParam("productId", productId).routeParam("quotaId", quotaId)
				.routeParam("tierId", tierId)
				.asString();

		return response.getStatus() == 200;
	}

	public boolean deleteUser(String partnerId, String productId, String userId) throws Exception {
		    // to be implemented
		HttpResponse<String> response = Unirest
				.delete(Paths.BILLING_BASE + "partner/{partnerId}/product/{productId}/deleteUser/{userId}")
				.routeParam("partnerId", partnerId).routeParam("productId", productId).routeParam("userId", userId)
				.asString();

		return response.getStatus() == 200;
	}

	public boolean updatePartner(String partnerId) throws Exception {
		HttpResponse<String> response = Unirest.put(Paths.BILLING_BASE + "updatePartner/{partnerId}")
				.routeParam("partnerId", partnerId).asString();

		return response.getStatus() == 200;
	}
}
