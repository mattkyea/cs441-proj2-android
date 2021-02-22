package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent act_action = new Intent(this, MainActivity2.class);
        //this line lets us open the existing activity (i.e. values will persist when we return to it)
        act_action.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//found this at https://stackoverflow.com/questions/18049284/how-to-go-to-an-already-existing-activity-from-a-different-one/18049394
        TextView x1 = (TextView) findViewById(R.id.x1);
        System.out.println("hi");
        System.out.println(x1.getText().toString());

        //make new SwipeListener to override what to do on left and right at this screen
        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                //print val in x1 and go to new screen
                System.out.println("swiped left");
                System.out.println(x1.getText().toString());
                startActivity(act_action);

            }

            @Override
            public void onSwipeRight() {
                System.out.println("swiped right");
            }
        });


    }



    }




