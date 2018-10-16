package com.example.notphilphil.bob.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        locations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    displayAlert(position);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void displayAlert(int position) throws IOException
    {
        new AlertDialog.Builder(this).setMessage(
                "Latitude:\t" + getLocationInformation().get(position)[2] +
                        "\nLongitude: " + getLocationInformation().get(position)[3] +
                        "\nStreet Address: " + getLocationInformation().get(position)[4] +
                        "\nCity: " + getLocationInformation().get(position)[5] +
                        "\nState: " + getLocationInformation().get(position)[6] +
                        "\nZip: " + getLocationInformation().get(position)[7] +
                        "\nType: " + getLocationInformation().get(position)[8] +
                        "\nPhone: " + getLocationInformation().get(position)[9] +
                        "\nWebsite: " + getLocationInformation().get(position)[10]
        )
                .setTitle(getLocationInformation().get(position)[1])
                .setCancelable(true)
                .show();
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