package com.example.tema_4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tema_4.async.AsyncTaskRunner;
import com.example.tema_4.async.Callback;
import com.example.tema_4.database.Center;
import com.example.tema_4.database.CenterService;
import com.example.tema_4.network.HttpManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private Fragment centersFragment;
    private Fragment resultsFragment;

    private Button btnAfiseazaCentrele;
    private Button btnAfiseazaRezultatele;
    private Button btnAdaugareCentre;
    private Button btnGrafic;

    List<Center> centre=new ArrayList<>();
    List<RezultateTest> rezultate=new ArrayList<>();

    private ActivityResultLauncher<Intent> addCenterLauncher;
    private CenterService centerService;
    private AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner();
    private static final String URL_REZULTATE="https://www.jsonkeeper.com/b/OT3N";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRezultateTesteFromNetwork();

        centerService=new CenterService(getApplicationContext());
        centerService.getAll(getAllCentreCallback());
        addCenterLauncher=registerAddCenterLauncher();

        btnAdaugareCentre=findViewById(R.id.popa_alexandra_btn_adaugare_centre);
        btnAdaugareCentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),FormActivity.class);
                addCenterLauncher.launch(intent);
            }
        });

        btnAfiseazaCentrele=findViewById(R.id.popa_alexandra_btn_afisare_centre);
        btnAfiseazaCentrele.setOnClickListener(openCentersFragment());

        btnAfiseazaRezultatele=findViewById(R.id.popa_alexandra_btn_afisare_rezultate);
        btnAfiseazaRezultatele.setOnClickListener(openResultsFragment());

        btnGrafic=findViewById(R.id.popa_alexandra_btn_grafic);
        btnGrafic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ChartActivity.class);
                intent.putExtra(ChartActivity.RESULT_KEY, (Serializable) rezultate);
                startActivity(intent);
            }
        });
    }

    //----- Preluare din JSON Rezultate -----

    private void getRezultateTesteFromNetwork(){
        Callable<String> asyncOperation=new HttpManager(URL_REZULTATE);
        Callback<String> mainThreadOperation=new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                rezultate.addAll(RezultateJsonParser.fromJson(result));
            }
        };
        asyncTaskRunner.executeAsync(asyncOperation,mainThreadOperation);
    }

    //----- Fragment Rezultate -----

    private View.OnClickListener openResultsFragment() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultsFragment=ResultsFragment.newInstance((ArrayList<RezultateTest>)rezultate);
                deschideFragmentRezultate();
            }
        };
    }

    private void deschideFragmentRezultate() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.popa_alexandra_list_fl,resultsFragment)
                .commit();
    }

    //----- Transmiterea datelor intre activitati si baze de date(pentru Centre)-----

    private Callback<List<Center>> getAllCentreCallback() {
        return new Callback<List<Center>>() {
            @Override
            public void runResultOnUiThread(List<Center> result) {
                if(result!=null){
                    centre.clear();
                    centre.addAll(result);
                }
            }
        };
    }

    private ActivityResultLauncher<Intent> registerAddCenterLauncher() {
        ActivityResultCallback<ActivityResult> callback=getAddCentruActivityResultCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),callback);
    }

    private ActivityResultCallback<ActivityResult> getAddCentruActivityResultCallback(){
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                    Center center= (Center) result.getData().getSerializableExtra(FormActivity.ADD_CENTER_KEY);
                    centerService.insert(center,insertCenterCallback());
                    if(centersFragment instanceof CentersFragment){
                        ((CentersFragment)centersFragment).notifyAdapter();
                    }
                }
            }
        };
    }

    private Callback<Center> insertCenterCallback() {
        return new Callback<Center>() {
            @Override
            public void runResultOnUiThread(Center center) {
                if(center!=null){
                    centre.add(center);
                }
            }
        };
    }

    //----- Fragment Centre -----

    private View.OnClickListener openCentersFragment() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centersFragment=CentersFragment.newInstance((ArrayList<Center>)centre);
                deschideFragment();
            }
        };
    }

    private void deschideFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.popa_alexandra_list_fl,centersFragment)
                .commit();
    }


}