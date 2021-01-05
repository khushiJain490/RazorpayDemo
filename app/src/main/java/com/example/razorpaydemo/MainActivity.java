package com.example.razorpaydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    String TAG= "payment error";
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());

        pay = findViewById(R.id.payButton);

        /**
         * Preload payment resources
         */
        Checkout.preload(getApplicationContext());

        pay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startPayment();

            }
        });
    }

    public void startPayment() {

        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        checkout.setKeyID("rzp_test_YZ6Vg6SA6c3Zab");


        /**
         * Set your logo here
         */
       // checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Merchant Name");
            options.put("description", "test order");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");

            //here amount is passed in paise. for eg if we want Rs 5 we have to write 500 in "value"
            options.put("amount", "10000");//pass amount in currency subunits
            options.put("prefill.email", "ron490jain@gmail.com");
            options.put("prefill.contact","7983981872");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Payment_ID");
//        builder.setMessage(s);
//        builder.show();
        Toast.makeText(this, "payment successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

    }
}