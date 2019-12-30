package dk.sens.android.rygestop;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

public class sharedPref {

    public static void save(LocalDateTime date, String key, Context context ){
        SharedPreferences prefs = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(date);
        editor.putString(key, json);
        editor.apply();
    }

    public static LocalDateTime load( String key, Context context){
        SharedPreferences prefs = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<LocalDateTime>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void saveArrayList(ArrayList<Text> texts, String key, Context context ){
        SharedPreferences prefs = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(texts);
        editor.putString(key, json);
        editor.apply();
    }

    public static ArrayList<Text> loadArrayList( String key, Context context){
        SharedPreferences prefs = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Text>>() {}.getType();


        if(json != null && !json.isEmpty())
        {

            return gson.fromJson(json, type);
        }
        else
        {
            ArrayList<Text> texts = new ArrayList<>();
            texts.add(new Text("Ryge-afvænning tekst " + Integer.toString(texts.size()+1), "Tryk for at læse", texts.size(), false));
            return texts;
        }


    }


    public static void saveUUID(String UUID, String key, Context context ){
        SharedPreferences prefs = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(UUID);
        editor.putString(key, json);
        editor.apply();
    }

    public static String loadUUID( String key, Context context){
        SharedPreferences prefs = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<String>() {}.getType();

        if(json != null && !json.isEmpty())
        {

            return gson.fromJson(json, type);
        }
        else
            {
                String json2 = UUID.randomUUID().toString();
                saveUUID(json2, key, context);
                return gson.fromJson(json2, type);
            }


    }

    public static void saveSettings(Settings_notifications Settings, String key, Context context ){
        SharedPreferences prefs = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Settings);
        editor.putString(key, json);
        editor.apply();
    }

    public static Settings_notifications loadSettings( String key, Context context){
        SharedPreferences prefs = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<Settings_notifications>() {}.getType();

        if(json != null && !json.isEmpty())
        {

            return gson.fromJson(json, type);
        }
        else
        {
            Settings_notifications settings = new Settings_notifications(7,false);
            return settings;
        }


    }

}
