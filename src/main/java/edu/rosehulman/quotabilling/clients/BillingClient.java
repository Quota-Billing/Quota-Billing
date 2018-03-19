package edu.rosehulman.quotabilling.clients;

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
		return true;
	}

	public boolean addPartner(String partnerId) throws Exception {
		return true;
	}

	public boolean addProductToPartner(String partnerId, String productId) throws Exception {
		return true;
	}

	public boolean addQuota(String partnerId, String productId, String quotaId)
			throws Exception {
		return true;
	}

	public boolean addTier(String partnerId, String productId, String quotaId, String tierId) throws Exception {
		return true;
	}

	public boolean deleteUser(String partnerId, String productId, String userId) throws Exception {
		return true;
	}

	public boolean updatePartner(String partnerId) throws Exception {
		return true;
	}
}
