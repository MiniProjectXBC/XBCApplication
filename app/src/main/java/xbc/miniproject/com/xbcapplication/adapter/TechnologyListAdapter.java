package xbc.miniproject.com.xbcapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.TechnologyModel;
import xbc.miniproject.com.xbcapplication.viewHolder.TechnologyViewHolder;

public class TechnologyListAdapter extends RecyclerView.Adapter<TechnologyViewHolder> {
    private Context context;
    private List<TechnologyModel> technologyModelList;

    public TechnologyListAdapter(Context context, List<TechnologyModel> technologyModelList) {
        this.context = context;
        this.technologyModelList = technologyModelList;
    }

    @NonNull
    @Override
    public TechnologyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View customView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.custom_list_technology,
                viewGroup,
                false
        );
        return new TechnologyViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull TechnologyViewHolder technologyViewHolder, int position) {
        final TechnologyModel user= technologyModelList.get(position);
        technologyViewHolder.setModel(user, position, context);
    }

    @Override
    public int getItemCount() {
        if(technologyModelList!=null){
            return technologyModelList.size();
        }else {
            return 0;
        }
    }
    public  void filterList(List<TechnologyModel> filterList){
        technologyModelList = filterList;
        notifyDataSetChanged();
    }
}
