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

public class CenterAdapter extends ArrayAdapter<Center> {

    private Context context;
    private int resource;
    private List<Center> centre;
    private LayoutInflater inflater;

    public CenterAdapter(@NonNull Context context, int resource, @NonNull List<Center> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.centre = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Center center = centre.get(position);
        if (center == null) {
            return view;
        }
        addDenumireCentru(view, center.getDenumire());
        addAdresaCentru(view, center.getAdresa());
        addJudetCenter(view,center.getJudet());
        addTelefonCenter(view,center.getTelefon());
        return view;
    }

    private void addTelefonCenter(View view, String telefon) {
        TextView textView = view.findViewById(R.id.popa_alexandra_tv_telefon_spital);
        if (telefon != null && !telefon.trim().isEmpty()) {
            textView.setText(telefon);
        } else {
            textView.setText(R.string.default_value);
        }
    }

    private void addJudetCenter(View view, String judet) {
        TextView textView = view.findViewById(R.id.popa_alexandra_tv_judet_spital);
        if (judet != null && !judet.trim().isEmpty()) {
            textView.setText(judet);
        } else {
            textView.setText(R.string.default_value);
        }
    }

    private void addAdresaCentru(View view, String adresa) {
        TextView textView = view.findViewById(R.id.popa_alexandra_tv_adresa_spital);
        if (adresa != null && !adresa.trim().isEmpty()) {
            textView.setText(adresa);
        } else {
            textView.setText(R.string.default_value);
        }
    }

    private void addDenumireCentru(View view, String denumire) {
        TextView textView = view.findViewById(R.id.popa_alexandra_tv_denumire_spital);
        if (denumire != null && !denumire.trim().isEmpty()) {
            textView.setText(denumire);
        } else {
            textView.setText(R.string.default_value);
        }
    }
}
