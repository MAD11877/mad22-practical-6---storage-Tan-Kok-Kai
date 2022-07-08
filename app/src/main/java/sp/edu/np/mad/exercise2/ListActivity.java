package sp.edu.np.mad.exercise2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements RecyclerAdapter.OnImageListener{
    public static ArrayList<User> usersList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        recyclerView = findViewById(R.id.recyclerView);
        usersList = new ArrayList<>();
        usersList = dbHandler.getUsers();
        setAdapter();
    }
    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(usersList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    /*private void setUserInfo() {
        for (int i =0; i <20; i++){
            Random random = new Random();
            String name = String.valueOf(random.nextInt(999999));
            String desc = String.valueOf(random.nextInt(999999));
            usersList.add(new User("Name" + name, "Description" + desc, i, false));

        }
    }*/

    @Override
    public void onImageClick(int position) {
        usersList.get(position);
        Bundle bundle = new Bundle();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Madness").setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String sValue = usersList.get(position).getName();
                String dValue = usersList.get(position).getDescription();
                Boolean fValue = usersList.get(position).isFollowed();
                int iValue = position;
                bundle.putBoolean("Followed", fValue);
                bundle.putString("Name", sValue);
                bundle.putString("Description", dValue);
                bundle.putInt("position", position);
                Intent myIntent = new Intent(ListActivity.this, MainActivity.class);
                myIntent.putExtras(bundle);
                startActivity(myIntent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Profile");
        alert.show();
    }
}