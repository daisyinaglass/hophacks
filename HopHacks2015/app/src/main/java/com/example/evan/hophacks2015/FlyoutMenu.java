package com.example.evan.hophacks2015;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Sarah on 2/7/2015.
 * Using tutorial by Jay Lamont on Youtube.
 */
public class FlyoutMenu extends LinearLayout {

    //CONSTANTS

    //the different screens we are dealing with
    private View content;
    private View menu;

    //constants
    //this is how many pixels of the schedule will be visible when the rolesAndGoals tab is open
    protected static final int menuMargin = 200;

    //creating a group of enum constants
    //this will help with animation later, when we can add states within OPEN and CLOSED
    public enum menuState {
        CLOSED, OPEN
    }

    //describes how content is situated when the app is first opened
    //in this case, the content takes up the full screen and the menu is set to CLOSED
    protected int currentContentOffset = 0;
    protected MenuSate menuCurrentState = MenuState.CLOSED;



    //CONSTRUCTORS
    public FlyoutMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public FlyoutMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public FlyoutMenu(Context context) {
        super(context);
    }

    //attaching the slides (menu and content) to the screen
    //this cannot be done in a constructor because we don't know if the slides will be created by then
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        //referring to what's actually attached to each slide -> this is in the xml file
        this.menu = this.getChildAt(0);
        this.content = this.getChildAt(1);

        this.menu.setVisibility(View.GONE);
    }
}
