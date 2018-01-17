package edu.rosehulman.quotabilling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.rosehulman.quotabilling.models.Partner;
import org.apache.http.HttpException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import java.io.InputStream;
import java.util.Optional;

import static spark.Spark.halt;

public class SetConfigController implements Route {

  @Override
  public Object handle(Request request, Response response) throws Exception {
    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

    Optional<Partner> partnerOptional = Database.getInstance().getPartnerBySession(request.session().attribute("value"));
    if (!partnerOptional.isPresent()) {
      throw halt(403);
    }
    Partner partner = partnerOptional.get();

    InputStream input = request.raw().getPart("uploaded_file").getInputStream();
    String body = IOUtils.toString(input);

    // TODO: All of the database calls assume this is the first time the config has been uploaded

    JsonObject partnerJsonObject = new JsonParser().parse(body).getAsJsonObject();

    //String partnerName = partnerJsonObject.get("name").getAsString();
    //String partnerPassword = partnerJsonObject.get("password").getAsString();

    // TODO: Generate partnerId and apiKey earlier during sign up process?
    //String partnerId = UUID.randomUUID().toString();
    //String apiKey = UUID.randomUUID().toString();

    // TODO
    //Database.getInstance().addPartner(partnerId, partnerName, apiKey, partnerPassword);
    //BillingClient.getInstance().addPartner(partnerId);

    JsonArray productsJsonArray = partnerJsonObject.getAsJsonArray("products");
    productsJsonArray.iterator().forEachRemaining(productJsonElement -> {
      JsonObject productJsonObject = productJsonElement.getAsJsonObject();
      String productId = productJsonObject.get("id").getAsString();
      String productName = productJsonObject.get("name").getAsString();

      Database.getInstance().addProductToPartner(partner.getId(), productName, productId);
      try {
		BillingClient.getInstance().addProductToPartner(partner.getId(), productId);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


      JsonArray quotasJsonArray = productJsonObject.getAsJsonArray("quotas");
      quotasJsonArray.iterator().forEachRemaining(quotaJsonElement -> {
        JsonObject quotaJsonObject = quotaJsonElement.getAsJsonObject();
        String quotaId = quotaJsonObject.get("id").getAsString();
        String quotaName = quotaJsonObject.get("name").getAsString();
        String type = quotaJsonObject.get("type").getAsString();

        Database.getInstance().addQuota(partner.getId(), productId, quotaId, quotaName, type);
        try {
			BillingClient.getInstance().addQuota(partner.getId(), productId, quotaId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JsonArray tiersJsonArray = quotaJsonObject.getAsJsonArray("tiers");
        tiersJsonArray.iterator().forEachRemaining(tierJsonElement -> {
          JsonObject tierJsonObject = tierJsonElement.getAsJsonObject();
          String tierId = tierJsonObject.get("id").getAsString();
          String tierName = tierJsonObject.get("name").getAsString();
          String max = tierJsonObject.get("max").getAsString();
          String price = tierJsonObject.get("price").getAsString();

          Database.getInstance().addTier(partner.getId(), productId, quotaId, tierId, tierName, max, price);
          try {
			BillingClient.getInstance().addTier(partner.getId(), productId, quotaId, tierId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        });
      });
    });

    //partnerJsonObject.addProperty("apiKey", apiKey);
    //partnerJsonObject.addProperty("partnerId", partnerId);

    ObjectMapper mapper = new ObjectMapper();
    Partner tempPartner = Database.getInstance().getPartnerById(partner.getId()).get();
    if (!QuotaClient.getInstance().setConfig(mapper.writeValueAsString(tempPartner))) {
      throw new HttpException("Setting config to quota server failed");
    }
    
    // TODO: Add to billing's database

		//return "Use this API key in your application: " + apiKey;
    return "Config Uploaded! <a href='./dashboard'>Continue...</a>";
	}

}
