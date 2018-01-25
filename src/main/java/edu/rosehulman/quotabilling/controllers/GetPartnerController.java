package edu.rosehulman.quotabilling.controllers;

import edu.rosehulman.quotabilling.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetPartnerController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");
		return Database.getInstance().getPartner(partnerId);
	}

}
