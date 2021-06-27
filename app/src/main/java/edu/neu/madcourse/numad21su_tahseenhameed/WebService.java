package edu.neu.madcourse.numad21su_tahseenhameed;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


public class WebService extends AppCompatActivity {

    int cardLeft;
    ArrayList<String> cardList;
    Handler handle;
    String id;
    static int cardID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        handle = new Handler();
        TextView webServiceDescription = findViewById(R.id.cardMessage);
        webServiceDescription.setMovementMethod(LinkMovementMethod.getInstance());
    }


    void shuffleDeck() {
        Log.v("DEBUG", "Shuffle Deck");
        if (checkInternetPermissions()) {
            new Thread(new NewDeckRunnable(this)).start();
        } else {
            requestInternetPermissions();
        }
    }

    void drawCard() {
        Log.v("DEBUG", "Draw A Card");
        if (checkInternetPermissions()) {
            new Thread(new DrawRunnable(this)).start();
        } else {
            requestInternetPermissions();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deckbutton:
                shuffleDeck();
                cardID = cardID + 1;
                TextView errorText = findViewById(R.id.errorText);
                errorText.setText("");
                break;

            case R.id.drawButton:
                drawCard();
                break;
        }
    }

    class NewDeckRunnable implements Runnable {

        private final WebService activity;

        NewDeckRunnable(WebService activity) {
            this.activity = activity;
        }

        @Override
        public void run() {
            String newDeckUrl = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
            try {
                String str = makeGetRequest(newDeckUrl);
                JSONObject jsonObject = new JSONObject(str);
                final String deckId = jsonObject.getString("deck_id");
                final int numCardsInDeck = jsonObject.getInt("remaining");
                handle.post(new Runnable() {
                    @Override
                    public void run() {
                        activity.id = deckId;
                        activity.cardLeft = numCardsInDeck;
                        activity.cardList = new ArrayList<String>();
                        activity.updateUI();
                    }
                });
            } catch (IOException | JSONException e) {

            }
        }
    }

    class DrawRunnable implements Runnable {
        private final WebService webActivity;

        DrawRunnable(WebService webActivity) {
            this.webActivity = webActivity;
        }

        @Override
        public void run() {
            String drawCardUrl = "https://deckofcardsapi.com/api/deck/" + id + "/draw/?count=1";
            try {
                final String jsonString = makeGetRequest(drawCardUrl);
                JSONObject obj = new JSONObject(jsonString);
                JSONArray cards = obj.getJSONArray("cards");
                final ArrayList<String> cardNames = new ArrayList<String>();
                for (int i = 0; i < cards.length(); i++) {
                    JSONObject card = cards.getJSONObject(i);
                    String value = card.getString("value");
                    String suit = card.getString("suit");
                    cardNames.add(value + " OF " + suit);
                }
                final int numCardsInDeck = obj.getInt("remaining");
                handle.post(new Runnable() {
                    @Override
                    public void run() {
                        webActivity.id = id;
                        webActivity.cardLeft = numCardsInDeck;
                        webActivity.cardList = cardNames;
                        webActivity.updateUI();
                    }
                });
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    String makeGetRequest(String url) throws IOException {
        URLConnection connection = null;
        String charset = java.nio.charset.StandardCharsets.UTF_8.name();
        connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();
        return convertInputStreamToString(response);
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString(StandardCharsets.UTF_8.name());

    }

    boolean checkInternetPermissions() {
        return ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    void requestInternetPermissions() {
        requestPermissions(new String[]{Manifest.permission.INTERNET}, 1);
    }

    @SuppressLint("SetTextI18n")
    void updateUI() {
        TextView deckView = findViewById(R.id.deckView);
        deckView.setText("Unique Card Deck ID: " + cardID + "\n" + (52 - this.cardLeft) + " cards drawn");
        if((this.cardLeft) == 0){
            TextView errorText = findViewById(R.id.errorText);
            errorText.setText("All cards have been drawn");
        }
        String cardDrawn = "";
        for (String cardName : this.cardList) {
            cardDrawn += cardName + "\n";
        }
        TextView cardsView = findViewById(R.id.cardsView);
        cardsView.setText(cardDrawn);
    }
}