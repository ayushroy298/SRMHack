package as.srmhack;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView logoImageView= findViewById(R.id.logoImageView);
        final ImageView nameImageView= findViewById(R.id.nameImageView);
        logoImageView.animate().rotation(360).scaleY(0.15f).scaleX(0.15f).setDuration(1000);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                logoImageView.animate().translationXBy(-1000).setDuration(500);
                nameImageView.animate().alpha(1).setDuration(1000);
            }
        }, 1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nameImageView.animate().translationYBy(-800).setDuration(1000);

            }
        }, 1500);



    }
}
