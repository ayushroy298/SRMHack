package as.srmhack;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeamMembersList extends AppCompatActivity {

    ArrayList memberName;
    ArrayAdapter arrayAdapter;
    String add,name;
    ListView nameListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_members_list);
        Intent intent = getIntent();
        name = intent.getExtras().getString("code");
        nameListView = findViewById(R.id.nameListView);
        memberName = new ArrayList();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, memberName);
        nameListView.setAdapter(arrayAdapter);
        final TextView textView = findViewById(R.id.textView);
        FirebaseDatabase.getInstance().getReference().child("Teams").child(name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                textView.setText("Team Members");
                add = dataSnapshot.getKey();
                memberName.add(add);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                FirebaseDatabase.getInstance().getReference().child("Teams").child(name).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int i = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String status = (String) ds.child("Status").getValue();
                            if (status.equals("1")) {
                                nameListView.getChildAt(i).setBackgroundColor(Color.GREEN);
                            }
                            else
                            {
                                nameListView.getChildAt(i).setBackgroundColor(Color.WHITE);
                            }
                            i++;
                            arrayAdapter.notifyDataSetChanged();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        }, 3000);

    }
}
