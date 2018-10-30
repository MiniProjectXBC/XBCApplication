package xbc.miniproject.com.xbcapplication.fragment;

import android.content.Intent;
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
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xbc.miniproject.com.xbcapplication.AddIdleMonitoringActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.BiodataListAdapter;
import xbc.miniproject.com.xbcapplication.adapter.MonitoringListAdapter;
import xbc.miniproject.com.xbcapplication.dummyModel.BiodataModel;
import xbc.miniproject.com.xbcapplication.dummyModel.MonitoringModel;
import xbc.miniproject.com.xbcapplication.model.biodata.BiodataList;
import xbc.miniproject.com.xbcapplication.model.monitoring.ModelMonitoring;
import xbc.miniproject.com.xbcapplication.model.monitoring.MonitoringData;
import xbc.miniproject.com.xbcapplication.model.monitoring.MonitoringDataList;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;

public class MonitoringFragment extends Fragment {
    private EditText monitoringEditTextSearch;
    private ImageView monitoringButtonInsert, monitoringButtonSearch;
    private RecyclerView monitoringRecyclerViewList;

    private List<MonitoringDataList> listMonitoring = new ArrayList<>();
    private MonitoringListAdapter monitoringListAdapter;

    RequestAPIServices apiServices;

    public MonitoringFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoring, container, false);

        getDataFromAPI();

        monitoringButtonInsert = (ImageView) view.findViewById(R.id.monitoringButtonInsert);
        monitoringButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddIdleMonitoringActivity.class);
                startActivity(intent);
            }
        });

        monitoringButtonSearch = (ImageView) view.findViewById(R.id.monitoringButtonSearch);
        monitoringButtonSearch.setOnClickListener(new View.OnClickListener() {
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

    private void getDataFromAPI() {
        apiServices = APIUtilities.getAPIServices();

        apiServices.getMonitoringList().enqueue(new Callback<ModelMonitoring>() {
            @Override
            public void onResponse(Call<ModelMonitoring> call, Response<ModelMonitoring> response) {
                List<MonitoringDataList> tmp = response.body().getMonitoringDataList();
                for (int i = 0; i<tmp.size();i++) {
                    MonitoringDataList data = tmp.get(i);
                    listMonitoring.add(data);
                }
            }

            @Override
            public void onFailure(Call<ModelMonitoring> call, Throwable t) {

            }
        });
    }

    private void tampilkanListBiodata() {
        if (monitoringListAdapter == null) {
            monitoringListAdapter = new MonitoringListAdapter(getContext(), listMonitoring);
            monitoringRecyclerViewList.setAdapter(monitoringListAdapter);
        }
    }

    private void filter(String text) {
        ArrayList<MonitoringDataList> filteredList = new ArrayList<>();

        for (MonitoringDataList item : listMonitoring) {
            if (item.getMonitoringBiodata().getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        monitoringListAdapter.filterList(filteredList);
    }
}
