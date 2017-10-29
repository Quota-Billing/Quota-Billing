package edu.rosehulman.quotabilling.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;


@Entity("user")


public class User {
	@Id
	private ObjectId id;
	@Property("id")
	private String userId;
	@Reference
	private Product product;
	@Reference
	private Partner partner;
	
	public User(){
		
	}
	
	public User(String id, Product product, Partner partner){
		this.id = new ObjectId();
		this.userId = id;
		this.product = product;
		this.partner = partner;
	}
	
	public String getId(){
		return this.userId;
	}
	
	public Product getProductId(){
		return this.product;
	}
	
	public Partner getPartnerId(){
		return this.partner;
	}
}

