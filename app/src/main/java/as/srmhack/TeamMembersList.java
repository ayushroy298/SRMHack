package as.srmhack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TeamMembersList extends AppCompatActivity {

    ArrayList memberName;
    ArrayAdapter arrayAdapter;
    String add;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_members_list);
        Intent intent=getIntent();
        String name = intent.getExtras().getString("code");
        ListView nameListView=findViewById(R.id.nameListView);
        memberName=new ArrayList();
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,memberName);
        nameListView.setAdapter(arrayAdapter);
        final TextView textView=findViewById(R.id.textView);
        FirebaseDatabase.getInstance().getReference().child("Teams").child(name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                textView.setText("Team Members");
                add=dataSnapshot.getKey();
                memberName.add(add);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}
