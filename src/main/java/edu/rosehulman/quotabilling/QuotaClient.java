package edu.rosehulman.quotabilling;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import edu.rosehulman.quotabilling.models.Partner;

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

  public boolean addPartner(Partner partner) throws Exception {
    HttpResponse<String> response = Unirest.post(Paths.QUOTA_BASE + "partner").body("{\"partnerId\":\"" + partner.getId() + "\",\"apiKey\":\"" + partner.getApikey() + "\"}").asString();
    return response.getStatus() == 200;
  }
}
