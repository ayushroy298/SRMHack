package as.srmhack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InfoActivity extends AppCompatActivity {

    DatabaseReference databaseReference,dr;
    String url,team,name,email,phone;
    TextView textView,textView1,textView2,textView3,textView4,teamView,nameView,emailView,phoneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //url from scanning
        Intent intent=getIntent();
        url = intent.getExtras().getString("code");
        //Bas kuch check kar raha tha
        //Toast.makeText(this, url, Toast.LENGTH_SHORT).show();

        databaseReference = FirebaseDatabase.getInstance().getReference("Teams");
        //setting the text view
        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView2);
        textView2=findViewById(R.id.textView3);
        textView3=findViewById(R.id.textView4);
        textView4=findViewById(R.id.textView5);
        teamView=findViewById(R.id.teamView);
        nameView=findViewById(R.id.nameView);
        emailView=findViewById(R.id.emailView);
        phoneView=findViewById(R.id.phoneView);

        databaseReference =FirebaseDatabase.getInstance().getReference("Teams");
        //to get the children of Team
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //iteration through names of the team
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    dr= FirebaseDatabase.getInstance().getReference("Teams").child(ds.getKey());
                    //to get to the participant name
                    Query query=dr.orderByChild("URL").equalTo(url);
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            team = dataSnapshot.getKey();
                            //iteration through participant name
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                name=data.getKey();
                                email = data.child("Email").getValue(String.class);
                                phone = data.child("Mobile").getValue(String.class);
                                dr.child(name).child("Status").setValue("1");

                                //setting value to text field
                                teamView.setText(team);
                                nameView.setText(name);
                                emailView.setText(email);
                                phoneView.setText(phone);

                                Log.i("output", team+" "+name+" "+email+" "+phone);
                                Toast.makeText(getBaseContext(), team+" "+name+" "+email+" "+phone,Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getBaseContext(),"Error,url: "+url,Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),"Error,url: "+url,Toast.LENGTH_LONG).show();
            }
        });
    }
}
