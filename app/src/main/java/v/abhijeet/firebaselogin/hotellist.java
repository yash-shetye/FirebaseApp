package v.abhijeet.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hotellist extends AppCompatActivity {

    TextView price1,price2,price3,price4;
    TextView hotel1,hotel2,hotel3,hotel4;
    Button book1,book2,book3,book4;

    DatabaseReference hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotellist);

        price1 = findViewById(R.id.price1);
        price2 = findViewById(R.id.price2);
        price3 = findViewById(R.id.price3);
        price4 = findViewById(R.id.price4);

        hotel1 =findViewById(R.id.hotelname1);
        hotel2 =findViewById(R.id.hotelname2);
        hotel3 =findViewById(R.id.hotelname3);
        hotel4 =findViewById(R.id.hotelname4);

        book1 = findViewById(R.id.hotelbtn1);
        book2 = findViewById(R.id.hotelbtn2);
        book3 = findViewById(R.id.hotelbtn3);
        book4 = findViewById(R.id.hotelbtn4);

        hotels = FirebaseDatabase.getInstance().getReference().child("Hotels");

        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hotelname = hotel1.getText().toString();
                String hotelprice = price1.getText().toString();

                Hotel hotel = new Hotel(hotelname,hotelprice);

                hotels.push().setValue(hotel);

                String price = price1.getText().toString();
                String value = hotel1.getText().toString();
                Intent intent = new Intent(hotellist.this, BookingPage.class);
                intent.putExtra("key", value);

                intent.putExtra("pricekey", price);

                intent.putExtra("resId", R.drawable.hotel2);
                startActivity(intent);


            }
        });

        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hotelname = hotel2.getText().toString();
                String hotelprice = price2.getText().toString();

                Hotel hotel = new Hotel(hotelname,hotelprice);

                hotels.push().setValue(hotel);

                String price = price2.getText().toString();

                String value = hotel2.getText().toString();
                Intent intent = new Intent(hotellist.this, BookingPage.class);
                intent.putExtra("key", value);

                intent.putExtra("pricekey", price);

                intent.putExtra("resId", R.drawable.hotel4);
                startActivity(intent);

            }
        });

        book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hotelname = hotel3.getText().toString();
                String hotelprice = price3.getText().toString();

                Hotel hotel = new Hotel(hotelname,hotelprice);

                hotels.push().setValue(hotel);


                String price = price3.getText().toString();

                String value = hotel3.getText().toString();
                Intent intent = new Intent(hotellist.this, BookingPage.class);
                intent.putExtra("key", value);

                intent.putExtra("pricekey", price);

                intent.putExtra("resId", R.drawable.hotel6);
                startActivity(intent);


            }
        });

        book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hotelname = hotel4.getText().toString();
                String hotelprice = price4.getText().toString();

                Hotel hotel = new Hotel(hotelname,hotelprice);

                hotels.push().setValue(hotel);


                String price = price4.getText().toString();
                String value = hotel4.getText().toString();


                Intent intent = new Intent(hotellist.this, BookingPage.class);
                intent.putExtra("key", value);

                intent.putExtra("pricekey", price);

                intent.putExtra("resId", R.drawable.hotel7);
                startActivity(intent);


            }
        });

    }


}