package as.srmhack;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
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

import com.google.firebase.FirebaseError;
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
    int i,icnt,ocnt;
    ListView teamNameListView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teams);
        teamNameListView=findViewById(R.id.teamNameListView);
        teamName=new ArrayList();
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,teamName);
        teamNameListView.setAdapter(arrayAdapter);
        textView=findViewById(R.id.textView);
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

        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                arrayAdapter.notifyDataSetChanged();
                FirebaseDatabase.getInstance().getReference().child("Teams").addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        i=0;
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            FirebaseDatabase.getInstance().getReference().child("Teams").child(ds.getKey()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                                        icnt = 0;
                                        ocnt = 0;
                                        arrayAdapter.notifyDataSetChanged();
                                        String status = d.child("Status").getValue().toString();
                                        //Toast.makeText(viewTeams.this, status, Toast.LENGTH_SHORT).show();

                                        if (status.equals("1"))
                                            icnt = 1;
                                        if (status.equals("0"))
                                            ocnt = 1;
                                        Handler handler1 = new Handler();
                                        handler1.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                /*
                                                if (ocnt == 0&&icnt==1)
                                                    teamNameListView.getChildAt(i).setBackgroundColor(Color.GREEN);
                                                else if (icnt == 1&&ocnt==1)
                                                    teamNameListView.getChildAt(i).setBackgroundColor(Color.YELLOW);
                                                else if(icnt==0&&ocnt==1)
                                                    teamNameListView.getChildAt(i).setBackgroundColor(Color.WHITE);
                                                i++;
                                                */
                                                //Toast.makeText(viewTeams.this, Integer.toString(teamNameListView.getAdapter().getCount()), Toast.LENGTH_SHORT).show();
                                                if(i<8) {
                                                    if (ocnt == 0 && icnt == 1)
                                                        teamNameListView.getChildAt(i).setBackgroundColor(Color.GREEN);
                                                    else if (icnt == 1 && ocnt == 1)
                                                        teamNameListView.getChildAt(i).setBackgroundColor(Color.YELLOW);
                                                    else if (icnt == 0 && ocnt == 1)
                                                        teamNameListView.getChildAt(i).setBackgroundColor(Color.WHITE);
                                                }
                                                i++;
                                            }
                                        }, 2000);
                                        arrayAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        },4000);

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
