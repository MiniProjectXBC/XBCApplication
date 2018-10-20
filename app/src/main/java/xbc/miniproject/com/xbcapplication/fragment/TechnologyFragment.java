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
import xbc.miniproject.com.xbcapplication.adapter.TechnologyListAdapter;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.TechnologyModel;

public class TechnologyFragment extends Fragment {
    private EditText technologyEditTextSearch;
    private Button technologyButtonInsert;
    private RecyclerView technologyRecyclerViewList;
    private List<TechnologyModel> technologyModelList =  new ArrayList<>();
    private TechnologyListAdapter technologyListAdapter;
    public TechnologyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     final View view = inflater.inflate(R.layout.fragment_technology, container, false);
     technologyRecyclerViewList = (RecyclerView)view.findViewById(R.id.technologyRecyclerViewList);
     RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
             LinearLayout.VERTICAL,
             false);
     technologyRecyclerViewList.setLayoutManager(layoutManager);
     technologyEditTextSearch =(EditText) view.findViewById(R.id.technologyEditTextSearch);

     technologyEditTextSearch.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {
            if(technologyEditTextSearch.getText().toString().trim().length()==0){
                technologyRecyclerViewList.setVisibility(view.INVISIBLE);
            }else{
                technologyRecyclerViewList.setVisibility(view.VISIBLE);
                filter(s.toString());
            }
         }
     });
     technologyButtonInsert = (Button) view.findViewById(R.id.technologyButtonInsert);
     technologyButtonInsert.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(getContext(), AddTechnologyActivity.class);
             startActivity(intent);
         }
     });
        tampilkanListTechnology();
        return  view;
    }
    public void filter(String text){
        ArrayList<TechnologyModel> filteredList = new ArrayList<>();
        for(TechnologyModel item: technologyModelList){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }

        }
        technologyListAdapter.filterlist(filteredList);
    }
    public void tampilkanListTechnology(){
        addDummyList();
        if(technologyListAdapter==null){
            technologyListAdapter = new TechnologyListAdapter(getContext(), technologyModelList);
            technologyRecyclerViewList.setAdapter(technologyListAdapter);
        }
    }
    public void addDummyList(){
        int index =1;
        for(int i=0; i<5; i++){
            TechnologyModel data =  new TechnologyModel();
            data.setName("Dunny Name"+index);
            technologyModelList.add(data);
            index++;
        }

    }
}
