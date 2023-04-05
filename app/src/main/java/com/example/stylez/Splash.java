package com.example.stylez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private ImageView imageView;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Get the ImageView from the layout
        imageView = findViewById(R.id.imageView);

        // Create a fade animation
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); // add deceleration effect
        fadeIn.setDuration(1000); // Set the duration of the animation in milliseconds

        // Apply the fade animation to the ImageView
        imageView.startAnimation(fadeIn);

        // Set a listener to start the next activity when the animation ends
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Start the next activity
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);

                // Finish the splash activity
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}