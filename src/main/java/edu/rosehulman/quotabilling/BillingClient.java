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

  public void addUser(String partnerId, String quotaId, String userId) throws Exception {
    // Send Billing the user

    HttpResponse<String> response = Unirest.post(Paths.ADD_USER_TO_BILLING + "partner/{partnerId}/quota/{quotaId}/user/{userId}")
        .routeParam("partnerId", partnerId)
        .routeParam("quotaId", quotaId)
        .routeParam("userId", userId)
        .asString();

    if (response.getStatus() != 200) {
    	System.out.println(response.getStatus());
    	throw new Exception();
    }
  }
  
  public void addPartner(String partnerId){
	  
  }
}
