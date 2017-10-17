package edu.rosehulman.quotabilling;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class BillingClient {

  private static BillingClient instance;

  private BillingClient() {
  }

  public static synchronized BillingClient getInstance() {
    if (instance == null) {
      instance = new BillingClient();
    }
    return instance;
  }

  public boolean addUser(String partnerId, String productId, String userId) throws Exception {
    // Send Billing the user

    HttpResponse<String> response = Unirest.post(Paths.BILLING_BASE + "partner/{partnerId}/product/{productId}/user/{userId}")
        .routeParam("partnerId", partnerId)
        .routeParam("product", productId)
        .routeParam("userId", userId)
        .asString();

    return response.getStatus() == 200;
  }
}
