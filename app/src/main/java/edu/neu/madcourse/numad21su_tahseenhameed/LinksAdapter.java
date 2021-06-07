package edu.neu.madcourse.numad21su_tahseenhameed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.linksViewHolder> {
    private final ArrayList<Items> itemList;
    private LinkListener listener;

    public LinksAdapter(ArrayList<Items> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(LinkListener listener) {
        this.listener = listener;
    }


    public class linksViewHolder extends RecyclerView.ViewHolder {
        private TextView linkName;
        private TextView link;

        public linksViewHolder(final View itemView, final LinkListener listener) {
            super(itemView);
            linkName  = itemView.findViewById(R.id.linkName);
            link = itemView.findViewById(R.id.hyperLink);
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

    @NonNull
    @Override
    public LinksAdapter.linksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View linkView = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_card, parent, false);
        return new linksViewHolder(linkView, listener);
    }

    @Override
    public void onBindViewHolder(LinksAdapter.linksViewHolder holder, int position) {
        Items current = itemList.get(position);
        holder.linkName.setText(current.getLinkName());
        holder.link.setText(current.getLinkHyper());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
