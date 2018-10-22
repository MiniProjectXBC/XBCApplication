package xbc.miniproject.com.xbcapplication.fragment;

import android.arch.lifecycle.Lifecycle;
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

import xbc.miniproject.com.xbcapplication.AddBatchActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.BatchListAdapter;
import xbc.miniproject.com.xbcapplication.dummyModel.BatchModel;

public class BatchFragment extends Fragment {

    private EditText batchEditTextSearch;
    private Button batchButtonInsert;
    private RecyclerView batchRecyclerViewList;

    private List<BatchModel> listBatch = new ArrayList<>();
    private BatchListAdapter batchListAdapter;

    public BatchFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_batch, container, false);

        //Cara mendapatkan Context di Fragment dengan menggunakan getActivity() atau getContext()
        //Toast.makeText(getContext(),"Test Context Behasil", Toast.LENGTH_LONG).show();

        batchRecyclerViewList = (RecyclerView) view.findViewById(R.id.batchRecyclerViewList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL, false);
        batchRecyclerViewList.setLayoutManager(layoutManager);
        batchRecyclerViewList.setVisibility(View.INVISIBLE);

        batchEditTextSearch = (EditText) view.findViewById(R.id.batchEditTextSearch);
        batchRecyclerViewList.setVisibility(View.INVISIBLE);
        batchEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(batchEditTextSearch.getText().toString().trim().length() == 0) {
                    batchRecyclerViewList.setVisibility(View.INVISIBLE);
                } else{
                    batchRecyclerViewList.setVisibility(View.VISIBLE);
                    filter(s.toString());
                }
            }
        });

        batchButtonInsert = (Button) view.findViewById(R.id.batchButtonInsert);
        batchButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddBatchActivity.class);
                startActivity(intent);
                startActivity(intent);
            }
        });
        tampilkanListBatch();

        return view;

    }

    private void  filter(String text) {
        ArrayList<BatchModel> filteredList = new ArrayList<>();

        for (BatchModel item : listBatch) {
            if(item.getTechnology().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            } else if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        batchListAdapter.filterList(filteredList);
    }

    private void tampilkanListBatch(){
        addDummyList();
        if (batchListAdapter == null) {
            batchListAdapter = new BatchListAdapter(getContext(), listBatch);
            batchRecyclerViewList.setAdapter(batchListAdapter);
        }
    }

    private void addDummyList() {
        int index = 1;
        for (int i = 0; i < 5; i++) {
            BatchModel data = new BatchModel();
            data.setTechnology("Dummy Technology " + index);
            data.setName("Dummy Name " +index);
            data.setTrainer("Dummy Trainer");
            listBatch.add(data);
            index++;
        }
    }
}
