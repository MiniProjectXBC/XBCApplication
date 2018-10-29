package xbc.miniproject.com.xbcapplication.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.adapter.FeedbackListAdapter;
import xbc.miniproject.com.xbcapplication.dummyModel.FeedbackModel;
import xbc.miniproject.com.xbcapplication.model.feedback.autoComplete.DataListAutocompleteFeedback;
import xbc.miniproject.com.xbcapplication.model.feedback.autoComplete.ModelAutocompleteFeedback;
import xbc.miniproject.com.xbcapplication.model.feedback.getQuestion.DataListQuestionFeedback;
import xbc.miniproject.com.xbcapplication.model.feedback.getQuestion.ModelQuestionFeedback;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;
import xbc.miniproject.com.xbcapplication.model.idleNews.ModelIdleNews;
import xbc.miniproject.com.xbcapplication.model.kelas.DataList;
import xbc.miniproject.com.xbcapplication.model.kelas.ModelClass;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;

public class FeedbackFragment extends Fragment  {
    private RecyclerView feedbackRecyclerView;
    private AutoCompleteTextView feedbackTextName;
    private Button feedbackButtonSave, feedbackButtonCancel;
    private FeedbackListAdapter feedbackListAdapter;

    private RequestAPIServices apiServices;

    private List<DataListQuestionFeedback> dataListQuestionFeedbacks = new ArrayList<>();


<<<<<<< HEAD


    private RequestAPIServices requestAPIServices;
=======
>>>>>>> parent of 72ecf8b... Merge branch 'master' of https://github.com/MiniProjectXBC/XBCApplication

    private List<DataListAutocompleteFeedback> feedbackModelList = new ArrayList<>();

    private boolean isTestSelected;
    private String[] test = {"Android", "Java"
    };


    public FeedbackFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        feedbackRecyclerView = (RecyclerView) view.findViewById(R.id.feedbackRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL,
                false);
        feedbackRecyclerView.setLayoutManager(layoutManager);
        feedbackRecyclerView.setVisibility(View.GONE);

        feedbackTextName = (AutoCompleteTextView) view.findViewById(R.id.feedbackTextName);
        tampil_auto_complete();


        feedbackButtonSave = (Button) view.findViewById(R.id.feedbackButtonSave);
        feedbackButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        feedbackButtonCancel = (Button) view.findViewById(R.id.feedbackButtonCancel);
        feedbackButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                getActivity().finish();
                feedbackRecyclerView.setVisibility(View.GONE);
                feedbackTextName.setText("");
                feedbackTextName.setError(null);
            }
        });

<<<<<<< HEAD


        //feedbackTextName = (AutoCompleteTextView) view.findViewById(R.id.feedbackTextName);

        //feedbackTextName = (AutoCompleteTextView) view.findViewById(R.id.feedbackTextName);

        feedbackTextName = (AutoCompleteTextView) view.findViewById(R.id.feedbackTextName);
=======

        //feedbackTextName = (AutoCompleteTextView) view.findViewById(R.id.feedbackTextName);
>>>>>>> parent of 72ecf8b... Merge branch 'master' of https://github.com/MiniProjectXBC/XBCApplication
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (getContext(), android.R.layout.select_dialog_item, test);
//        feedbackTextName.setThreshold(0);
//        feedbackTextName.setAdapter(adapter);


//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (getContext(), android.R.layout.select_dialog_item, test);
//        feedbackTextName.setThreshold(0);
//        feedbackTextName.setAdapter(adapter);
//
        feedbackTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feedbackTextName.getText().toString().trim().length() == 0) {
                    //feedbackTextName.showDropDown();
                }
            }
        });

        feedbackTextName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
<<<<<<< HEAD
=======

>>>>>>> parent of 72ecf8b... Merge branch 'master' of https://github.com/MiniProjectXBC/XBCApplication
                isTestSelected = true;
                feedbackTextName.setError(null);
                filter(feedbackTextName.getText().toString());
                feedbackRecyclerView.setVisibility(View.VISIBLE);

<<<<<<< HEAD
=======

>>>>>>> parent of 72ecf8b... Merge branch 'master' of https://github.com/MiniProjectXBC/XBCApplication
                //isTestSelected = true;
                //feedbackTextName.setError(null);
                //filter(feedbackTextName.getText().toString().trim());
                //feedbackRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        tampilkanListQuestion();



        feedbackTextName.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isTestSelected = false;
                feedbackTextName.setError("Test must from the list!");
                feedbackRecyclerView.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (feedbackTextName.getText().toString().trim().length() == 0) {
                    feedbackRecyclerView.setVisibility(View.GONE);
                }
            }
        });


<<<<<<< HEAD
=======

