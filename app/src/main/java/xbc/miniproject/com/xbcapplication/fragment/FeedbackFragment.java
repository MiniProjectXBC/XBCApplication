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
import android.widget.TextView;

import xbc.miniproject.com.xbcapplication.AddTechnologyActivity;
import xbc.miniproject.com.xbcapplication.R;

public class FeedbackFragment extends Fragment {
    private RecyclerView feedbackRecyclerView;
    private EditText feedbackEditTextTest;
    private Button feedbackButtonSave, feedbackButtonCancel;

    public FeedbackFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        feedbackRecyclerView = (RecyclerView)view.findViewById(R.id.feedbackRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL,
                false);
        feedbackRecyclerView.setLayoutManager(layoutManager);
        feedbackEditTextTest =(EditText) view.findViewById(R.id.trainerEditTextSearch);
        feedbackRecyclerView.setVisibility(view.INVISIBLE);

        feedbackButtonSave = (Button) view.findViewById(R.id.feedbackButtonSave);
        feedbackButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedbackFragment.class);
                startActivity(intent);
            }
        });

        feedbackButtonCancel = (Button) view.findViewById(R.id.feedbackButtonCancel);
        feedbackButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedbackFragment.class);
                startActivity(intent);
            }
        });


        return  view;

    }
}
