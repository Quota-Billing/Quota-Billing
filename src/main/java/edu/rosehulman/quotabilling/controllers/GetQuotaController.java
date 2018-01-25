package edu.rosehulman.quotabilling.controllers;

import edu.rosehulman.quotabilling.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetQuotaController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String quotaId = request.params(":quotaId");
		return Database.getInstance().getQuota(partnerId, productId, quotaId);
	}

}
