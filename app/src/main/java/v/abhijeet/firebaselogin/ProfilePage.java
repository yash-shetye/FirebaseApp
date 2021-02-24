package v.abhijeet.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {



    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        final TextView namee = (TextView)findViewById(R.id.et_name);
        final TextView mobilee =(TextView)findViewById(R.id.et_mobileno);
        final TextView emaill =(TextView) findViewById(R.id.et_emailid);
        final TextView usernamee =(TextView) findViewById(R.id.et_username);
        btn = findViewById(R.id.bookbtn);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null)
                {
                    String name = userProfile.name;

                    String mobile = userProfile.mobile;

                    String username = userProfile.username;
                    String email = userProfile.email;
                    String password = userProfile.password;


                    namee.setText(name);
                    mobilee.setText(mobile);
                    emaill.setText(email);
                    usernamee.setText(username);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfilePage.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this, hotellist.class));
            }
        });
    }
}