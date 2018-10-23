package xbc.miniproject.com.xbcapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.FeedbackModel;
import xbc.miniproject.com.xbcapplication.dummyModel.TechnologyModel;
import xbc.miniproject.com.xbcapplication.dummyModel.TrainerModel;
import xbc.miniproject.com.xbcapplication.viewHolder.FeedbackViewHolder;
import xbc.miniproject.com.xbcapplication.viewHolder.TechnologyViewHolder;
import xbc.miniproject.com.xbcapplication.viewHolder.TrainerViewHolder;

public class FeedbackListAdapter extends RecyclerView.Adapter<FeedbackViewHolder> {

    private Context context;
    private List<FeedbackModel> feedbackModelList;

    public FeedbackListAdapter(Context context, List<FeedbackModel> trainerModelList) {
        this.context = context;
        this.feedbackModelList = feedbackModelList;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View customView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.custom_list_feedback,
                viewGroup,
                false
        );
        return new FeedbackViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder feedbackViewHolder, int i) {
        final FeedbackModel user= feedbackModelList.get(i);
        feedbackViewHolder.setModel(user, i, context);


    }


    @Override
    public int getItemCount() {
        if(feedbackModelList!=null){
            return feedbackModelList.size();
        }else {
            return 0;
        }
    }

    public  void filterList(ArrayList<FeedbackModel> filterList){
        feedbackModelList = filterList;
        notifyDataSetChanged();
    }

}
