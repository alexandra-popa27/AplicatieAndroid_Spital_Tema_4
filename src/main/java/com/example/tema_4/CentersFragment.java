package com.example.tema_4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tema_4.database.Center;

import java.util.ArrayList;
import java.util.List;

public class CentersFragment extends Fragment {

    public static final String CENTRE_KEY = "CENTRE_KEY";
    private List<Center> centre;
    private ListView lvCentre;

    public CentersFragment() {
        // Required empty public constructor
    }

    public static CentersFragment newInstance(ArrayList<Center>centre) {
        CentersFragment fragment = new CentersFragment();
        Bundle args = new Bundle();
        args.putSerializable(CENTRE_KEY,centre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            centre= (List<Center>) getArguments().getSerializable(CENTRE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_centers, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        if(getContext()!=null){
            lvCentre=view.findViewById(R.id.popa_alexandra_listview_centers);
            //ArrayAdapter<Center> adapter=new ArrayAdapter<>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1,centre);
            CenterAdapter adapter=new CenterAdapter(getContext().getApplicationContext(),R.layout.lv_center_row_view,centre,getLayoutInflater());
            lvCentre.setAdapter(adapter);
        }
    }

    public void notifyAdapter() {
        ArrayAdapter<CenterAdapter> adapter= (ArrayAdapter<CenterAdapter>) lvCentre.getAdapter();
        adapter.notifyDataSetChanged();
    }
}