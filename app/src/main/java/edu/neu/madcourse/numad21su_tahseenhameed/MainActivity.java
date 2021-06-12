package edu.neu.madcourse.numad21su_tahseenhameed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button aboutButton;
    Button clickyButton;
    Button linksButton;
    Button locationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutButton = findViewById(R.id.aboutButton);
        clickyButton = findViewById(R.id.clickyButton);
        linksButton = findViewById(R.id.button_links);
        locationButton = findViewById(R.id.button_location);

        aboutButton.setOnClickListener(this);
        clickyButton.setOnClickListener(this);
        linksButton.setOnClickListener(this);
        locationButton.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aboutButton:
                Context context = getApplicationContext();
                CharSequence text = "Hi, I am Tahseen Hameed.\n" + " you can reach me at hameed.t@northeastern.edu";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
            case R.id.clickyButton:
                Intent intent = new Intent(MainActivity.this, ButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.button_links:
                intent = new Intent(MainActivity.this, Links.class);
                startActivity(intent);
                break;
            case R.id.button_location:
                intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
                break;
        }

    }
}