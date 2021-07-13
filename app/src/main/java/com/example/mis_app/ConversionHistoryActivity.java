package com.example.mis_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConversionHistoryActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> conversion = new HashMap<>();
    CollectionReference conversionRef = db.collection("conversions");
    List<String> conversionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_history);
        db.collection("conversions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String baseCurrency = document.getId();
                                String rateValue = (String)document.get("rate");

                                conversionList.add(baseCurrency + " = " + rateValue);

                                String toastSTR = baseCurrency + " = " + rateValue;
                                //Toast toast = Toast.makeText(getApplicationContext(),  toastSTR, Toast.LENGTH_SHORT);
                                //toast.show();
                            }
                        }
                        TextView label = (TextView)findViewById(R.id.rate1Val);
                        int listSize = conversionList.size();
                        if (!conversionList.isEmpty()) {
                            label.setText(conversionList.get(listSize - 1).toString());
                        }
                        if (listSize >= 2) {
                            label = (TextView) findViewById(R.id.rate2Val);
                            label.setText(conversionList.get(listSize - 2).toString());
                        }
                        if (listSize >= 3) {
                            label = (TextView) findViewById(R.id.rate3Val);
                            label.setText(conversionList.get(listSize - 3).toString());
                        }
                        if (listSize >= 4) {
                            label = (TextView) findViewById(R.id.rate4Val);
                            label.setText(conversionList.get(listSize - 4).toString());
                        }
                        if (listSize >= 5) {
                            label = (TextView) findViewById(R.id.rate5Val);
                            label.setText(conversionList.get(listSize - 5).toString());
                        }
                        if (listSize >= 6) {
                            label = (TextView) findViewById(R.id.rate6Val);
                            label.setText(conversionList.get(listSize - 6).toString());
                        }
                        if (listSize >= 7) {
                            label = (TextView) findViewById(R.id.rate7Val);
                            label.setText(conversionList.get(listSize - 7).toString());
                        }
                        if (listSize >= 8) {
                            label = (TextView) findViewById(R.id.rate8Val);
                            label.setText(conversionList.get(listSize - 8).toString());
                        }
                        if (listSize >= 9) {
                            label = (TextView) findViewById(R.id.rate9Val);
                            label.setText(conversionList.get(listSize - 9).toString());
                        }
                        if (listSize >= 10) {
                            label = (TextView) findViewById(R.id.rate10Val);
                            label.setText(conversionList.get(listSize - 10).toString());
                        }
                    }
                });
    }
}


