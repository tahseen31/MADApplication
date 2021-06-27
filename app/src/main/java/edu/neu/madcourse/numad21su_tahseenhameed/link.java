package edu.neu.madcourse.numad21su_tahseenhameed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class link extends AppCompatActivity implements LinkDialogBox.DialogListener {
    private ArrayList<LinkItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private LinkRviewAdapter linkRviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    public void openDialog(){
        LinkDialogBox dialog=new LinkDialogBox();
        dialog.show(getSupportFragmentManager(),"dialog" );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        init(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(link.this, "Delete an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                linkRviewAdapter.notifyItemRemoved(position);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void createRecyclerView() {

        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        linkRviewAdapter = new LinkRviewAdapter(itemList);

        recyclerView.setAdapter(linkRviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

    private void initialItemData(Bundle savedInstanceState) {

        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);
                for (int i = 0; i < size; i++) {
                    String linkName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    String linkURL = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");

                    LinkItemCard linkItemCard = new LinkItemCard(linkName, linkURL);

                    itemList.add(linkItemCard);
                }
            }
        }
        else {
            LinkItemCard item1 = new LinkItemCard("Default Empty Link", "");
            LinkItemCard item2 = new LinkItemCard("Default Empty Link", "");
            LinkItemCard item3 = new LinkItemCard("Default Empty Link", "");
            LinkItemCard item4 = new LinkItemCard("Default Empty Link", "");
            itemList.add(item1);
            itemList.add(item2);
            itemList.add(item3);
            itemList.add(item4);
        }
    }

    @Override
    public void onOKClick(DialogFragment linkDialog) {
        Dialog addLinkDialog = linkDialog.getDialog();

        String linkName = ((EditText) addLinkDialog.findViewById(R.id.edit_linkName)).getText().toString();
        String linkURL = ((EditText) addLinkDialog.findViewById(R.id.edit_url)).getText().toString();

        linkDialog.dismiss();
        itemList.add(0, new LinkItemCard(linkName, linkURL));
        linkRviewAdapter.notifyItemInserted(0);
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout,"Link Added Successfully",Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    public void onCancelClick(DialogFragment linkDialog) {
        linkDialog.dismiss();
    }
}