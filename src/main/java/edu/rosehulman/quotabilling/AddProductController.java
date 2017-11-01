package edu.rosehulman.quotabilling;

import spark.Request;
import spark.Response;
import spark.Route;

public class AddProductController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String name = request.params(":name");
		String productId = request.params(":productId");
		
		Database.getInstance().addProductToPartner(partnerId, name, productId);
		
		boolean added = BillingClient.getInstance().addProductToPartner(partnerId, name, productId);
		System.out.println("here");
		if (!added) {
			throw new Exception();
		}
		return "";
	}

}
