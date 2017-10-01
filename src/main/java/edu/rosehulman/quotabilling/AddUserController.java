package edu.rosehulman.quotabilling;

import spark.Request;
import spark.Response;
import spark.Route;

public class AddUserController implements Route {

  @Override
  public Object handle(Request request, Response response) throws Exception {
    String partnerId = request.params(":partnerId");
    String productId = request.params(":productId");
    String userId = request.params(":userId");

    // Add the user to our database
    Database.getInstance().addUser(partnerId, productId, userId);

    // Send the user to Billing
    //BillingClient.getInstance().addUser(partnerId, productId, userId);

    // Return to Quota a success
    return "";
  }
}
