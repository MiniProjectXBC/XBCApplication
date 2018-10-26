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
import xbc.miniproject.com.xbcapplication.AddIdleNewsActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.IdleNewsListAdapter;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;
import xbc.miniproject.com.xbcapplication.model.idleNews.ModelIdleNews;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;

public class IdleNewsFragment extends Fragment {
    private EditText idleNewsEditTextSearch;
    private Button idleNewsButtonInsert;
    private RecyclerView idleNewsRecyclerViewList;

    private List<IdleNewsList> listIdleNews = new ArrayList<>();
    private IdleNewsListAdapter idleNewsListAdapter;

    private RequestAPIServices apiServices;

    public IdleNewsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_idle_news, container, false);

        getDataFromAPI();

        idleNewsRecyclerViewList = (RecyclerView) view.findViewById(R.id.idleNewsRecyclerViewList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL, false);
        idleNewsRecyclerViewList.setLayoutManager(layoutManager);

        idleNewsEditTextSearch = (EditText) view.findViewById(R.id.idleNewsEditTextSearch);
        idleNewsRecyclerViewList.setVisibility(View.INVISIBLE);
        idleNewsEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (idleNewsEditTextSearch.getText().toString().trim().length() == 0) {
                    idleNewsRecyclerViewList.setVisibility(View.INVISIBLE);
                } else {
                    idleNewsRecyclerViewList.setVisibility(View.VISIBLE);
                    filter(editable.toString());
                }
            }
        });

        idleNewsButtonInsert = (Button) view.findViewById(R.id.idleNewsButtonInsert);
        idleNewsButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddIdleNewsActivity.class);
                startActivity(intent);
            }
        });

        tampilkanListIdleNews();


        return view;
    }

    private void getDataFromAPI() {
        apiServices = APIUtilities.getAPIServices();
        apiServices.getListIdleNews().enqueue(new Callback<ModelIdleNews>() {
            @Override
            public void onResponse(Call<ModelIdleNews> call, Response<ModelIdleNews> response) {
                if (response.code() == 200){
                    List<IdleNewsList> tmp = (List<IdleNewsList>) response.body().getDataList();
                    for (int i = 0; i<tmp.size();i++){
                        IdleNewsList data = tmp.get(i);
                        listIdleNews.add(data);
                    }
                } else{
                    Toast.makeText(getContext(), "Gagal Mendapatkan List Idle News: " + response.code() + " msg: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ModelIdleNews> call, Throwable t) {
                Toast.makeText(getContext(), "List Idle News onFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void filter(String text) {
        ArrayList<IdleNewsList> filteredList = new ArrayList<>();

        for (IdleNewsList item : listIdleNews) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        idleNewsListAdapter.filterList(filteredList);
    }

    private void tampilkanListIdleNews() {
//        addDummyList();
        if (idleNewsListAdapter == null) {
            idleNewsListAdapter = new IdleNewsListAdapter(getContext(), listIdleNews);
            idleNewsRecyclerViewList.setAdapter(idleNewsListAdapter);
        }
    }
}

//    private void addDummyList() {
//        int index = 1;
//        for (int i = 0; i < 5; i++) {
//            IdleNewsModel data = new IdleNewsModel();
//            data.setTitle("Dummy Title " + index);
//            data.setCategory("Dummy Category " + index);
//            listIdleNews.add(data);
//            index++;
//        }
//    }
//}
