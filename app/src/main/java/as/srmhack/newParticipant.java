package as.srmhack;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class newParticipant extends AppCompatActivity {

    EditText teamEditText;
    EditText nameEditText;
    EditText emailEditText;
    EditText mobileEditText;
    EditText urlEditText;
    EditText sizeEditText;

    public void add(View view)
    {
        FirebaseDatabase.getInstance().getReference().child("Teams").child(teamEditText.getText().toString()).child(nameEditText.getText().toString()).child("Mobile").setValue(mobileEditText.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Teams").child(teamEditText.getText().toString()).child(nameEditText.getText().toString()).child("Email").setValue(emailEditText.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Teams").child(teamEditText.getText().toString()).child(nameEditText.getText().toString()).child("URL").setValue(urlEditText.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Teams").child(teamEditText.getText().toString()).child(nameEditText.getText().toString()).child("Status").setValue("0");
        FirebaseDatabase.getInstance().getReference().child("Teams").child(teamEditText.getText().toString()).child("Status").setValue("0");
        Toast.makeText(this, "Entered", Toast.LENGTH_SHORT).show();
    }
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_participant);
        teamEditText=findViewById(R.id.teamEditText);
        nameEditText=findViewById(R.id.nameEditText);
        emailEditText=findViewById(R.id.emailEditText);
        mobileEditText=findViewById(R.id.mobileEditText);
        urlEditText=findViewById(R.id.urlEditText);
    }
}