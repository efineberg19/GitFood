package com.example.gitfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to display screen with selected favorite foods. Foods can be clicked on to navigate to the
 * page displaying when they will be available.
 */
public class FavoriteFoodsList extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> arrayOfSelectedFoods = new ArrayList<String>();
    ArrayList<String> test = new ArrayList<>();


    /**
     * Sets the content view, initializes the listView, and sets up intents for when a food is
     * clicked.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Sets display
        setContentView(R.layout.activity_favorite_foods_list);
        listView = findViewById(R.id.listView);

        //Sets up what will happen when an item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setContentView(R.layout.activity_food);

                //Intent intent = new Intent(FavoriteFoodsList.this, FoodActivity.class);
                //intent.putExtra("FoodDetail", listView.getItemAtPosition(i).toString());

                apiRequest(listView.getItemAtPosition(i).toString());
            }
        });

        //Sets up list of foods
        ArrayAdapter arrayOfFoodsAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, arrayOfSelectedFoods);

        listView.setAdapter(arrayOfFoodsAdapter);
    }

    /**
     * Class to make the Dining Hall API request and work with resulting Json data.
     * @param foodName
     */
    public void apiRequest(final String foodName) {
        setContentView(R.layout.activity_food);
        final TextView foodTitle = findViewById(R.id.foodTitle);
        final TextView mealInfo = findViewById(R.id.mealInfo);

        //The API links for each dining hall
        String ikeAPI = "https://web.housing.illinois.edu/MobileDining2/WebService/Search.aspx?" +
                "k=7A828F94-620B-4EE3-A56F-328036CC3C04&from=12-11-2019&to=12-11-2019&id=1&t=json";
        String parAPI = "https://web.housing.illinois.edu/MobileDining2/WebService/Search.aspx?" +
                "k=7A828F94-620B-4EE3-A56F-328036CC3C04&from=12-11-2019&to=12-11-2019&id=2&t=json";
        String larAPI = "https://web.housing.illinois.edu/MobileDining2/WebService/Search.aspx?" +
                "k=7A828F94-620B-4EE3-A56F-328036CC3C04&from=12-11-2019&to=12-11-2019&id=5&t=json";

        //Ike API request
        JsonObjectRequest jsonObjectRequestIke = new JsonObjectRequest
                (Request.Method.GET, ikeAPI, null, new Response.Listener<JSONObject>() {

                    /**
                     * Works with result of request.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<String> times = new ArrayList<>();
                            JSONArray api = response.getJSONObject("Menus")
                                    .getJSONArray("Item");

                            for (int i = 0; i < api.length(); i++) {
                                if (api.getJSONObject(i).get("FormalName").equals(foodName)) {
                                    times.add(api.getJSONObject(i).get("Meal").toString());
                                }
                            }

                            if (times.size() > 0) {
                                foodTitle.setText(foodName);

                                String mealsString = "";
                                for (String t : times) {
                                    mealsString += " " + t + ",";
                                }

                                mealInfo.setText("Ike:" + mealsString.substring
                                        (0, mealsString.length() - 1));
                            }

                        } catch (Exception e) {
                            System.out.println("error");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });


        //PAR API request
        JsonObjectRequest jsonObjectRequestPAR = new JsonObjectRequest
                (Request.Method.GET, parAPI, null, new Response.Listener<JSONObject>() {

                    /**
                     * Works with result of request.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray api = response.getJSONObject("Menus").getJSONArray("Item");
                            List<String> times = new ArrayList<>();
                            for (int i = 0; i < api.length(); i++) {
                                if (api.getJSONObject(i).get("FormalName").equals(foodName)) {
                                    times.add(api.getJSONObject(i).get("Meal").toString());
                                }
                            }

                            String msg = mealInfo.getText().toString();

                            if (times.size() > 0) {
                                foodTitle.setText(foodName);

                                String mealsString = "";
                                for (String t : times) {
                                    mealsString += " " + t + ",";
                                }
                                if (!msg.equals("Dining Hall:")) {
                                    mealInfo.setText(msg + "\nPAR:" + mealsString.substring(0, mealsString.length() - 1));
                                } else {
                                    mealInfo.setText("PAR:" + mealsString.substring(0, mealsString.length() - 1));
                                }
                            }

                        } catch (Exception e) {
                            System.out.println("error");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });


        //LAR API request
        JsonObjectRequest jsonObjectRequestLAR = new JsonObjectRequest
                (Request.Method.GET, larAPI, null, new Response.Listener<JSONObject>() {

                    /**
                     * Works with result of request.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray api = response.getJSONObject("Menus").getJSONArray("Item");
                            List<String> times = new ArrayList<>();
                            for (int i = 0; i < api.length(); i++) {
                                if (api.getJSONObject(i).get("FormalName").equals(foodName)) {
                                    times.add(api.getJSONObject(i).get("Meal").toString());
                                }
                            }

                            String msg = mealInfo.getText().toString();

                            if (times.size() > 0) {
                                foodTitle.setText(foodName);

                                String mealsString = "";
                                for (String t : times) {
                                    mealsString += " " + t + ",";
                                }

                                if (!msg.equals("Dining Hall:")) {
                                    mealInfo.setText(msg + "\nLAR:" + mealsString.substring(0, mealsString.length() - 1));
                                } else {
                                    mealInfo.setText("LAR:" + mealsString.substring(0, mealsString.length() - 1));
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("error");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequestIke);
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequestPAR);
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequestLAR);
    }

    /**
     * Updates the UI when called.
     */
    public void updateUI() {
        for (CheckBox check : AddingFoodActivity.checkBoxesList) {
            if (check.isChecked() && !arrayOfSelectedFoods.contains(check.getText().toString())) {
                arrayOfSelectedFoods.add(check.getText().toString());
            }
        }
    }
}

