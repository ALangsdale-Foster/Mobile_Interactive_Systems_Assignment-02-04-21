package com.example.mis_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class ExchangeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> conversion = new HashMap<>();
    CollectionReference conversionRef = db.collection("conversions");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> stringAdapt = ArrayAdapter.createFromResource(this, R.array.string_array, android.R.layout.simple_spinner_item);
        stringAdapt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(stringAdapt);
        spinner2.setAdapter(stringAdapt);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    String url = "https://api.exchangeratesapi.io/latest?base="; //api url rates from european central bank : https://exchangeratesapi.io/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //unsure what purpose this second abstract definition serves, but app throws error if not here
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //unsure what purpose this second abstract definition serves, but app throws error if not here
    }

    public void constructURL(View view) {
        TextView label = (TextView)findViewById(R.id.textView);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        String baseCurr, convCurr; //no android studio, "convCurr" is not a typo, it's a shorthand for convertedCurrency
        baseCurr = spinner.getSelectedItem().toString();
        convCurr = spinner2.getSelectedItem().toString();
        url = "https://api.exchangeratesapi.io/latest?base=" + baseCurr + "&symbols=" + convCurr;
        //label.setText(url);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        EditText textbox1 = (EditText)findViewById(R.id.editText);
                        TextView label = (TextView)findViewById(R.id.textView);
                        String shortResponse = response.substring(16,20); //isolate the actual value returned to 2 decimal places
                        String baseCurr, convCurr; //no android studio, "convCurr" is not a typo, it's a shorthand for convertedCurrency
                        Spinner spinner = (Spinner)findViewById(R.id.spinner);
                        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
                        baseCurr = spinner.getSelectedItem().toString();
                        convCurr = spinner2.getSelectedItem().toString();

                        if (textbox1.getText().toString().isEmpty()) {
                            String output = 1 + " " + baseCurr + " = " + shortResponse + " " + convCurr;
                            Toast toast = Toast.makeText(getApplicationContext(),  output, Toast.LENGTH_SHORT);
                            toast.show(); //empty textbox = return 1 base converted to new curr

                            conversion.put("base", baseCurr);
                            conversion.put("new", convCurr);
                            conversion.put ("rate", shortResponse);
                            conversionRef.document(baseCurr + ":" + convCurr).set(conversion);
                        }
                        else if (isNumeric(textbox1.getText().toString())) {
                            double newResponse = Double.parseDouble(shortResponse);
                            double newVal = newResponse * Double.parseDouble(textbox1.getText().toString());
                            String val = textbox1.getText().toString();
                            DecimalFormat dec = new DecimalFormat("####.##");
                            String output = val + " " + baseCurr + " = " + dec.format(newVal) + " " + convCurr;
                            Toast toast = Toast.makeText(getApplicationContext(),  output, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(),  "Please Enter A Valid Number", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView label = (TextView)findViewById(R.id.textView);
                label.setText("API Error: That didn't work!");
            }
        });
        queue.add(stringRequest);
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

