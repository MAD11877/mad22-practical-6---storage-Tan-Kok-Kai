package sp.edu.np.mad.exercise2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import sp.edu.np.mad.exercise2.databinding.ActivityMessageGroupBinding;

public class MessageGroup extends AppCompatActivity {
    ActivityMessageGroupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        binding = ActivityMessageGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Group1Fragment());
        Button group1 = findViewById(R.id.message1);
        Button group2 = findViewById(R.id.message2);
        group1.setText("Group 1");
        group2.setText("Group 2");
        group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Group1Fragment());
            }
        });
        group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Group2Fragment());
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.messageFragment, fragment);
        fragmentTransaction.commit();
    }
}