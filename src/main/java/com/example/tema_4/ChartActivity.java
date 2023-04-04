package com.example.tema_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {

    public static final String RESULT_KEY = "results";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<RezultateTest> rezultate = (List<RezultateTest>) getIntent().getSerializableExtra(RESULT_KEY);
        Map<String, Integer> source = calculateChartSource(rezultate);
        setContentView(new ChartView(getApplicationContext(), source));
    }

    private Map<String, Integer> calculateChartSource(List<RezultateTest> rezultate) {
        if (rezultate == null || rezultate.isEmpty()) {
            return new HashMap<>();
        }
        Map<String, Integer> source = new HashMap<>();
        for (RezultateTest rezultat : rezultate) {
            String key = rezultat.getRezultat();
            if (source.containsKey(key)) {
                Integer currentValue = source.get(key);
                source.put(key, currentValue + 1);
            } else {
                source.put(key, 1);
            }
        }
        return source;
    }
}