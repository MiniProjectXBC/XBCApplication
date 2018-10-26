package xbc.miniproject.com.xbcapplication.fragment;

import android.app.Activity;
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
import xbc.miniproject.com.xbcapplication.AddUserActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.UserListAdapter;
import xbc.miniproject.com.xbcapplication.dummyModel.UserModel;
import xbc.miniproject.com.xbcapplication.model.user.DataList;
import xbc.miniproject.com.xbcapplication.model.user.ModelUser;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;

public class UserFragment extends Fragment{
    private EditText userEditTextSearch;
    private Button userButtonInsert;
    private RecyclerView userRecyclerViewList;
    private List<DataList> userModelList =  new ArrayList<>();
    private UserListAdapter userListAdapter;
    private RequestAPIServices apiServices;
    public UserFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user, container, false);

        getDataFromApi();
        userRecyclerViewList = (RecyclerView)view.findViewById(R.id.userRecyclerViewList);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL,
                false);
        userRecyclerViewList.setLayoutManager(layoutManager);
        userEditTextSearch = (EditText) view.findViewById(R.id.userEditTextSearch);
        userRecyclerViewList.setVisibility(view.INVISIBLE);
        userEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(userEditTextSearch.getText().toString().trim().length()==0){
                    userRecyclerViewList.setVisibility(view.INVISIBLE);
                }else{
                    userRecyclerViewList.setVisibility(view.VISIBLE);
                    filter(s.toString());
                }
            }
        });
        userButtonInsert = (Button) view.findViewById(R.id.userButtonInsert);
        userButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddUserActivity.class);
                startActivity(intent);
            }
        });
        tampilkanListUser();
        return view;
    }
    private void getDataFromApi(){
        apiServices = APIUtilities.getAPIServices();
        apiServices.getListUsser().enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                if(response.code()==200){
                    List<DataList> tmp = response.body().getDataList();
                    for(int i=0; i<tmp.size(); i++){
                        DataList data = tmp.get(i);
                        userModelList.add(data);
                    }
                }else {
                    Toast.makeText(getContext(), "Gagal Mendapatkan List User : "+ response.code()+"msg: "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Toast.makeText(getContext(), "List User onFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void filter(String text){
        ArrayList<DataList> filteredList = new ArrayList<>();

        for(DataList item: userModelList){
            if(item.getUsername().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        userListAdapter.filterList(filteredList);
    }

    public void tampilkanListUser(){

        if(userListAdapter==null){
            userListAdapter =  new UserListAdapter(getContext(), userModelList);
            userRecyclerViewList.setAdapter(userListAdapter);
        }
    }
//    public void addDummyList(){
//        int index=1;
//        for (int i =0; i<5 ;i++){
//            ModelUser data = new ();
//            data.setUsername("Dummy Name"+ index);
//            data.setRole("Staff "+index);
//            data.setStatus("Active "+index);
//            userModelList.add(data);
//            index++;
//        }
//        int index2=1;
//        for (int i =0; i<5; i++){
//            UserModel data = new UserModel();
//            data.setUsername("Dummy2 Name"+ index2);
//            data.setRole("Admin "+index2);
//            data.setStatus("Active "+index2);
//            userModelList.add(data);
//            index2++;
//        }



}
