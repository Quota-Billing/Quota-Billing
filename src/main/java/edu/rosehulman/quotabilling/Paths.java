package edu.rosehulman.quotabilling;

public class Paths {

  public static String ADD_USER = "partner/:partnerId/quota/:quotaId/user/:userId";


  public static String BILLING_BASE = "http://localhost:8085/"; // TODO put this in a config file
  
  public static String ADD_USER_TO_BILLING = "http://localhost:8085/addUser/";
}
