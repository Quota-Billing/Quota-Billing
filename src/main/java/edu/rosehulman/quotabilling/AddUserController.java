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

    // TODO: Add the user to our database

    // TODO: Send the user to Billing
    // boolean sentToBilling = BillingClient.getInstance().addUser(partnerId, productId, userId);
    // if (!sentToBilling) {
    //    throw new Exception();
    // }

    // Return to Quota a success
    return "";
  }
}
