package v.abhijeet.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class PaymentPage extends AppCompatActivity {

    TextView expiry;
    private int mYear, mMonth, mDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        expiry = findViewById(R.id.Expiry);


        String finalroom = getIntent().getStringExtra("finalroom");

        TextView noroom = (TextView)findViewById(R.id.noofrooms);

        noroom.setText(finalroom);


        TextView paymentprice = (TextView)findViewById(R.id.paymentprice);

        int sum =getIntent().getExtras().getInt("key");
        paymentprice.setText(Integer.toString(sum));


        expiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(PaymentPage.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                expiry.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        mYear,
                        mMonth,
                        mDay);
                datePickerDialog.show();
            }
        });

    }
}