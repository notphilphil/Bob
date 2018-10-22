package com.example.notphilphil.bob.controllers;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.notphilphil.bob.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class LocationsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Button home_bt = findViewById(R.id.home_bt);
        ListView locations = findViewById(R.id.location_list);

        try {
            ArrayAdapter locationsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getLocationNames());
            locations.setAdapter(locationsAdapter);
        } catch (IOException err) {
            Log.d("LocationsStuff", "Something went wrong, " + err.getMessage());
        }
        home_bt.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        });
        locations.setOnItemClickListener((parent, view, position, id) -> {
            try {
                displayAlert(position);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void displayAlert(int position) throws IOException
    {
        ArrayList<String[]> info = getLocationInformation();
        new AlertDialog.Builder(this).setMessage(
                "Latitude:\t" + info.get(position)[2] +
                        "\nLongitude: " + info.get(position)[3] +
                        "\nStreet Address: " + info.get(position)[4] +
                        "\nCity: " + info.get(position)[5] +
                        "\nState: " + info.get(position)[6] +
                        "\nZip: " + info.get(position)[7] +
                        "\nType: " + info.get(position)[8] +
                        "\nPhone: " + info.get(position)[9] +
                        "\nWebsite: " + info.get(position)[10]
        )
                .setTitle(info.get(position)[1])
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