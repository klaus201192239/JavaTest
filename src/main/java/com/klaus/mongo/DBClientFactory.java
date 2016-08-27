package com.klaus.mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class DBClientFactory {

	public static void main(String[] args) {

		DBClientFactory facNew = new DBClientFactory();
		
		//MongoDatabase dbNew = facNew.getDB("120.27.117.232", "schooltime");
		MongoDatabase dbNew = facNew.getDB("127.0.0.1", "schooltime");

		DBClientFactory fac = new DBClientFactory();

		MongoClient mongoClient = fac.getDBClient("120.27.117.232");

		MongoIterable<String> dbList = mongoClient.listDatabaseNames();

		for (String s : dbList) {

			if (s.equals("schooltime")) {

				MongoDatabase db = mongoClient.getDatabase(s);

				MongoIterable<String> colle = db.listCollectionNames();

				for (String co : colle) {

					if (!co.equals("system.indexes")) {

						MongoCollection<Document> collectionNew = dbNew.getCollection(co);

						MongoCollection<Document> collection = db.getCollection(co);

						MongoIterable<Document> doclist = collection.find();

						for (Document doc : doclist) {

							System.out.println(doc.toJson().toString());

							//collectionNew.insertOne(doc);

						}

					}

				}

			}

		}

	}

	private MongoClient mongoClient = null;

	public MongoClient getDBClient(String ip) {

		if (mongoClient == null) {
			intializeMongoClient(ip);

		}

		return mongoClient;

	}

	public MongoDatabase getDB(String ip, String dbName) {

		if (mongoClient == null) {
			intializeMongoClient(ip);

		}

		return mongoClient.getDatabase(dbName);

	}

	private void intializeMongoClient(String ip) {

		//// mongoClient = new MongoClient("localhost", 27017);
		// mongoClient = new MongoClient("114.215.87.133", 27017);
		mongoClient = new MongoClient(ip, 27017);

	}

	public synchronized void closeConnection() {

		if (mongoClient != null) {

			mongoClient.close();

		}
		
	}

}
