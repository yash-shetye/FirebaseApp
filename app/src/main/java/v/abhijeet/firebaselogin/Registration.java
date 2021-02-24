package v.abhijeet.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Registration extends AppCompatActivity  {
    EditText user_name, emailid, passwordid, cnpasswordid,mobileid,nameid;
    Button btnregister;
   FirebaseDatabase database;
    DatabaseReference ref;
    TextView textViewLogin;


    FirebaseAuth mAuth;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        user_name = findViewById(R.id.editTextUsername);
        emailid = findViewById(R.id.editTextEmail);
        passwordid = findViewById(R.id.editTextPassword);
        cnpasswordid = findViewById(R.id.editTextCnfPassword);
        btnregister = findViewById(R.id.buttonRegister);
        mobileid = findViewById(R.id.editTextMobile);
        nameid = findViewById(R.id.editTextName);

        mAuth = FirebaseAuth.getInstance();


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                resgiteruser();




            }
        });



        textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, MainActivity.class));
            }
        });


    }

    private void resgiteruser() {
        String email = emailid.getText().toString().trim();
        String password = passwordid.getText().toString().trim();
        String passwordConf = cnpasswordid.getText().toString().trim();
        String username = user_name.getText().toString().trim();
        String name = nameid.getText().toString().trim();
        String mobile = mobileid.getText().toString().trim();

        if(TextUtils.isEmpty(email)&&TextUtils.isEmpty(username)&&TextUtils.isEmpty(name)&&TextUtils.isEmpty(mobile)&&TextUtils.isEmpty(password)){
            emailid.setError("Email is Required.");
            user_name.setError("Username is Required.");
            nameid.setError("Name is Required.");
            mobileid.setError("Mobile No is Required.");
            passwordid.setError("Password is Required.");
            return;
        }


        if(password.length() < 6){
            passwordid.setError("Password Must be >= 6 Characters");
            return;
        }
        if(!email.matches(emailPattern)){

            emailid.setError("invalid format");
        }

        if(mobile.length()<10 ){

            mobileid.setError("Invalid format");
            mobileid.requestFocus();
            Toast.makeText(Registration.this, "Please enter 10 digits", Toast.LENGTH_SHORT).show();
        }
        if (password.equals(passwordConf)) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                User user = new User(name, mobile, username, email, password);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(task1 -> {

                                    if (task1.isSuccessful()) {
                                        Toast.makeText(Registration.this, "Registration Successfulll", Toast.LENGTH_SHORT).show();


                                        Intent moveToLogin = new Intent(Registration.this, MainActivity.class);
                                        startActivity(moveToLogin);
                                        Toast.makeText(Registration.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Registration.this, "Failed to Registersssss", Toast.LENGTH_SHORT).show();

                                    }

                                });
                            } else {
                                Toast.makeText(Registration.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
        else {
            Toast.makeText(Registration.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
        }

    }
}