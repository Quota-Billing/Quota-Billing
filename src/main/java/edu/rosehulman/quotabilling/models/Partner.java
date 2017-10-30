package edu.rosehulman.quotabilling.models;

import java.util.HashMap;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
@Entity("partner")
public class Partner {
	@Id
	private ObjectId id;
	@Property
	private String name;
	@Property
	private HashMap<String, Product> products = new HashMap<String, Product>();
	@Property
	private String partnerId;
	@Property
	private String apikey;
	@Property
	private String password;

	public Partner() {

	}

	public Partner(String partnerId, String name, String apikey) {
		this.id = new ObjectId();
		this.partnerId = partnerId;
		this.name = name;
		this.apikey = apikey;
	}

	public void addProduct(Product product) {
		this.products.put(product.getId(), product);
	}

	public Product getProduct(int productId) {
		return this.products.get(productId);
	}

	public void removeProduct(Product product) {
		this.products.remove(product.getId());
	}

	public void setId(String id) {
		this.partnerId = id;
	}

	public String getId() {
		return this.partnerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setPassword(String password){
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Partner: " + this.partnerId + "\n");
		for (String id : this.products.keySet()) {
			builder.append(this.products.get(id).toString());
		}
		return builder.toString();
	}
}

