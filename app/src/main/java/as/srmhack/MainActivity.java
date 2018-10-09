package as.srmhack;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void scanNew(View view)
    {
        Intent intent=new Intent(getApplicationContext(),scanNew.class);
        startActivity(intent);
    }

    public void ViewTeams(View view)
    {
        Intent intent=new Intent(getApplicationContext(),viewTeams.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.newParticipant:
            {
                Intent intent=new Intent(getApplicationContext(),newParticipant.class);
                startActivity(intent);
            }
            default: return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView logoImageView= findViewById(R.id.logoImageView);
        final ImageView nameImageView= findViewById(R.id.nameImageView);
        final Button scanButton=findViewById(R.id.scanButton);
        final Button teamsButton=findViewById(R.id.teamsButton);
        logoImageView.animate().rotation(720).scaleY(0.15f).scaleX(0.15f).setDuration(1000);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                logoImageView.animate().translationXBy(-1000).setDuration(1000);
                nameImageView.animate().alpha(1).setDuration(1500);
            }
        }, 1100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nameImageView.animate().translationYBy(-750).setDuration(1500);

            }
        }, 1600);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanButton.setVisibility(View.VISIBLE);
                teamsButton.setVisibility(View.VISIBLE);
            }
        },2600);



    }
}
