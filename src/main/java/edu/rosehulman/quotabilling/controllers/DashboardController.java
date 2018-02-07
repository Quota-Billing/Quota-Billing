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

        boolean configUploaded = request.cookie("configUploaded") != null && request.cookie("configUploaded").equals("true");
        response.removeCookie("configUploaded");

        //return String.format("Welcome, %s<br/>Api key: %s<br/><br/><a href='/logout'>Logout</a><br/><br/><div><form method=\"post\" action=\"setConfig\" enctype=\"multipart/form-data\"><input type=\"file\" name=\"uploaded_file\" accept=\".json\"><br/><br/><button>Upload JSON</button></form><br/><br/><a href=\"https://github.com/Quota-Billing/Java-SDK#sampleconfigjson\" target=\"_blank\">Sample JSON Config File</a></div>", partner.getName(), partner.getApikey());
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0\"/>\n" +
                "    <title>Quota and Billing Service</title>\n" +
                "\n" +
                "    <!-- CSS  -->\n" +
                "    <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/materialize.min.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/>\n" +
                "    <link href=\"css/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<nav class=\"grey darken-3\" role=\"navigation\">\n" +
                "    <div class=\"nav-wrapper container\">\n" +
                "        <a id=\"logo-container\" href=\"#\" class=\"brand-logo\"><img class=\"logo\" src=\"img/rose-r.png\"/></a>\n" +
                "        <ul class=\"right hide-on-med-and-down\">\n" +
                "            <li><a href=\"/logout\">Log Out</a></li>\n" +
                "        </ul>\n" +
                "\n" +
                "        <ul id=\"nav-mobile\" class=\"side-nav\">\n" +
                "            <li><a href=\"/logout\">Log Out</a></li>\n" +
                "        </ul>\n" +
                "        <a href=\"#\" data-activates=\"nav-mobile\" class=\"button-collapse\"><i class=\"material-icons\">menu</i></a>\n" +
                "    </div>\n" +
                "</nav>\n" +
                "\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"section\">\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col s12\">\n" +
                "                <h3 class=\"red-text text-darken-3\">Welcome, %s</h3>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"row\">\n" +
                "            <nav>\n" +
                "                <div class=\"nav-wrapper grey darken-3\">\n" +
                "                    <div class=\"col s12\">\n" +
                "                        <a href=\"/logout\" class=\"breadcrumb\">Quota Billing Service</a>\n" +
                "                        <a href=\"\" class=\"breadcrumb\">Dashboard</a>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </nav>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"section\">\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col l12 m12 s12\">\n" +
                "                <h4 class=\"red-text text-darken-3\">Api Key</h4>\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col l8 m12 s12\">\n" +
                "                        <ul class=\"collection\">\n" +
                "                            <li class=\"collection-item\"><code>%s</code></li>\n" +
                "                        </ul>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"row\">\n" +
                "            <div class=\"col l12 m12 s12\">\n" +
                "                <h4 class=\"red-text text-darken-3\">Upload Config File</h4>\n" +
                "                <div class=\"col l8 m12 s12\">\n" +
                "                    <form method=\"post\" action=\"setConfig\" enctype=\"multipart/form-data\">\n" +
                "                        <div class=\"file-field input-field\">\n" +
                "                            <div class=\"btn waves-effect waves-light btn-large grey darken-3\">\n" +
                "                                <span>File</span>\n" +
                "                                <input type=\"file\" accept=\".json\" name=\"uploaded_file\">\n" +
                "                            </div>\n" +
                "                            <div class=\"file-path-wrapper\">\n" +
                "                                <input class=\"file-path validate\" type=\"text\">\n" +
                "                            </div>\n" +
                "                        </div><br>\n" +
                "                        <input type=\"submit\" value=\"Upload\" class=\"waves-effect waves-light btn-large grey darken-3\">\n" +
                "                    </form>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<!--  Scripts-->\n" +
                "<script src=\"js/jquery-2.1.1.min.js\"></script>\n" +
                "<script src=\"js/materialize.min.js\"></script>\n" +
                "<script src=\"js/init.js\"></script>\n" +
                (configUploaded ? "<script>Materialize.toast('Config successfully uploaded.', 4000);</script>" : "") +
                "\n" +
                "</body>\n" +
                "</html>\n";
        return String.format(html, partner.getName(), partner.getApikey());
    }
}
