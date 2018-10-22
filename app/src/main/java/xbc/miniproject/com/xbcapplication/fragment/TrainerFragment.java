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

import java.util.ArrayList;
import java.util.List;

import xbc.miniproject.com.xbcapplication.AddTechnologyActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.TechnologyListAdapter;
import xbc.miniproject.com.xbcapplication.adapter.TrainerListAdapter;
import xbc.miniproject.com.xbcapplication.dummyModel.TechnologyModel;
import xbc.miniproject.com.xbcapplication.dummyModel.TrainerModel;

public class TrainerFragment extends Fragment {

    private EditText trainerEditTextSearch;
    private Button trainerButtonInsert;
    private RecyclerView trainerRecyclerViewList;
    private List<TrainerModel> trainerModelList =  new ArrayList<>();
    private TrainerListAdapter trainerListAdapter;
    public TrainerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_trainer_layout, container, false);
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
                Intent intent = new Intent(getContext(), AddTechnologyActivity.class);
                startActivity(intent);
            }
        });


        tampilkanListTrainer();
        return  view;
    }
    public void filter(String text){
        ArrayList<TrainerModel> filteredList = new ArrayList<>();

        for(TrainerModel item: trainerModelList){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }

        }
        trainerListAdapter.filterList(filteredList);
    }
    public void tampilkanListTrainer(){
        addDummyList();
        if(trainerListAdapter==null){
            trainerListAdapter = new TrainerListAdapter(getContext(), trainerModelList);
            trainerRecyclerViewList.setAdapter(trainerListAdapter);
        }
    }
    public void addDummyList(){
        int index =1;
        for(int i=0; i<5; i++){
            TrainerModel data =  new TrainerModel();
            data.setName("Dummy Name"+index);
            trainerModelList.add(data);
            index++;
        }

    }
}
