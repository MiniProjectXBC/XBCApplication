package xbc.miniproject.com.xbcapplication.viewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.BiodataModel;

public class BiodataViewHolder extends RecyclerView.ViewHolder {
    TextView listBiodataTextViewName,
            listBiodataTextViewMajors,
            listBiodataTextViewGpa;

    ImageView listBiodataButtonAction;

    public BiodataViewHolder(@NonNull View itemView) {
        super(itemView);

        listBiodataButtonAction = (ImageView) itemView.findViewById(R.id.listBiodataButtonAction);
        listBiodataTextViewName = (TextView) itemView.findViewById(R.id.listBiodataTextViewName);
        listBiodataTextViewMajors = (TextView) itemView.findViewById(R.id.listBiodataTextViewMajors);
        listBiodataTextViewGpa = (TextView) itemView.findViewById(R.id.listBiodataTextViewGpa);

    }

    public void setModel(BiodataModel biodataModel, final int position, final Context context){
        listBiodataTextViewName.setText(biodataModel.getName());
        listBiodataTextViewMajors.setText(biodataModel.getMajor());
        listBiodataTextViewGpa.setText(biodataModel.getGpa());

        listBiodataButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Anda Menekan Action Posisi: "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
