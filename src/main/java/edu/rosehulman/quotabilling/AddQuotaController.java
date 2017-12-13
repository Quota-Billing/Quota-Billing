package edu.rosehulman.quotabilling;

import spark.Request;
import spark.Response;
import spark.Route;

public class AddQuotaController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String name = request.params(":name");
		String productId = request.params(":productId");
		String quotaId = request.params(":quotaId");
		String type = request.params(":type");
		
		Database.getInstance().addQuota(partnerId, productId, quotaId, name, type);
		
		boolean added = BillingClient.getInstance().addQuota(partnerId, productId, quotaId);
		if (!added) {
			throw new Exception();
		}
		return "";
	}

}
