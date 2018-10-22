package xbc.miniproject.com.xbcapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.BiodataListAdapter;
import xbc.miniproject.com.xbcapplication.adapter.MonitoringListAdapter;
import xbc.miniproject.com.xbcapplication.dummyModel.BiodataModel;
import xbc.miniproject.com.xbcapplication.dummyModel.MonitoringModel;

public class MonitoringFragment extends Fragment {
    private EditText monitoringEditTextSearch;
    private Button monitoringButtonInsert;
    private RecyclerView monitoringRecyclerViewList;

    private List<MonitoringModel> listMonitoring = new ArrayList<>();
    private MonitoringListAdapter monitoringListAdapter;

    public MonitoringFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoring, container, false);

        monitoringButtonInsert = (Button) view.findViewById(R.id.monitoringButtonInsert);
        monitoringButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        monitoringRecyclerViewList = (RecyclerView) view.findViewById(R.id.monitoringRecyclerViewList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        monitoringRecyclerViewList.setLayoutManager(layoutManager);

        monitoringEditTextSearch = (EditText) view.findViewById(R.id.monitoringEditTextSearch);
        monitoringRecyclerViewList.setVisibility(View.INVISIBLE);
        monitoringEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (monitoringEditTextSearch.getText().toString().trim().length() == 0){
                    monitoringRecyclerViewList.setVisibility(View.INVISIBLE);
                } else{
                    monitoringRecyclerViewList.setVisibility(View.VISIBLE);
                    filter(s.toString());
                }
            }
        });

        tampilkanListBiodata();

        return view;
    }

    private void tampilkanListBiodata() {
        addDummyList();
        if (monitoringListAdapter == null) {
            monitoringListAdapter = new MonitoringListAdapter(getContext(), listMonitoring);
            monitoringRecyclerViewList.setAdapter(monitoringListAdapter);
        }
    }

    private void addDummyList() {
        int index = 1;
        for (int i = 0; i < 5; i++) {
            MonitoringModel data = new MonitoringModel();
            data.setName("Dummy Name " + index);
            data.setIdleDate("Dummy Idle Date");
            data.setPlacementDate("Dummy Placement Date");
            listMonitoring.add(data);
            index++;
        }
    }

    private void filter(String text) {
        ArrayList<MonitoringModel> filteredList = new ArrayList<>();

        for (MonitoringModel item : listMonitoring) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        monitoringListAdapter.filterList(filteredList);
    }
}
