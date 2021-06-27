package edu.neu.madcourse.numad21su_tahseenhameed;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinkRviewAdapter extends RecyclerView.Adapter<LinkRviewHolder> {

    private final ArrayList<LinkItemCard> itemList;
    private ItemClickListener listener;

    //Constructor
    public LinkRviewAdapter(ArrayList<LinkItemCard> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public LinkRviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new LinkRviewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(LinkRviewHolder holder, int position) {
        LinkItemCard currentItem = itemList.get(position);

        holder.linkName.setText(currentItem.getlinkName());
        holder.linkURL.setText(currentItem.getLinkURL());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
