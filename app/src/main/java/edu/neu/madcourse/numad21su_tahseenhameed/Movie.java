package edu.neu.madcourse.numad21su_tahseenhameed;

public class Movie {

    private String title;
    private Double rating;

    public Movie(String title, Double rating){
        this.title = title;

        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }
    public Double getRating() {
        return rating;
    }
}
