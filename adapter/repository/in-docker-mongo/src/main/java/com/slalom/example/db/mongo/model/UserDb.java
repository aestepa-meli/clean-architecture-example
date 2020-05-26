package com.slalom.example.db.mongo.model;

import com.mongodb.BasicDBObject;
import com.slalom.example.domain.entity.User;
import org.bson.Document;

import java.io.Serializable;


public class UserDb implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String email;
	private String password;
	private String lastName;
	private String firstName;
	private String role;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public User toUser() {
		return User.builder()
			.id(id)
			.email(email)
			.password(password)
			.lastName(lastName)
			.firstName(firstName)
			.build();
	}

	public static UserDb from(final User user) {
		final UserDb userDb = new UserDb();
		userDb.setId(user.getId());
		userDb.setEmail(user.getEmail());
		userDb.setPassword(user.getPassword());
		userDb.setFirstName(user.getFirstName());
		userDb.setLastName(user.getLastName());
		return userDb;
	}

	public Document toDocument(){
		final Document doc = new Document("firstname", this.firstName)
			.append("lastname", this.lastName)
			.append("email", this.email)
			.append("role", this.role)
			.append("password", this.password);
		return doc;
	}

	public static User docToUser(Document doc){
		if(doc == null){
			return null;
		}
		final UserDb userDb = new UserDb();
		userDb.setId(doc.get("_id").toString());
		userDb.setEmail(doc.get("email").toString());
		userDb.setPassword(doc.get("password").toString());
		userDb.setFirstName(doc.get("firstname").toString());
		userDb.setLastName(doc.get("lastname").toString());
		return userDb.toUser();
	}


}
