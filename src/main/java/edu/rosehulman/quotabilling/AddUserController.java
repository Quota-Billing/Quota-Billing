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
    Database db = Database.getInstance();
    db.addUser(Integer.parseInt(userId));
    db.addUserToProduct(Integer.parseInt(productId), Integer.parseInt(userId));
    db.addProductToPartner(Integer.parseInt(partnerId), Integer.parseInt(productId));
    // Send the user to Billing
    //BillingClient.getInstance().addUser(partnerId, productId, userId);

    // Return to Quota a success
    return "";
  }
}
