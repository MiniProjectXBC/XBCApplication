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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xbc.miniproject.com.xbcapplication.AddAssignmentActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.AssignmentListAdapter;
import xbc.miniproject.com.xbcapplication.model.assignment.AssignmentList;
import xbc.miniproject.com.xbcapplication.model.assignment.ModelAssignment;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;

public class AssignmentFragment extends Fragment {
    private EditText assignmentEditTextSearch;
    private Button assignmentButtonInsert;
    private RecyclerView assignmentRecyclerViewList;

    private List<AssignmentList> listAssignment = new ArrayList<>();
    private AssignmentListAdapter assignmentListAdapter;

    private RequestAPIServices apiServices;

    public AssignmentFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);

        getDataFromAPI();

        assignmentButtonInsert = (Button) view.findViewById(R.id.assignmentButtonInsert);
        assignmentButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddAssignmentActivity.class);
                startActivity(intent);
            }
        });

        assignmentRecyclerViewList = (RecyclerView) view.findViewById(R.id.assignmentRecyclerViewList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        assignmentRecyclerViewList.setLayoutManager(layoutManager);

        assignmentEditTextSearch = (EditText) view.findViewById(R.id.assignmentEditTextSearch);
        assignmentRecyclerViewList.setVisibility(View.INVISIBLE);
        assignmentEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (assignmentEditTextSearch.getText().toString().trim().length() == 0){
                    assignmentRecyclerViewList.setVisibility(View.INVISIBLE);
                } else{
                    assignmentRecyclerViewList.setVisibility(View.VISIBLE);
                    filter(editable.toString());
                }
            }
        });
        tampilkanListAssignment();

        return view;
    }

    private void getDataFromAPI() {
        apiServices = APIUtilities.getAPIServices();
        apiServices.getListAssignment().enqueue(new Callback<ModelAssignment>() {
            @Override
            public void onResponse(Call<ModelAssignment> call, Response<ModelAssignment> response) {
                if (response.code() == 200){
                    List<AssignmentList> tmp = response.body().getAssignmentList();
                    for (int i = 0; i<tmp.size() ;i++){
                        AssignmentList data = tmp.get(i);
                        listAssignment.add(data);
                    }
                } else{
                    Toast.makeText(getContext(), "Gagal Mendapatkan List Biodata: " + response.code() + " msg: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ModelAssignment> call, Throwable t) {
                Toast.makeText(getContext(), "List Biodata onFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void tampilkanListAssignment() {
//        addDummyList();
        if (assignmentListAdapter == null) {
            assignmentListAdapter = new AssignmentListAdapter(getContext(), listAssignment);
            assignmentRecyclerViewList.setAdapter(assignmentListAdapter);
        }
    }

//    private void addDummyList() {
//        int index = 1;
//        for (int i = 0; i < 5; i++) {
//            AssignmentModel data = new AssignmentModel();
//            data.setName("Dummy Name " + index);
//            data.setStartDate("Start Date");
//            data.setEndDate("End Date");
//            listAssignment.add(data);
//            index++;
//        }
//    }

    private void filter(String text) {
        ArrayList<AssignmentList> filteredList = new ArrayList<>();
        for (AssignmentList item : listAssignment) {
            if (item.getBiodata().getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        assignmentListAdapter.filterList(filteredList);
    }
}
