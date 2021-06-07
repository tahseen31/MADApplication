package edu.neu.madcourse.numad21su_tahseenhameed;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;



public class Links extends AppCompatActivity {
    private ArrayList<Items> linkList = new ArrayList<>();
    private RecyclerView rView;
    private LinksAdapter adapter;
    private RecyclerView.LayoutManager rLayoutManager;
    private FloatingActionButton addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.links_main);

        createRecyclerView();


        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(v -> {
            int pos = 0;
            callDialog(pos);
        });
    }

    private void createRecyclerView() {

        rLayoutManager = new LinearLayoutManager(this);

        rView = findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);

        adapter = new LinksAdapter(linkList);
        LinkListener itemClickListener = new LinkListener(){
            @Override
            public void onItemClick(int position) {
                //attributions bond to the item has been changed
                String link = linkList.get(position).getLinkHyper();
                try {
                    if (!link.startsWith("https://") && !link.startsWith("http://")) {
                        link = "http://" + link;
                    }
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Please try with valid link",
                            Toast.LENGTH_SHORT).show();


                }
                adapter.notifyItemChanged(position);
            }
        };
        adapter.setOnItemClickListener(itemClickListener);


        rView.setAdapter(adapter);
        rView.setLayoutManager(rLayoutManager);


    }

    private void callDialog(int pos){
        Dialog popup = new Dialog(this);
        popup.setContentView(R.layout.link_popup);

        Button add = popup.findViewById(R.id.buttonAddLink);
        Button cancel = popup.findViewById(R.id.buttonCancel);
        popup.show();

        add.setOnClickListener(v -> {
            EditText ed1 = popup.findViewById(R.id.LinkInputName);
            String n = ed1.getText().toString();

            EditText ed2 = popup.findViewById(R.id.linkEdit);
            String www = ed2.getText().toString();

            popup.cancel();
            addLink(pos, n, www);

        });
        cancel.setOnClickListener(v -> popup.cancel());

    }

    private void addLink(int position, String name, String link) {
        Items n = new Items(name, link);


        linkList.add(position, n);


        adapter.notifyItemInserted(position);

        Snackbar.make(findViewById(R.id.button_links), R.string.LinkAdded,
                Snackbar.LENGTH_SHORT)
                .show();
    }
}