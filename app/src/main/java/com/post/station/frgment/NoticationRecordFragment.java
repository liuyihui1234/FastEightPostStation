package com.post.station.frgment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.post.station.R;
import com.post.station.model.HomeModel;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public class NoticationRecordFragment extends Fragment {

    private HomeModel model = new HomeModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticationrecord, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
