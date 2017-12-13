package edu.rosehulman.quotabilling;

import spark.Request;
import spark.Response;
import spark.Route;

public class AddPartnerController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String name = request.params(":name");
		String key = request.params(":api_key");
		String password = request.params(":password");
		
		Database.getInstance().addPartner(partnerId, name, key, password);
		
		boolean added = BillingClient.getInstance().addPartner(partnerId);
		if (!added) {
			throw new Exception();
		}
		return "";
	}

}
