package com.example.alexandercaballero.kiva;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private String text = "hola mundo!!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonObjectRequest jor = new JsonObjectRequest(
                // url
                "http://api.kivaws.org/v1/loans/newest.json",
                //json object
                null,
                //json object response listener.
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray loans = response.getJSONArray("loans");

                            JSONObject firstLoan = loans.getJSONObject(0);

                            text = firstLoan.getString("name") + " - " +firstLoan.getJSONObject("location").getString("country");

                            TextView tv = (TextView) findViewById(R.id.tilte);

                            tv.setText(text);

                            tv = (TextView) findViewById(R.id.body);
                            tv.setText(firstLoan.getString("use"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                //error listener
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //todo salio mal!!
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }

}