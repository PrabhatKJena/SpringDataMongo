package edu.pk.test;

import com.mongodb.*;

import java.net.UnknownHostException;

public class MongoTest {
    public static void main(String[] args) {
        MongoClient mongoClient = null;
        try {
            // Mongo client with server "127.0.0.1" and port "27017" [like create connection in JDBC]
            mongoClient = new MongoClient("127.0.0.1", 27017);

            // Getting database [same as using database in JDBC]
            DB booksDB = mongoClient.getDB("booksDB");

            // Getting collection [like select table in RDBMS]
            DBCollection dbCollection = booksDB.getCollection("books");

            // inserting new document into collection [like insert new record into table]
            BasicDBObject newDbObject = new BasicDBObject();
            newDbObject.put("_id", "ID001");
            newDbObject.put("name", "Spring In Action");
            newDbObject.put("category", "Technical");
            newDbObject.put("author", "Craig Walls");
            newDbObject.put("price", "550");
            dbCollection.insert(newDbObject);

            // inserting new document into collection [like insert new record into table]
            newDbObject = new BasicDBObject();
            newDbObject.put("_id", "ID002");
            newDbObject.put("name", "Think In Java");
            newDbObject.put("category", "Technical");
            newDbObject.put("author", "Bruce Eckel");
            newDbObject.put("price", "450");
            dbCollection.insert(newDbObject);

            // Find all documents from the collection [like all records from the table in JDBC]
            DBCursor dbObjects = dbCollection.find();
            System.out.println("List of All Documents : ");
            for (DBObject dbObject : dbObjects)
                System.out.println(dbObject.toString());

            // Find all documents from the collection where name=Think In Java
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("name", "Think In Java");
            dbObjects = dbCollection.find(basicDBObject);
            System.out.println("List of All Documents where name = Think In Java : ");
            for (DBObject dbObject : dbObjects)
                System.out.println(dbObject.toString());

        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } finally {
            // closing client [like closing connection in JDBC]
            if (mongoClient != null)
                mongoClient.close();
        }

        // Adding comment to test SSH from IntelliJ
    }
}
