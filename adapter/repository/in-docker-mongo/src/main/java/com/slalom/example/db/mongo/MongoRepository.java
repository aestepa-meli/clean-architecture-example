package com.slalom.example.db.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.slalom.example.db.mongo.model.UserDb;
import com.slalom.example.domain.entity.User;
import com.slalom.example.usecase.port.UserRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class MongoRepository implements UserRepository {

	private MongoDatabase database;

	public MongoRepository() {
		database = MongoDocker.getDb();
	}

	@Override
	public User create(User user) {
		UserDb model = UserDb.from(user);
		Document doc = model.toDocument();
		database.getCollection("user").insertOne(doc);
		String id = doc.getObjectId("_id").toString();
		model.setId(id);
		return model.toUser();
	}

	@Override
	public Optional<User> findById(String id) {
		Bson filterId = eq("_id", new ObjectId(id));
		Document doc = database.getCollection("user").find(filterId).first();

		return Optional.ofNullable(UserDb.docToUser(doc));
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Bson filterId = eq("_id", email);
		Document doc = database.getCollection("user").find(filterId).first();

		return Optional.ofNullable(UserDb.docToUser(doc));
	}

	@Override
	public List<User> findAllUsers() {

		List<User> userList = new ArrayList<>();
		FindIterable<Document> docs = database.getCollection("user").find();

		for (Document doc : docs) {
			userList.add(UserDb.docToUser(doc));
		}

		return userList;
	}
}
