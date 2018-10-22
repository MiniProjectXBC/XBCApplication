package xbc.miniproject.com.xbcapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.BiodataModel;
import xbc.miniproject.com.xbcapplication.dummyModel.ClassModel;
import xbc.miniproject.com.xbcapplication.viewHolder.BiodataViewHolder;
import xbc.miniproject.com.xbcapplication.viewHolder.ClassViewHolder;

public class ClassListAdapter extends RecyclerView.Adapter<ClassViewHolder> {
    private Context context;
    private List<ClassModel> classlist;

    public ClassListAdapter(Context context, List<ClassModel> classlist) {
        this.context = context;
        this.classlist = classlist;
    }

    @NonNull
    @Override

    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View customView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.custom_list_class,
                viewGroup,
                false
        );
        return new ClassViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder classViewHolder, int i) {
        final ClassModel user = classlist.get(i);
        classViewHolder.setModel(user, i, context);
    }

    @Override
    public int getItemCount() {
        if (classlist != null) {
            return classlist.size();
        } else {
            return 0;
        }
    }
    public void filterList(List<ClassModel> filterList) {
        classlist = filterList;
        notifyDataSetChanged();
    }
}
