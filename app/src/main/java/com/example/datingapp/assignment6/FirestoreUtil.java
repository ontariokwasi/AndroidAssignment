package com.example.datingapp.assignment6;



import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirestoreUtil {
    public static List<Match> LOCAL_MATCHES_DATA = new ArrayList<>();
    static final String JSONSTRING = "[\n" +
            " {\n" +
            "      \"imageUrl\": \"https://i.imgur.com/fF3Iiih.jpg\",\n" +
            "      \"liked\" : false,\n" +
            "      \"name\" : \"Cool Guy Mike\",\n" +
            "      \"uid\" : \"-LBUnGOjj3Nr5DXhI-j_\",\n" +
            "      \"lat\": \"47.620497\",\n" +
            "      \"longitude\": \"-122.349059\"\n" +
            "    },\n" +
            " {\n" +
            "      \"imageUrl\": \"https://i.imgur.com/kobQVOD.jpg\",\n" +
            "      \"liked\" : true,\n" +
            "      \"name\" : \"Mark the King\",\n" +
            "      \"uid\" : \"-LBUngosfmAxrQDx1xmH\",\n" +
            "      \"lat\": \"47.604634\",\n" +
            "      \"longitude\": \"-122.331101\"\n" +
            "    },\n" +
            " {\n" +
            "      \"imageUrl\": \"https://i.imgur.com/z4OKVlA.jpg\",\n" +
            "      \"liked\" : false,\n" +
            "      \"name\" : \"Overachiever Alex\",\n" +
            "      \"uid\" : \"-LBUnncHl-pz__UNHMyF\",\n" +
            "      \"lat\": \"47.699254\",\n" +
            "      \"longitude\": \"-122.333896\"\n" +
            "    },\n" +
            " {\n" +
            "      \"imageUrl\": \"https://i.imgur.com/0Sycoon.jpg\",\n" +
            "      \"liked\" : false,\n" +
            "      \"name\" : \"Iceman Judah\",\n" +
            "      \"uid\" : \"-LBUnwSPtSlyhvVHNbPb\",\n" +
            "      \"lat\": \"47.610550\",\n" +
            "      \"longitude\": \"-122.342796\"\n" +
            "    },\n" +
            " {\n" +
            "      \"imageUrl\": \"https://i.imgur.com/idPP9QQ.jpg\",\n" +
            "      \"liked\" : true,\n" +
            "      \"name\" : \"Hayden the Wrestler\",\n" +
            "      \"uid\" : \"-LBUo1JZiL1bZi2xxBHn\",\n" +
            "      \"lat\": \"47.609349\",\n" +
            "      \"longitude\": \"-122.325362\"\n" +
            "    },\n" +
            " {\n" +
            "      \"imageUrl\": \"https://i.imgur.com/a3omF7q.jpg\",\n" +
            "      \"liked\" : false,\n" +
            "      \"name\" : \"Money man Ben\",\n" +
            "      \"uid\" : \"-LBUoAWHzXyxw5CQr5iV\",\n" +
            "      \"lat\": \"34.050769\",\n" +
            "      \"longitude\": \"-118.254149\"\n" +
            "    }\n" +
            "]";

    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    public static List<Match> initData(){
        firestore.collection("matches").get().addOnSuccessListener(q->{
            if (q.getDocuments().isEmpty()) {
                try {
                    FirestoreUtil.uploadMatches();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return retrieveAllMatches();
    }
    private static void  uploadMatches() throws JSONException {
        JSONArray jsonArray = new JSONArray(JSONSTRING);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (int i=0;i<jsonArray.length(); i++){
            db.collection("matches")
                    .add(Match.fromJSonArray((JSONObject) jsonArray.get(i)))
                    .addOnSuccessListener(documentReference -> Log.d("UPLOAD", "Added with ID: "+documentReference.getId()))
                    .addOnFailureListener(e -> Log.d("UPLOAD", e.getMessage()));
        }
    }

    private static List<Match> retrieveAllMatches(){
        List<Match> matches = new ArrayList<>();
        firestore.collection("matches").get().addOnSuccessListener(querySnapshot -> {
            querySnapshot.getDocuments().forEach(documentSnapshot -> {
                Match match = new Match();
                match.setImageUrl(documentSnapshot.get("imageUrl", String.class));
                match.setName(documentSnapshot.get("name", String.class));
                match.setUid(documentSnapshot.get("uid", String.class));
                match.setLat(documentSnapshot.get("lat", String.class));
                match.setLongitude(documentSnapshot.get("longitude", String.class));
                match.setLiked(documentSnapshot.get("liked", Boolean.class));
                matches.add(match);
            });
        });
        LOCAL_MATCHES_DATA = matches;
        return matches;
    }
    public static void updateLike(Match match){
       firestore.collection("matches").whereEqualTo("uid", match.getUid()).limit(1).get().addOnSuccessListener(task->{
           if(!task.isEmpty() && !task.getDocuments().isEmpty()){
               String documentId = task.getDocuments().get(0).getId();
               firestore.collection("matches").document(documentId).set(match);
           }
       });

    }
}
