package as.srmhack;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewTeams extends AppCompatActivity
{

    DatabaseReference databaseReference;
    String name;
    ArrayAdapter arrayAdapter;
    ArrayList teamName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teams);
        final ListView teamNameListView=findViewById(R.id.teamNameListView);
        teamName=new ArrayList();
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,teamName);
        teamNameListView.setAdapter(arrayAdapter);

        final TextView textView=findViewById(R.id.textView);
        FirebaseDatabase.getInstance().getReference().child("Teams").addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                textView.setText("Team List");
                name=dataSnapshot.getKey();
                teamName.add(name);
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
        teamNameListView.setAdapter(arrayAdapter);
        teamNameListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String name= (String) teamName.get(position);
                Intent intent = new Intent(getApplicationContext(),TeamMembersList.class);
                intent.putExtra("code", name);
                startActivity(intent);

            }
        });
    }
}
