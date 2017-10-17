package edu.rosehulman.quotabilling;

import spark.Request;
import spark.Response;
import spark.Route;

public class AddToBillingController implements Route {

	  @Override
	  public Object handle(Request request, Response response) throws Exception {
	    String partnerId = request.params(":partnerId");
	    String quotaId = request.params(":quotaId");
	    String userId = request.params(":userId");

	    // Send the user to Billing
	    BillingClient.getInstance().addUser(partnerId, quotaId, userId);

	    // Return to Quota a success
	    return "";
	  }
	
	
}
