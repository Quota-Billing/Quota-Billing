package edu.rosehulman.quotabilling.controllers;

import edu.rosehulman.quotabilling.Database;
import edu.rosehulman.quotabilling.models.Partner;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Optional;

import static spark.Spark.halt;

public class DashboardController implements Route {
  @Override
  public Object handle(Request request, Response response) throws Exception {
    Optional<Partner> partnerOptional = Database.getInstance().getPartnerBySession(request.session().attribute("value"));
    if (!partnerOptional.isPresent()) {
      throw halt(403);
    }
    Partner partner = partnerOptional.get();

    return String.format("Welcome, %s<br/>Api key: %s<br/><br/><a href='/logout'>Logout</a><br/><br/><div><form method=\"post\" action=\"setConfig\" enctype=\"multipart/form-data\"><input type=\"file\" name=\"uploaded_file\" accept=\".json\"><br/><br/><button>Upload JSON</button></form></div>", partner.getName(), partner.getApikey());
  }
}
