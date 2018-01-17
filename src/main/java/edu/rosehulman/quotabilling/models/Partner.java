package edu.rosehulman.quotabilling.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.rosehulman.quotabilling.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

@Entity("partner")
public class Partner {
	@Id
	@JsonSerialize(using = ObjectIdSerializer.class)
	private ObjectId id;
	@Property
	@JsonProperty("name")
	private String name;
	@Reference
	@JsonProperty("products")
	private List<Product> products;
	@Property
	@JsonProperty("partnerId")
	private String partnerId;
	@Property
	@JsonProperty("apikey")
	private String apikey;
	@Property
	@JsonProperty("passwordHash")
	private String passwordHash;
	@Property
	@JsonProperty("passwordSalt")
	private String passwordSalt;
	@Property
	@JsonProperty("sessionValue")
	private String sessionValue;

	public Partner() {

	}

//	public Partner(String partnerId, String name, String apikey) {
//		this.id = new ObjectId();
//		this.partnerId = partnerId;
//		this.name = name;
//		this.apikey = apikey;
//		this.products = new ArrayList<Product>();
//	}

	@JsonIgnore
	public ObjectId getObjectId() {
		if (id == null) {
			return id = new ObjectId();
		}
		return id;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

	@JsonIgnore
	public Product getProduct(String productId) {
		for (Product p : this.products) {
			if (p.getId().equals(productId)) {
				return p;
			}
		}
		return null;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@JsonIgnore
	public List<Product> getProducts() {
		return products;
	}

	public void removeProduct(Product product) {
		this.products.remove(product);
	}

	public void setPartnerId(String id) {
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

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@JsonIgnore
	public String getPasswordHash() {
		return passwordHash;
	}

	@JsonIgnore
	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	@JsonIgnore
	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	@JsonIgnore
	public String getSessionValue() {
		return sessionValue;
	}

	public void setSessionValue(String sessionValue) {
		this.sessionValue = sessionValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Partner: " + this.partnerId + "\n");
		for (Product p : this.products) {
			builder.append(p.getId().toString());
		}
		return builder.toString();
	}
}
