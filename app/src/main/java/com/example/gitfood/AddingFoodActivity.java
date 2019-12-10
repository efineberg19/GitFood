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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to add foods to the user's favorite foods.
 */
public class AddingFoodActivity extends FavoriteFoodsList {
    private Button submitButton;
    private ArrayList<String> availableFoodsList = new ArrayList<String>();
    protected static ArrayList<CheckBox> checkBoxesList = new ArrayList<CheckBox>();
    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 001;

    /**
     * Initializes food options and ensures checked checkboxes change color.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayOfSelectedFoods = new ArrayList<String>();
        setContentView(R.layout.activity_adding_food);

        //Find the things we need
        LinearLayout checkBoxDisplay = findViewById(R.id.checkBoxDisplay);

        //Add foods to the list of available foods
        availableFoodsList.add("Udon Noodle Bowl");
        availableFoodsList.add("Strawberry Cheesecake Swirl Bar");
        availableFoodsList.add("Original Popcorn Chicken");
        availableFoodsList.add("Grilled Chicken Breast");
        availableFoodsList.add("Chocolate Chunk Cookie");
        availableFoodsList.add("Broccoli Cheddar Soup");

        //Make checkboxes change color once they're clicked
        final CheckBox checkboxOne = (CheckBox) findViewById(R.id.checkbox1);
        checkboxOne.setText(availableFoodsList.get(0));
        checkboxOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxOne.isChecked()) {
                    checkboxOne.setTextColor(getResources().getColor(R.color.colorAccent));

                } else {
                    checkboxOne.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });
        final CheckBox checkboxTwo = (CheckBox) findViewById(R.id.checkbox2);
        checkboxTwo.setText(availableFoodsList.get(1));
        checkboxTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxTwo.isChecked()) {
                    checkboxTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    checkboxTwo.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });
        final CheckBox checkboxThree = (CheckBox) findViewById(R.id.checkbox3);
        checkboxThree.setText(availableFoodsList.get(2));
        checkboxThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxThree.isChecked()) {
                    checkboxThree.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    checkboxThree.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });
        final CheckBox checkboxFour = (CheckBox) findViewById(R.id.checkbox4);
        checkboxFour.setText(availableFoodsList.get(3));
        checkboxFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxFour.isChecked()) {
                    checkboxFour.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    checkboxFour.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });
        final CheckBox checkboxFive = (CheckBox) findViewById(R.id.checkbox5);
        checkboxFive.setText(availableFoodsList.get(4));
        checkboxFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxFive.isChecked()) {
                    checkboxFive.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    checkboxFive.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });
        final CheckBox checkboxSix = (CheckBox) findViewById(R.id.checkbox6);
        checkboxSix.setText(availableFoodsList.get(5));
        checkboxSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxSix.isChecked()) {
                    checkboxSix.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    checkboxSix.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        //Initialize list of checkboxes
        checkBoxesList.add(checkboxOne);
        checkBoxesList.add(checkboxTwo);
        checkBoxesList.add(checkboxThree);
        checkBoxesList.add(checkboxFour);
        checkBoxesList.add(checkboxFive);
        checkBoxesList.add(checkboxSix);

        //Initialize submit button
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitButton.isPressed()) {
                    submitButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                } else {
                    submitButton.setBackgroundColor(getResources().getColor(R.color.submitPrimary));
                }

                updateUI();
                displayNotification();
            }
        });
    }

    public void displayNotification() {
        createNotificationChannel();
        //creating notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (this,CHANNEL_ID);
        //NotificationChannel channel = new NotificationChannel(NOTIFICATION_ID, )
        //setting notification properties:
        //a small icon:
        builder.setSmallIcon(R.drawable.ic_stat_notification);
        //a title
        builder.setContentTitle("GitFood Alert");
        //detail text
        builder.setContentText("Your Fav Food is Being Served!!!! :) ");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //issue the notification
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        //notification_ID allows to update the notification later on, add as notification
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            CharSequence name = "Personal Notifications";
            String description = "Include all the notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel
                    (CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager =(NotificationManager) getSystemService
                    (NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }

}
