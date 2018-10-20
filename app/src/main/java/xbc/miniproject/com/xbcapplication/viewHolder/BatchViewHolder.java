package xbc.miniproject.com.xbcapplication.viewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.BatchModel;

public class BatchViewHolder extends RecyclerView.ViewHolder {
    TextView listBatchTextViewTechnology,
            listBatchTextViewName,
            listBatchTextViewTrainer;

    ImageView listBatchButtonAction;



    public BatchViewHolder(@NonNull View itemView) {
        super(itemView);

        listBatchButtonAction = (ImageView) itemView.findViewById(R.id.listBatchButtonAction);
        listBatchTextViewName = (TextView) itemView.findViewById(R.id.listBatchTextViewName);
        listBatchTextViewTechnology = (TextView) itemView.findViewById(R.id.listBatchTextViewTechnology);
        listBatchTextViewTrainer = (TextView) itemView.findViewById(R.id.listBatchTextViewTrainer);
    }

    public  void setModel(BatchModel batchModel, final int position , final Context context){
        listBatchTextViewTechnology.setText(batchModel.getTechnology());
        listBatchTextViewName.setText(batchModel.getName());
        listBatchTextViewTrainer.setText(batchModel.getTrainer());

        listBatchTextViewTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
