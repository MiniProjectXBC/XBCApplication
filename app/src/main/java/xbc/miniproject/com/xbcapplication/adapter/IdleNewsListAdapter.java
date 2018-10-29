package xbc.miniproject.com.xbcapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;
import xbc.miniproject.com.xbcapplication.viewHolder.IdleNewsViewHolder;

public class IdleNewsListAdapter extends RecyclerView.Adapter<IdleNewsViewHolder> {
    private Context context;
    private List<IdleNewsList> idleNewsList;

    public IdleNewsListAdapter(Context context, List<IdleNewsList> idleNewsList) {
        this.context = context;
        this.idleNewsList = idleNewsList;
    }

    @NonNull
    @Override
    public IdleNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View customView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.custom_list_idle_news,
                viewGroup,
                false
        );
        return new IdleNewsViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull IdleNewsViewHolder idleNewsViewHolder, int i) {
        final IdleNewsList user = idleNewsList.get(i);
        idleNewsViewHolder.setModelIdle(user, i, context);
    }

    @Override
    public int getItemCount() {
        if (idleNewsList != null){
            return idleNewsList.size();
        }
        else {
            return 0;
        }
    }
    public void filterList(List<IdleNewsList> filterList) {
        idleNewsList = filterList;
        notifyDataSetChanged();
    }
}
