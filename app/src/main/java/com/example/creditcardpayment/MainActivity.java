package com.example.creditcardpayment;

import com.example.creditcardpayment.validate.CreditCard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private TextInputEditText cardno,date;
    private TextInputEditText cvv,fname,lname;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvv=findViewById(R.id.cvv);
        cardno=findViewById(R.id.cardno);
        date=findViewById(R.id.date);
        cvv=findViewById(R.id.cvv);
        fname=findViewById(R.id.firstname);
        lname=findViewById(R.id.lastname);
        submit=findViewById(R.id.pay);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCard(v);

            }
        });



    }

    public void validateCard(View view) {
        clearAnyPreviousErrorMessages();
        String creditCardNumber = cardno.getText().toString();
        String expirationDate = date.getText().toString();
        String cvv2 = cvv.getText().toString();
        String firstName = fname.getText().toString();
        String lastName = lname.getText().toString();
        CreditCard n=new CreditCard(creditCardNumber,expirationDate,cvv2,firstName,lastName);


        if (!n.isValidCardNumber(creditCardNumber)) {
            cardno.setError("Invalid Card Number.");
            cardno.requestFocus();
        } else if (!n.isValidExpirationDate(expirationDate)) {
            date.setError("Invalid Expiration Date");
            date.requestFocus();
        } else if (!n.isValidCvv(creditCardNumber, cvv2)) {
            cvv.setError("Invalid cvv");
            cvv.requestFocus();
        } else if (firstName.isEmpty()) {
            fname.setError("Please enter First Name");
            fname.requestFocus();
        } else if (lastName.isEmpty()) {
            lname.setError("Please Enter Last Name");
            lname.requestFocus();
        } else {
            closeSoftKeyboard(view);
            CreditCard creditCard = new CreditCard(creditCardNumber, expirationDate, cvv2, firstName, lastName);
            alertDialog("Successful", null, "ok");
        }
    }


    private void clearAnyPreviousErrorMessages() {
        cardno.setError(null);
        date.setError(null);
        cvv.setError(null);
        fname.setError(null);
        lname.setError(null);
    }

    public void closeSoftKeyboard(View view) {
        // Don't have the soft keyboard taking up screen space after tapping the button
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void alertDialog(String title, String message, String buttonText) {
        new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonText, null)
                .show();
    }

}
