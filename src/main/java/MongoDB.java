import api.Course;
import api.MeetingTime;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MongoDB {

    public static MongoClient mc;


    public static void connect(){
        mc = new MongoClient("localhost", 27017);
    }

    public static void insertCoreClassData(Course course){
        MongoDatabase db = mc.getDatabase("rucore");
        MongoCollection<Document> coll = db.getCollection("classes");
        if(course.isSatisfyCore() == true){
            Document tempDoc = new Document("courseTitle", course.getTitle())
                    .append("courseIndex", course.getIndex())
                    .append("credits", course.getCredits())
                    .append("coreCodes", course.getCoreCodes());
            coll.insertOne(tempDoc);
        }

    }

    public static void insertClassTime(int year, int semester, String campus, MeetingTime meetingTime){
        MongoDatabase db = mc.getDatabase("ruclass");


        if(meetingTime.getBuildingCode() != null){


            String campusAbbrev = meetingTime.getCampusAbbrev().toLowerCase();
            String buildingCode =  meetingTime.getBuildingCode();
            String room = meetingTime.getRoomNumber();

            //ArrayList<ArrayList<String>> days = new ArrayList<ArrayList<String>>();

            MongoCollection<Document> coll = db.getCollection("classrooms");

            Document tempDoc = new Document("buildingCode", buildingCode)
                    .append("room", room);

            ArrayList<String> m = new ArrayList<String>();
            ArrayList<String> t = new ArrayList<String>();
            ArrayList<String> w = new ArrayList<String>();
            ArrayList<String> th = new ArrayList<String>();
            ArrayList<String> f = new ArrayList<String>();
            BasicDBObject monday = new BasicDBObject("monday", m);
            BasicDBObject tuesday = new BasicDBObject("tuesday", t);
            BasicDBObject wednesday = new BasicDBObject("wednesday", w);
            BasicDBObject thursday = new BasicDBObject("thursday", th);
            BasicDBObject friday = new BasicDBObject("friday", f);

            Document buildingDocFresh = new Document("buildingCode", buildingCode)
                    .append("campus", campusAbbrev)
                    .append("room", room)
                    .append("days", monday);


            FindIterable<Document> buildingDoc = coll.find(tempDoc);

            if(buildingDoc.first() != null) {
                String doc = buildingDoc.first().toString();
                String docID = StringUtils.substringBetween(doc, "_id", ",");
                String docBldg = StringUtils.substringBetween(doc, "buildingCode=", ",");
                String docRoom = StringUtils.substringBetween(doc, "room=", ",");


                System.out.println(doc);
                System.out.println(docBldg);
                System.out.println(docRoom);

                if (docBldg.equals(meetingTime.getBuildingCode()) && docRoom.equals(meetingTime.getRoomNumber())) {
                    System.err.println(meetingTime.getMeetingDay());
                    if (meetingTime.getMeetingDay() != null) {

                    if (meetingTime.getMeetingDay().equals("M")) {
                        System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                        coll.updateMany(buildingDoc.first(), Updates.addToSet("days.monday", meetingTime.getTimeUnit()));
                    }
                    if (meetingTime.getMeetingDay().equals("T")) {
                        System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                        coll.updateMany(buildingDoc.first(), Updates.addToSet("days.tuesday", meetingTime.getTimeUnit()));
                    }
                    if (meetingTime.getMeetingDay().equals("W")) {
                        System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                        coll.updateMany(buildingDoc.first(), Updates.addToSet("days.wednesday", meetingTime.getTimeUnit()));
                    }
                    if (meetingTime.getMeetingDay().equals("TH")) {
                        System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                        coll.updateMany(buildingDoc.first(), Updates.addToSet("days.thursday", meetingTime.getTimeUnit()));
                    }
                    if (meetingTime.getMeetingDay().equals("F")) {
                        System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                        coll.updateMany(buildingDoc.first(), Updates.addToSet("days.friday", meetingTime.getTimeUnit()));
                    }
                } else {
                    System.out.println("ERROR");
                    coll.insertOne(buildingDocFresh);

                }
            }


            } else {
                System.err.println("NOT FOUND: inserting: FRESH: " + meetingTime.getTimeUnit() + meetingTime.getBuildingCode() + meetingTime.getRoomNumber() + "into: " + meetingTime.getCampusAbbrev());
                if(meetingTime.getMeetingDay().equals("M")){
                    //System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                    m.add(meetingTime.getTimeUnit());
                    buildingDocFresh.append("days", monday);
                    coll.insertOne(buildingDocFresh);
                }
                if(meetingTime.getMeetingDay().equals("T")){
                    //System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                    t.add(meetingTime.getTimeUnit());
                    buildingDocFresh.append("days", tuesday);
                    coll.insertOne(buildingDocFresh);
                }
                if(meetingTime.getMeetingDay().equals("W")){
                    //System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                    w.add(meetingTime.getTimeUnit());
                    buildingDocFresh.append("days", wednesday);
                    coll.insertOne(buildingDocFresh);
                }
                if(meetingTime.getMeetingDay().equals("TH")){
                    //System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                    th.add(meetingTime.getTimeUnit());
                    buildingDocFresh.append("days", thursday);
                    coll.insertOne(buildingDocFresh);
                }
                if(meetingTime.getMeetingDay().equals("F")){
                    //System.out.println("Inserting: " + meetingTime.getBuildingCode() + " && " + meetingTime.getRoomNumber() + " into" + doc);
                    f.add(meetingTime.getTimeUnit());
                    buildingDocFresh.append("days", friday);
                    coll.insertOne(buildingDocFresh);
                }
            }




//            Document newDoc = new Document("buildingCode", buildingCode)
//                    .append("room", room)
//                    .append("meetingTimes", meetingTimes);
//
//            Document existingDoc = new Document("buildingCode", buildingCode)
//                    .append("room", room);
//
//            FindIterable<Document> foundDoc = coll.find(existingDoc);
//
//            if(foundDoc.first() != null){
//                if((meetingTime.getBuildingCode() + meetingTime.getRoomNumber()).equals("SC203")){
//                    System.err.println(foundDoc.first());
//                }
//                System.out.println("Inserting: " + meetingTime.getTimeUnit() + meetingTime.getBuildingCode() + meetingTime.getRoomNumber());
//                coll.updateMany(foundDoc.first(), Updates.addToSet("meetingTimes", meetingTime.getTimeUnit()));
//            } else {
//                System.err.println("NOT FOUND: inserting: FRESH: " + meetingTime.getTimeUnit() + meetingTime.getBuildingCode() + meetingTime.getRoomNumber() + "into: " + meetingTime.getCampusAbbrev());
//                coll.insertOne(newDoc);
//            }
            //System.out.println(foundDoc.first());


        }
    }
}
