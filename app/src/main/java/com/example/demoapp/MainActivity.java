package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText editTextNumberDecimal;
    TextView textViewDiscountPercentage;
    TextView textViewDiscountedPrice;


    final String TAG = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumberDecimal = findViewById(R.id.editTextTicketPrice);
        textViewDiscountPercentage = findViewById(R.id.textNumberDisPer);
        textViewDiscountedPrice = findViewById(R.id.textNumberDisPrice);

        findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumberDecimal.setText("");
                textViewDiscountedPrice.setText("");
                textViewDiscountPercentage.setText("");


            }
        });

        findViewById(R.id.discount5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndShowDiscount(5.0);
            }
        });

        findViewById(R.id.discount10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndShowDiscount(10.0);
            }
        });

        findViewById(R.id.discount15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndShowDiscount(15.0);
            }
        });

        findViewById(R.id.discount20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndShowDiscount(20.0);
            }
        });

        findViewById(R.id.discount50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndShowDiscount(50.0);
            }
        });
    }


        private void calculateAndShowDiscount(double discount) {
            String enteredAmount = editTextNumberDecimal.getText().toString();
            try {
                double amount = Double.valueOf(enteredAmount);
                double discountedPrice = (100 - discount) * amount / 100.0;
                textViewDiscountPercentage.setText(String.valueOf(discount) + "%");
                textViewDiscountedPrice.setText(String.valueOf(discountedPrice));
            } catch(NumberFormatException exception){
                Toast.makeText(MainActivity.this, "Enter a valid number !!", Toast.LENGTH_SHORT).show();
            }

        }
        }









