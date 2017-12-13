package edu.rosehulman.quotabilling;

import spark.Request;
import spark.Response;
import spark.Route;

public class AddTierController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String name = request.params(":name");
		String productId = request.params(":productId");
		String quotaId = request.params(":quotaId");
		String tierId = request.params(":tierId");
		String price = request.params(":price");
		String max = request.params("max");
		
		Database.getInstance().addTier(partnerId, productId, quotaId, tierId, name, max, price);
		boolean added = BillingClient.getInstance().addTier(partnerId, productId, quotaId, tierId);
		if (!added) {
			throw new Exception();
		}
		return "";
	}

}
