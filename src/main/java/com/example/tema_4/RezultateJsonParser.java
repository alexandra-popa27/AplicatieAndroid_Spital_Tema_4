package com.example.tema_4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RezultateJsonParser {
    public static List<RezultateTest> fromJson(String json){
        if(json==null||json.isEmpty()){
            return new ArrayList<>();
        }
        try {
            JSONArray array=new JSONArray(json);
            List<RezultateTest> results=new ArrayList<>();
            for(int i=0;i< array.length();i++){
                JSONObject object=array.getJSONObject(i);
                JSONObject objectRezultate=object.getJSONObject("informatii").getJSONObject("rezultateTest");
                String rezultat=objectRezultate.getString("rezultat");
                int numarAnticorpi=objectRezultate.getInt("numarAnticorpi");
                String vaccinat=objectRezultate.getString("vaccinat");
                String tipTestare=objectRezultate.getString("tipTestare");

                RezultateTest rezultateTest=new RezultateTest(rezultat,numarAnticorpi,vaccinat,tipTestare);
                results.add(rezultateTest);
            }
            return results;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  new ArrayList<>();
    }
}