>>>>>>> parent of 72ecf8b... Merge branch 'master' of https://github.com/MiniProjectXBC/XBCApplication
        //tampilkanListQuestion();
        return view;

    }

    public void tampil_auto_complete(){
        String contentType = "aplication/json";
        String token = "MOGLK40NEYLUFKIORVFAFE5OCO60T4R140VTW35L9T72LRSRWKJIZXWTCD1HQKPZURKJPNYHIX0SO6SX672HASCKVAHPV6VHRXOKVV7KEQVZNETUBXRXM7CEKR5ZQJDA";
        apiServices = APIUtilities.getAPIServices();
        apiServices.roleautocomplete(contentType, token, "g").enqueue(new Callback<ModelAutocompleteFeedback>() {
            @Override
            public void onResponse(Call<ModelAutocompleteFeedback> call, Response<ModelAutocompleteFeedback> response) {
                if (response.code() == 200){
                    if (response.body().getMessage() != null){
                        List<String> str = new ArrayList<String>();
                        for (DataListAutocompleteFeedback s : response.body().getDataList()){
                            str.add(s.getId().toString());
                        }
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item,str.toArray(new String[0]));
                        feedbackTextName.setThreshold(1);
                        feedbackTextName.setAdapter(adapter);

                    }
                }

            }

            @Override
            public void onFailure(Call<ModelAutocompleteFeedback> call, Throwable t) {

            }
        });

    }

    private void inputValidation() {
        if (feedbackTextName.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity(), "Test Field still empty!", Toast.LENGTH_SHORT).show();
        } else {
            saveSuccesNotification();

        }
    }


    public void saveSuccesNotification() {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("NOTIFICATION !")
                .setMessage("Data Successfully Submitted!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }


    public void tampilkanListQuestion() {
        //addDummyList();


        apiServices = APIUtilities.getAPIServices();
        apiServices.getListQuestionFeedback().enqueue(new Callback<ModelQuestionFeedback>() {
            @Override
            public void onResponse(Call<ModelQuestionFeedback> call, Response<ModelQuestionFeedback> response) {
                if(response.code() == 200){
                    List<DataListQuestionFeedback> tmp = response.body().getDataList();
                    for (int i = 0; i<tmp.size(); i++){
                        DataListQuestionFeedback data = tmp.get(i);
                        dataListQuestionFeedbacks.add(data);
                    }
                } else {
                    Toast.makeText(getContext(),"Gagal Mendapatkan List Question: " + response.code() + " msg: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelQuestionFeedback> call, Throwable t) {
                Toast.makeText(getContext(), "List Question onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        if (feedbackListAdapter == null) {
            feedbackListAdapter = new FeedbackListAdapter(getContext(), dataListQuestionFeedbacks);
            feedbackRecyclerView.setAdapter(feedbackListAdapter);
        }

    }

    public void filter(String text) {
        ArrayList<DataListQuestionFeedback> filteredList = new ArrayList<>();

        for (DataListQuestionFeedback item : dataListQuestionFeedbacks) {
            if (item.getId().toString().contains(text.toLowerCase())) {
                filteredList.add(item);
            }

        }
        feedbackListAdapter.filterList(filteredList);
    }
//    public void tampilkanListQuestion() {
//        //addDummyList();
//        if (feedbackListAdapter == null) {
//            feedbackListAdapter = new FeedbackListAdapter(getContext(), feedbackModelList);
//            feedbackRecyclerView.setAdapter(feedbackListAdapter);
//        }
//        getListQuestionFeedback();
//    }
//
//    public void filter(String text) {
//        ArrayList<FeedbackModel> filteredList = new ArrayList<>();
//
//        for (FeedbackModel item : feedbackModelList) {
//            if (item.getTest().toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(item);
//            }
//
//        }
//        feedbackListAdapter.filterList(filteredList);
//    }





//    public void getListQuestionFeedback(){
//        apiServices = APIUtilities.getAPIServices();
//        apiServices.getListQuestionFeedback().enqueue(new Callback<ModelQuestionFeedback>() {
//            @Override
//            public void onResponse(Call<ModelQuestionFeedback> call, Response<ModelQuestionFeedback> response) {
//                if (response.code() == 200){
//                    List<DataListQuestionFeedback> tmp = response.body().getDataList();
//                    for (int i = 0; i<tmp.size();i++){
//                        DataListQuestionFeedback data = tmp.get(i);
//                        dataListQuestionFeedbacks.add(data);
//                    }
//                } else{
//                    Toast.makeText(getContext(), "Gagal Mendapatkan List Question: " + response.code() + " msg: " + response.message(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ModelQuestionFeedback> call, Throwable t) {
//                Toast.makeText(getContext(), "List Question onFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

//    public void addDummyList() {
//        int index = 1;
//        for (int i = 0; i < 5; i++) {
//            FeedbackModel data = new FeedbackModel();
//            data.setTest("Android");
//            data.setQuestion("Dummy Name" + index);
//            feedbackModelList.add(data);
//            index++;
//        }
//        for (int i = 0; i < 3; i++) {
//            FeedbackModel data = new FeedbackModel();
//            data.setTest("Java");
//            data.setQuestion("Dummy Name" + index);
//            feedbackModelList.add(data);
//            index++;
//        }
//
//    }
}
