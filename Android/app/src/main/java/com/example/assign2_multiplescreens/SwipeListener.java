package com.example.assign2_multiplescreens;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


/*
 * I'm using this class to simplify listening for swipe events. I want to
 * be able to swipe on every page, which means each activity will need to
 * implement this class. I tried adding some globals and doing an indexing thing
 * here to simplify what each screen had to do, but it got too complex.
 *
 * So, this is a class that overrides the touch listener, and each screen will
 * make an instance of this class to listen for swipes. The nested class SimpleSwipeGestureListener
 * does the math needed to determine if it was left or right.
 *
 * https://developer.android.com/training/gestures/detector#java gave me a brief overview of the touch classes
 *
 * and https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures makes up the bulk of what I've done here.
 * I focused on understanding it rather than copy-pasting, but you can obviously see the similarities. Thus, I've commented my version to show
 * that I know what is going on.
 */
public class SwipeListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    /*
     * Constructor for this class, which initializes gestureDetector. This will allow us to detect
     * gestures on the current screen/activity (where this constructor will be called from).
     */
    public SwipeListener(Context context) {
        gestureDetector = new GestureDetector(context, new SimpleSwipeGestureListener());
    }

    /*
     * The 2 classes we'll override in each activity to bring us to a different page.
     * We need to override in each activity because the swipe will bring us somewhere
     * different depending on where we are. I tried an indexing thing but it was too complex.
     */
    public void onSwipeLeft() { }
    public void onSwipeRight() { }


    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    /*
     * This is the nested class that actually measures/listens for swipes. gestureDetector is an instance
     * of this class.
     */
    private final class SimpleSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

        /*
        * Basic click, which Android recommends to always override, even if not used.
        * This is because "all gestures begin with an onDown() message," and if we return false we will ignore
        * the rest of the gesture.
        */
        @Override
        public boolean onDown(MotionEvent event) { return true; }

        /*
         * This is what we're really interested in. This overridden function captures 2 events - the onDown() (initial press)
         * and the onReleased() (when the user's finger comes off the screen). So, with just these 2 events, we can measure
         * the line between the points at which the events occured to tell if the user is swiping left or right.
         */
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            //I've trimmed this from the Stack Overflow answer. I don't need precision, which is what the
            //thresholds and velocities can help with, and I also just want left or right, not up and down.
            float Xdistance = event2.getX() - event1.getX();
            if (Xdistance > 0) {
                //if user's finger lifts up to the right of where it was pressed down, we have a positive value
                onSwipeRight();
                return true;
            }else if(Xdistance <0){
                //if user's finger lifts up to the left of where it was pressed down, we have a negative value
                onSwipeLeft();
                return true;
            }
            //unlikely to move straight up or down, but if we wanted to measure that it would go here
            return false;
        }

    }

}

