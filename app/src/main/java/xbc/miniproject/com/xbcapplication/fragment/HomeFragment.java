package xbc.miniproject.com.xbcapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.utility.SessionManager;

public class HomeFragment extends Fragment {
    TextView homeFragmentNamaUser;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeFragmentNamaUser = (TextView) view.findViewById(R.id.homeFragmentNamaUser);
        homeFragmentNamaUser.setText(SessionManager.getUsername(getContext()));

        return view;
    }
}
