package com.example.gitfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * First screen of the app. Connects to FavoriteFoodsList and AddingFoodActivity classes.
 */
public class MainActivity extends AppCompatActivity {
    private Button favFoodsButton;
    private Button addFoodsButton;

    /**
     * Sets content view and sets up the Favorite Foods and Add Foods buttons.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favFoodsButton = (Button) findViewById(R.id.favFoodsButton);
        favFoodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityFavFoods();
            }
        });

        addFoodsButton = (Button) findViewById(R.id.addFoodsButton);
        addFoodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityAddFoods();
            }
        });
    }

    /**
     * Creates an intent to open the Favorite Food screen.
     */
    public void openActivityFavFoods() {
        Intent favFoodIntent = new Intent(this, FavoriteFoodsList.class);
        startActivity(favFoodIntent);
    }

    /**
     * Creates an intent to open the Add Foods screen.
     */
    public void openActivityAddFoods() {
        Intent addFoodIntent = new Intent(this, AddingFoodActivity.class);
        startActivity(addFoodIntent);
    }
}