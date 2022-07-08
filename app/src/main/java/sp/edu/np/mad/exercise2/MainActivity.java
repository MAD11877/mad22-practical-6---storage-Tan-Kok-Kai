package sp.edu.np.mad.exercise2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String Tag = "Main Activity";
    String value;
    String dValue;
    Boolean fValue;
    Integer pValue;
    ArrayList<User> users = ListActivity.usersList;
    DBHandler dbHandler = new DBHandler(this, null, null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent receivingEnd = getIntent();
        value = receivingEnd.getStringExtra("Name");
        dValue = receivingEnd.getStringExtra("Description");
        fValue = receivingEnd.getBooleanExtra("Followed", false);
        pValue = receivingEnd.getIntExtra("position", 0);
        setContentView(R.layout.activity_main);
        TextView name = findViewById(R.id.textView);
        TextView desc = findViewById(R.id.textView2);
        name.setText(value);
        desc.setText(dValue);
        Button follow = findViewById(R.id.button);
        Button message = findViewById(R.id.button2);
        message.setText("Message");
        if (fValue == false){
            follow.setText("Follow");
        }
        else{
            follow.setText("Unfollow");
        }
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (users.get(pValue).isFollowed() == false){
                    follow.setText("Unfollow");
                    users.get(pValue).setFollowed(true);
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
                else{
                    follow.setText("Follow");
                    users.get(pValue).setFollowed(false);
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                dbHandler.updateUsers(users.get(pValue));
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent messageIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(messageIntent);
            }
        });
    }

}