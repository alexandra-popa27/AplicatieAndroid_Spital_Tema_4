package com.example.tema_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tema_4.database.Center;

import java.util.List;

public class ResultAdapter extends ArrayAdapter<RezultateTest> {

    private Context context;
    private int resource;
    private List<RezultateTest> rezultate;
    private LayoutInflater inflater;

    public ResultAdapter(@NonNull Context context, int resource, @NonNull List<RezultateTest> objects,LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.rezultate = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        RezultateTest result = rezultate.get(position);
        if (result == null) {
            return view;
        }
        addRezultatTest(view, result.getRezultat());
        addNumarAnticorpi(view, result.getNumarAnticorpi());
        addVaccinatTest(view,result.getVaccinat());
        addTipTest(view,result.getTipTestare());
        return view;
    }

    private void addTipTest(View view, String tipTestare) {
        TextView textView = view.findViewById(R.id.popa_alexandra_tv_tip_test);
        if (tipTestare != null && !tipTestare.trim().isEmpty()) {
            textView.setText(tipTestare);
        } else {
            textView.setText(R.string.default_value);
        }
    }

    private void addVaccinatTest(View view, String vaccinat) {
        TextView textView = view.findViewById(R.id.popa_alexandra_tv_este_vaccinat);
        if (vaccinat != null && !vaccinat.trim().isEmpty()) {
            textView.setText(vaccinat);
        } else {
            textView.setText(R.string.default_value);
        }
    }

    private void addNumarAnticorpi(View view, int numarAnticorpi) {
        TextView textView = view.findViewById(R.id.popa_alexandra_tv_numar_anticorpi);
        if (String.valueOf(numarAnticorpi) != null && !String.valueOf(numarAnticorpi).trim().isEmpty()) {
            textView.setText(String.valueOf(numarAnticorpi));
        } else {
            textView.setText(R.string.default_value);
        }
    }

    private void addRezultatTest(View view, String rezultat) {
        TextView textView = view.findViewById(R.id.popa_alexandra_tv_rezultat_test);
        if (rezultat != null && !rezultat.trim().isEmpty()) {
            textView.setText(rezultat);
        } else {
            textView.setText(R.string.default_value);
        }
    }


}
