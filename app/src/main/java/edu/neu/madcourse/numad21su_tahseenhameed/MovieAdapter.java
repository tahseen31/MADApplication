package edu.neu.madcourse.numad21su_tahseenhameed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private List movieList;

    public MovieAdapter(Context context , List movies){
        this.context = context;
        movieList = movies;
    }


    public class MovieHolder extends RecyclerView.ViewHolder {
        TextView title, rating;
        ConstraintLayout constraintLayout;
        public MovieHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_name);
            rating = itemView.findViewById(R.id.rating);
            constraintLayout = itemView.findViewById(R.id.item_layout);
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MovieAdapter.MovieHolder holder, int position) {

        Movie movie = (Movie) movieList.get(position);
        holder.rating.setText(movie.getRating().toString());
        holder.title.setText(movie.getTitle());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }



    }

