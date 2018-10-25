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
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xbc.miniproject.com.xbcapplication.AddTechnologyActivity;
import xbc.miniproject.com.xbcapplication.AddTrainerActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.TechnologyListAdapter;
import xbc.miniproject.com.xbcapplication.adapter.TrainerListAdapter;
import xbc.miniproject.com.xbcapplication.dummyModel.TechnologyModel;
import xbc.miniproject.com.xbcapplication.dummyModel.TrainerModel;
import xbc.miniproject.com.xbcapplication.model.biodata.ModelBiodata;
import xbc.miniproject.com.xbcapplication.model.trainer.DataListTrainer;
import xbc.miniproject.com.xbcapplication.model.trainer.ModelTrainer;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;

public class TrainerFragment extends Fragment {

    private EditText trainerEditTextSearch;
    private Button trainerButtonInsert;
    private RecyclerView trainerRecyclerViewList;
    private List<DataListTrainer> trainerModelList =  new ArrayList<>();
    private TrainerListAdapter trainerListAdapter;
    private RequestAPIServices apiServices;
    public TrainerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_trainer_layout, container, false);
        getDataFromAPI();

        trainerRecyclerViewList = (RecyclerView)view.findViewById(R.id.trainerRecyclerViewList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL,
                false);
        trainerRecyclerViewList.setLayoutManager(layoutManager);

        trainerEditTextSearch =(EditText) view.findViewById(R.id.trainerEditTextSearch);
        trainerRecyclerViewList.setVisibility(view.INVISIBLE);
        trainerEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(trainerEditTextSearch.getText().toString().trim().length()==0){
                    trainerRecyclerViewList.setVisibility(view.INVISIBLE);
                }else{
                    trainerRecyclerViewList.setVisibility(view.VISIBLE);
                    filter(s.toString());
                }
            }
        });
        trainerButtonInsert = (Button) view.findViewById(R.id.trainerButtonInsert);
        trainerButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddTrainerActivity.class);
                startActivity(intent);
            }
        });


        tampilkanListTrainer();
        return  view;
    }

    private void getDataFromAPI() {
        apiServices = APIUtilities.getAPIServices();
        apiServices.getListTrainer().enqueue(new Callback<ModelTrainer>() {
            @Override
            public void onResponse(Call<ModelTrainer> call, Response<ModelTrainer> response) {
                if (response.code() == 200){
                    List<DataListTrainer> tmp = response.body().getDataList();
                    for (int i = 0; i<tmp.size();i++){
                        DataListTrainer data = tmp.get(i);
                        trainerModelList.add(data);
                    }
                } else{
                    Toast.makeText(getContext(), "Gagal Mendapatkan List Biodata: " + response.code() + " msg: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ModelTrainer> call, Throwable t) {
                Toast.makeText(getContext(), "List Biodata onFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void filter(String text){
        ArrayList<DataListTrainer> filteredList = new ArrayList<>();

        for(DataListTrainer item: trainerModelList){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }

        }
        trainerListAdapter.filterList(filteredList);
    }
    public void tampilkanListTrainer(){

        if(trainerListAdapter==null){
            trainerListAdapter = new TrainerListAdapter(getContext(), trainerModelList);
            trainerRecyclerViewList.setAdapter(trainerListAdapter);
        }
    }


    }

