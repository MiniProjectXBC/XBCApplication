package xbc.miniproject.com.xbcapplication.viewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.RequestBody;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.FeedbackModel;
import xbc.miniproject.com.xbcapplication.dummyModel.TrainerModel;
import xbc.miniproject.com.xbcapplication.model.feedback.getQuestion.DataListQuestionFeedback;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.utility.SessionManager;

public class FeedbackViewHolder extends RecyclerView.ViewHolder {
    private Context context;

    private TextView question;
    private EditText customListFeedback;
    public FeedbackViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        question = (TextView) itemView.findViewById(R.id.question);
        customListFeedback = (EditText) itemView.findViewById(R.id.customListFeedback);
    }

    public void setModel(DataListQuestionFeedback dataListQuestionFeedback, final int position, final Context context) {
        question.setText(dataListQuestionFeedback.getName());
        //customListFeedback.setText(dataListQuestionFeedback.getNotes().toString());
    }




}
