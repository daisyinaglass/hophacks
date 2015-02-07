package com.example.evan.hophacks2015;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Sarah on 2/7/2015.
 */
public class FlyoutMenu extends LinearLayout {

    //the different screens we are dealing with
    private View schedule;
    private View menu;

    //constants
    //this is how many pixels of the schedule will be visible when the rolesAndGoals tab is open
    protected static final int menuMargin = 200;

    //creating a group of enum constants
    public enum menuState {
        CLOSED, OPEN
    }



    public FlyoutMenu(Context context) {
        super(context);
    }
}
