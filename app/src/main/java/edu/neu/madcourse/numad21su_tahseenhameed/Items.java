package edu.neu.madcourse.numad21su_tahseenhameed;


public class Items implements LinkListener {

    private final String linkName;
    private final String linkHyper;

    public Items(String linkName, String linkHyper){
        this.linkName = linkName;
        this.linkHyper = linkHyper;

    }

    public String getLinkName() {
        return linkName;
    }

    public String getLinkHyper() {
        return linkHyper;
    }


    @Override
    public void onItemClick(int position) {
    }

}
