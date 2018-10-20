package xbc.miniproject.com.xbcapplication.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import xbc.miniproject.com.xbcapplication.adapter.BiodataListAdapter;
import xbc.miniproject.com.xbcapplication.dummyModel.BatchModel;

public class BatchFragment extends Fragment {

    private EditText batchEditTextSearch;
    private Button batchButtonInsert;
    private RecyclerView batchRecyclerViewList;

    private List<BatchModel> listBatch = new ArrayList<>();

}
