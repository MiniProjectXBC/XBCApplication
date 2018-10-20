package xbc.miniproject.com.xbcapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.BiodataListAdapter;
import xbc.miniproject.com.xbcapplication.dummyModel.BiodataModel;

public class BiodataFragment extends Fragment {
    EditText biodataEditTextSearch;
    Button biodataButtonInsert;
    RecyclerView biodataRecyclerViewList;

    List<BiodataModel> listBiodata = new ArrayList<>();
    BiodataListAdapter biodataListAdapter;

    public BiodataFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_biodata, container, false);

        //Cara mendapatkan Context di Fragment dengan menggunakan getActivity() atau getContext()
        //Toast.makeText(getContext(),"Test Context Behasil", Toast.LENGTH_LONG).show();

        biodataEditTextSearch = (EditText) view.findViewById(R.id.biodataEditTextSearch);
        biodataButtonInsert = (Button) view.findViewById(R.id.biodataButtonInsert);
        biodataButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        biodataRecyclerViewList = (RecyclerView) view.findViewById(R.id.biodataRecyclerViewList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL, false);
        biodataRecyclerViewList.setLayoutManager(layoutManager);

        tampilkanListBiodata();



        return view;
    }

    private void tampilkanListBiodata() {
        addDummyList();
        if (biodataListAdapter == null) {
            biodataListAdapter = new BiodataListAdapter(getContext(),listBiodata);
            biodataRecyclerViewList.setAdapter(biodataListAdapter);
        }
    }

    private void addDummyList() {
        for (int i = 0; i < 5; i++) {
            BiodataModel data = new BiodataModel();
            data.setName("Dummy Name");
            data.setMajor("Dummy Major");
            data.setGpa("Dummy GPA");
            listBiodata.add(data);
        }
    }
}
