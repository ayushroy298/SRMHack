package as.srmhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent=getIntent();
        String value;
        value = intent.getExtras().getString("code");
        Toast.makeText(this,value,Toast.LENGTH_LONG).show();
    }
}
