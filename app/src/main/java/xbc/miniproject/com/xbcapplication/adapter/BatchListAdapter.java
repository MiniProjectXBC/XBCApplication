package xbc.miniproject.com.xbcapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.model.batch.DataList;
import xbc.miniproject.com.xbcapplication.viewHolder.BatchViewHolder;

public class BatchListAdapter extends RecyclerView.Adapter<BatchViewHolder> {
    private Context context;
    private List<DataList> dataList;

    public BatchListAdapter(Context context, List<DataList> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public BatchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View customView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.custom_list_batch,
                viewGroup,
                false
        );
        return new BatchViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchViewHolder batchViewHolder, int position) {
        final DataList user = dataList.get(position);
        batchViewHolder.setModel(user, position,context);
    }

    @Override
    public int getItemCount() {

        if (dataList != null) {
            return dataList.size();
        } else {
            return 0;
        }
    }

    public  void filterList(List<DataList> filterList) {
        dataList = filterList;
        notifyDataSetChanged();
    }
}
