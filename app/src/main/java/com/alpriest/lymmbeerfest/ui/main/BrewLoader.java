package com.alpriest.lymmbeerfest.ui.main;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

class BrewLoader {
    private Activity activity;

    public BrewLoader(Activity activity) {
        this.activity = activity;
    }

    public List<Brew> load() {
        String json = null;
        try {
            InputStream is = activity.getAssets().open("brews.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Type type = new TypeToken<List<Brew>>() {}.getType();
        return new Gson().fromJson(json, type);
    }
}
