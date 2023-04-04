package com.example.tema_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tema_4.database.Center;
import com.google.android.material.textfield.TextInputEditText;

public class FormActivity extends AppCompatActivity {

    public static final String ADD_CENTER_KEY = "ADD_CENTER_KEY";
    private Button btnAdaugaCentrul;
    private TextInputEditText tietDenumire;
    private TextInputEditText tietAdresa;
    private TextInputEditText tietJudet;
    private TextInputEditText tietTelefon;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        btnAdaugaCentrul=findViewById(R.id.popa_alexandra_btn_adaugare);
        tietDenumire=findViewById(R.id.popa_alexandra_tiet_denumire);
        tietAdresa=findViewById(R.id.popa_alexandra_tiet_adresa);
        tietJudet=findViewById(R.id.popa_alexandra_tiet_judet);
        tietTelefon=findViewById(R.id.popa_alexandra_tiet_telefon);

        btnAdaugaCentrul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()){
                    Center center=buildCenter();
                    intent.putExtra(ADD_CENTER_KEY,center);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });

        intent=getIntent();
    }

    private Center buildCenter() {
        String denumire=tietDenumire.getText().toString();
        String adresa=tietAdresa.getText().toString();
        String judet=tietJudet.getText().toString();
        String telefon=tietTelefon.getText().toString();
        return new Center(denumire,adresa,judet,telefon);
    }

    private boolean isValid(){
        if(tietDenumire.getText()==null){
            Toast.makeText(getApplicationContext(),R.string.introduceti_denumirea,Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietAdresa.getText()==null){
            Toast.makeText(getApplicationContext(),R.string.introduceti_adresa,Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietJudet.getText()==null){
            Toast.makeText(getApplicationContext(),R.string.introduceti_judetul,Toast.LENGTH_LONG).show();
            return false;
        }
        if(tietTelefon.getText()==null||tietTelefon.getText().toString().trim().length()!=10){
            Toast.makeText(getApplicationContext(),R.string.introduceti_telefonul,Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}