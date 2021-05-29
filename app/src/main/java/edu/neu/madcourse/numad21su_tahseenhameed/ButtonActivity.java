package edu.neu.madcourse.numad21su_tahseenhameed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener{
    Button A;
    Button B;
    Button C;
    Button D;
    Button E;
    Button F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        A = findViewById(R.id.buttonA);
        B = findViewById(R.id.buttonB);
        C = findViewById(R.id.buttonC);
        D = findViewById(R.id.buttonD);
        E = findViewById(R.id.buttonE);
        F = findViewById(R.id.buttonF);

        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        E.setOnClickListener(this);
        F.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonA:
                ((TextView)findViewById(R.id.pressedtextView)).setText("Pressed A");
                break;
            case R.id.buttonB:
                ((TextView)findViewById(R.id.pressedtextView)).setText("Pressed B");
                break;
            case R.id.buttonC:
                ((TextView)findViewById(R.id.pressedtextView)).setText("Pressed C");
                break;
            case R.id.buttonD:
                ((TextView)findViewById(R.id.pressedtextView)).setText("Pressed D");
                break;
            case R.id.buttonE:
                ((TextView)findViewById(R.id.pressedtextView)).setText("Pressed E");
                break;
            case R.id.buttonF:
                ((TextView)findViewById(R.id.pressedtextView)).setText("Pressed F");
                break;

        }

    }
}