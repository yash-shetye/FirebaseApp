package v.abhijeet.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class BookingPage extends AppCompatActivity {

ImageView imgview;
     int count = 0;
    TextView fvalue;
    Button payment;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private int mYear, mMonth, mDay, mHour, mMinute;



    TextView checkin , checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        imgview = findViewById(R.id.hotelimage);

        fvalue = (TextView) findViewById(R.id.integer_number);
        payment = findViewById(R.id.buttonpay);

        checkin = findViewById(R.id.from);
        checkout = findViewById(R.id.To);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Hotels");
        userID = user.getUid();

        final TextView hotelnamee = (TextView)findViewById(R.id.hotelname);
        final TextView hotelpricee =(TextView)findViewById(R.id.pricebooking);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Hotel hotel = snapshot.getValue(Hotel.class);

                if(hotel != null)
                {
                    String hotelname = hotel.hotelname;

                    String hotelprice = hotel.hotelprice;



                    hotelnamee.setText(hotelname);
                    hotelpricee.setText(hotelprice);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(BookingPage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                checkin.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        mYear,
                        mMonth,
                        mDay);
                datePickerDialog.show();
            }

        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(BookingPage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                checkout.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        mYear,
                        mMonth,
                        mDay);
                datePickerDialog.show();
            }

        });



        String value = getIntent().getStringExtra("key");
        TextView tv = (TextView)findViewById(R.id.hotelname);
        tv.setText(value);
        String price = getIntent().getStringExtra("pricekey");

        TextView pricetv = (TextView)findViewById(R.id.pricebooking);
        pricetv.setText(price);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            imgview.setImageResource(resId);
        }
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num1 = Integer.parseInt(pricetv.getText().toString());
                int num2 = Integer.parseInt(fvalue.getText().toString());
                int sum = num1*num2;

                String fprice = pricetv.getText().toString();

                String froom = fvalue.getText().toString();

                Intent intent = new Intent(BookingPage.this, PaymentPage.class);
                intent.putExtra("finalprice", fprice);
                intent.putExtra("finalroom", froom);
                intent.putExtra("key",sum);

                startActivity(intent);
            }
        });


    }

    public void increaseInteger(View view) {
        count++;
        fvalue.setText(Integer.toString(count));
    }

    public void decreaseInteger(View view) {
        if(count <= 0) count = 0;
        count--;
        fvalue.setText(Integer.toString(count));
    }



}