/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.javatestproject;

import com.jayway.jsonpath.JsonPath;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author v_vin
 */
public class JavaTestProject {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Now calling Files.readString() method to
        // read the file
        String json;

        ArrayList<String> tagsList = new ArrayList<String>();
        try {

            File jsonFile = new File("data.json");
            json = Files.readString(Path.of(jsonFile.getAbsolutePath()));

            System.out.println(json);
            JSONObject obj = new JSONObject(json);
//            System.out.println("Recipient : ");

            JSONArray reciepientArr = obj.getJSONArray("recipients");
//            System.out.println(reciepientArr);

            ArrayList<String> tmpList = new ArrayList<String>();
            Map<String, ArrayList<String>> dicPerson = new HashMap<String, ArrayList<String>>();

            for (int i = 0; i < reciepientArr.length(); i++) {

                JSONObject person = reciepientArr.getJSONObject(i);

                JSONArray tags = person.getJSONArray("tags");
//                System.out.println("Name: ");
//                System.out.println(person.get("name"));
                ArrayList<String> tmpList2 = new ArrayList<String>();
//                System.out.println("Tags: ");
                for (int j = 0; j < tags.length(); j++) {
                   
                    tmpList.add(tags.getString(j));
//                    System.out.println(tags.getString(j));
                    tmpList2.add(tags.getString(j));
                }
                
                dicPerson.put(person.getString("name"), tmpList2);
//                System.out.println("\n\n ");
                
//                System.out.println(dicPerson);
            }
            tagsList.addAll(tmpList.stream().distinct().toList());
           //Get Duplicate Tags
           
            ArrayList<String> pairs = new ArrayList<>();
        
        // iterate through the map and compare the tags of each user
        for (String name1 : dicPerson.keySet()) {
            for (String name2 : dicPerson.keySet()) {
                if (!name1.equals(name2)) {
                    Set<String> commonTags = new HashSet<>(dicPerson.get(name1));
                    commonTags.retainAll(dicPerson.get(name2));
                    if (commonTags.size() >= 2) {
                        pairs.add(name1 + ", " + name2 + " - " + commonTags);
                    }
                }
            }
        }
        
        // print the pairs to the console
        for (String pair : pairs) {
            System.out.println(pair);
        }
           
//            List<String> columnNames = JsonPath.read(json, "$..*");
        } catch (IOException ex) {
            Logger.getLogger(JavaTestProject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
