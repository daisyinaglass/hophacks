package com.example.evan.hophacks2015;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Sarah on 2/7/2015.
 * Using tutorial by Jay Lamont on Youtube.
 */
public class FlyOutContainer extends LinearLayout {

    //CONSTANTS

    //the different screens we are dealing with
    private View content;
    private View menu;

    //constants
    //this is how many pixels of the schedule will be visible when the rolesAndGoals tab is open
    protected static final int menuMargin = 200;

    //creating a group of enum constants
    //this will help with animation later, when we can add states within OPEN and CLOSED
    public enum MenuState {
        CLOSED, OPEN
    }

    //describes how content is situated when the app is first opened
    //in this case, the content takes up the full screen and the menu is set to CLOSED
    protected int currentContentOffset = 0;
    protected MenuState menuCurrentState = MenuState.CLOSED;



    //CONSTRUCTORS
    public FlyOutContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public FlyOutContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public FlyOutContainer(Context context) {
        super(context);
    }

    //attaching the slides (menu and content) to the screen
    //this cannot be done in a constructor because we don't know if the slides will be created by then
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        //referring to what's actually attached to each slide -> this is in the xml file
        this.menu = this.getChildAt(0);
        this.content = this.getChildAt(1);

        //setting the visibility of the menu slide to gone
        //this makes older versions of Android not try to draw it on the screen when it's closed
        this.menu.setVisibility(View.GONE);
    }

    //positioning the slides on the screen relative to the container
    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        //will be true if size of layout container has changed since this method called
        if (changed)
            this.calculateChildDimensions();

        //after calculating the size, position the flyout menu first (remember the margin!)
        this.menu.layout(left, top, right - menuMargin, bottom);

        //next, position the content slide
        this.content.layout(left + this.currentContentOffset, top, right + this.currentContentOffset, bottom);
    }

    //this switches the two slides, based out of menu slide
    public void toggleMenu() {
        switch (this.menuCurrentState) {
            //case of when the menu is closed and we want to open it
            case CLOSED:
                //make menu visible (from GONE)
                this.menu.setVisibility(View.VISIBLE);
                //from 0 -> width of menu
                this.currentContentOffset = this.getMenuWidth();
                //shifts horizontally the view by the value passed in
                this.content.offsetLeftAndRight(currentContentOffset);
                //set the menu to open
                this.menuCurrentState = MenuState.OPEN;
                break;
            //case of when the menu is open and we want to close it
            case OPEN:
                //first shift content view back to the left
                this.content.offsetLeftAndRight(-currentContentOffset);
                //now set offset to 0
                this.currentContentOffset = 0;
                //set menu to closed
                this.menuCurrentState = MenuState.CLOSED;
                //set menu GONE (from visible)
                this.menu.setVisibility(View.GONE);
                break;
        }
        //refresh to draw
        this.invalidate();
    }

    private int getMenuWidth() {
        return this.menu.getLayoutParams().width;
    }

    //sets the width and height to that of the content and the menu
    private void calculateChildDimensions() {
        this.content.getLayoutParams().height = this.getHeight();
        this.content.getLayoutParams().width = this.getWidth();

        //remember that the menu has a margin when open
        this.menu.getLayoutParams().height = this.getHeight() - menuMargin;
        this.menu.getLayoutParams().width = this.getWidth();
    }
}
