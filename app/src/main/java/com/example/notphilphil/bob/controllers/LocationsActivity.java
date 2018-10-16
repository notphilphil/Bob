package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.notphilphil.bob.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class LocationsActivity extends AppCompatActivity {
    private ListView locations;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Button home_bt = findViewById(R.id.home_bt);

        locations = (ListView) findViewById(R.id.location_list);

        try {
            ArrayAdapter locationsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getLocationNames());
            locations.setAdapter(locationsAdapter);
            String[] names = getLocationNames();
            ArrayList<String[]> info = getLocationInformation();
        } catch (IOException err) {
            Log.d("LocationsStuff", "Something went wrong, " + err.getMessage());
        }
        home_bt.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private ArrayList<String[]> getLocationInformation() throws IOException {
        InputStream ins = getResources()
                .openRawResource(
                        getResources()
                                .getIdentifier("location_data", "raw", getPackageName()));
        BufferedReader br = new BufferedReader(new InputStreamReader(ins));
        ArrayList<String[]> info = new ArrayList<>();
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            info.add(line.split(","));
        }
        return info;
    }

    private String[] getLocationNames() throws IOException {
        InputStream ins = getResources()
                .openRawResource(
                        getResources()
                                .getIdentifier("location_data", "raw", getPackageName()));
        BufferedReader br = new BufferedReader(new InputStreamReader(ins));
        ArrayList<String> info = new ArrayList<>();
        String line = br.readLine();
        int index = Arrays.asList(line.split(",")).indexOf("Name");
        while ((line = br.readLine()) != null) {
            info.add(line.split(",")[index]);
        }
        String[] arr = new String[info.size()];
        int idx = 0;
        for (String i : info) { arr[idx++] = i; }
        return arr;
    }
}