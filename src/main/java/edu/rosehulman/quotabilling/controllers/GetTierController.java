package edu.rosehulman.quotabilling.controllers;

import edu.rosehulman.quotabilling.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetTierController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String quotaId = request.params(":quotaId");
		String tierId = request.params(":tierId");
		return Database.getInstance().getTier(partnerId, productId, quotaId, tierId);
	}

}
