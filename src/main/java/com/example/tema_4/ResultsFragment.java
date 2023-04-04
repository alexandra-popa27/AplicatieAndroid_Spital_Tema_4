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

public class ResultsFragment extends Fragment {

    public static final String RESULTS_KEY = "RESULTS_KEY";
    private List<RezultateTest> rezultate;
    private ListView lvRezultate;

    public ResultsFragment() {
        // Required empty public constructor
    }

    public static ResultsFragment newInstance(ArrayList<RezultateTest>rezultate) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putSerializable(RESULTS_KEY,rezultate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rezultate= (List<RezultateTest>) getArguments().getSerializable(RESULTS_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_results, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        lvRezultate=view.findViewById(R.id.popa_alexandra_listview_results);
        ResultAdapter adapter=new ResultAdapter(getContext().getApplicationContext(),R.layout.lv_result_row_view,rezultate,getLayoutInflater());
        lvRezultate.setAdapter(adapter);
    }

    public void notifyAdapter() {
        ArrayAdapter<ResultAdapter> adapter= (ArrayAdapter<ResultAdapter>) lvRezultate.getAdapter();
        adapter.notifyDataSetChanged();
    }
}