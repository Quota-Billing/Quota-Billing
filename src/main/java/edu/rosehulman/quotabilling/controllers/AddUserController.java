package edu.rosehulman.quotabilling.controllers;

import edu.rosehulman.quotabilling.Database;
import edu.rosehulman.quotabilling.clients.BillingClient;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddUserController implements Route {

  @Override
  public Object handle(Request request, Response response) throws Exception {
    try {
      String partnerId = request.params(":partnerId");
      String productId = request.params(":productId");
      String userId = request.params(":userId");

      String ret = Database.getInstance().addUser(userId, productId, partnerId);
      System.out.println("DATABASE ADD: " + ret);
      // TODO: Add the user to our database

      // .partnerId.TODO: Send the user to Billing
      boolean sentToBilling = BillingClient.getInstance().addUser(partnerId, productId, userId);
      if (!sentToBilling) {
        throw new Exception("Not sent to billing");
      }
      // Add the user to our database

      // Send the user to Billing

      // Return to Quota a success
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    return "";
  }
}
