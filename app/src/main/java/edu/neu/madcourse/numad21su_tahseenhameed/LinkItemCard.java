package edu.neu.madcourse.numad21su_tahseenhameed;

import android.os.Parcel;
import android.os.Parcelable;

public class LinkItemCard {

    private final String linkName;
    private final String linkURL;

    //Constructor
    public LinkItemCard(String linkName, String linkURL) {
        this.linkName = linkName;
        this.linkURL = linkURL;

    }

    //Getters for the imageSource, itemName and itemDesc
    public String getlinkName() {
        return linkName;
    }

    public String getLinkURL() {
        return linkURL;
    }


}