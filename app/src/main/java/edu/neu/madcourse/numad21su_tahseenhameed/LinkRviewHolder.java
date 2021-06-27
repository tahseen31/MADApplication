package edu.neu.madcourse.numad21su_tahseenhameed;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkRviewHolder extends RecyclerView.ViewHolder {

    public TextView linkName;
    public TextView linkURL;


    public LinkRviewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
       // linkName = itemView.findViewById(R.id.link_name);
       // linkURL = itemView.findViewById(R.id.link_URL);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}