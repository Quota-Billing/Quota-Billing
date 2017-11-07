package edu.rosehulman.quotabilling;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class QuotaClient {

  private static QuotaClient instance;

  private QuotaClient() {
  }

  public static synchronized QuotaClient getInstance() {
    if (instance == null) {
      instance = new QuotaClient();
    }
    return instance;
  }

  public boolean setConfig(String body) throws Exception {
    HttpResponse<String> response = Unirest.post(Paths.QUOTA_BASE + Paths.SET_CONFIG).body(body).asString();
    return response.getStatus() == 200;
  }
}
