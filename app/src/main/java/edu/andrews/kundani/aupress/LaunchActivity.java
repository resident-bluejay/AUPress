package edu.andrews.kundani.aupress;

//First app activity --> displays press logo
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class LaunchActivity extends Activity {

    //delay timer
    private static int DELAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView image = (ImageView) findViewById(R.id.start_screen_imageView);
        image.setImageDrawable(getResources().getDrawable(R.drawable.logo_au_press));

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //start the main activity
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, DELAY_TIME);
    }
}
