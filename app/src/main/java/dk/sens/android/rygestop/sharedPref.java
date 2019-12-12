package dk.sens.android.rygestop;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
        return gson.fromJson(json, type);
    }

}
