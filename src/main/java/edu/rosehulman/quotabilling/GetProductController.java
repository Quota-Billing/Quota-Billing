package edu.rosehulman.quotabilling;

import spark.Request;
import spark.Response;
import spark.Route;

public class GetProductController implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		return Database.getInstance().getProduct(partnerId, productId);
	}

}
