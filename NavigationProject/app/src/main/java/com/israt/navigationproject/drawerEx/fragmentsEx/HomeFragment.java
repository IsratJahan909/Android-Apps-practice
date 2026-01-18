package com.israt.navigationproject.drawerEx.fragmentsEx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.israt.navigationproject.R;
import com.israt.navigationproject.drawerEx.DrawerExampleActivity;

public class HomeFragment extends Fragment {


        @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

            View view = inflater.inflate((R.layout.fragment_home, container, false);
        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etPhone = view.findViewById(R.id.etPhone);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnclickListener(View v -> showDataDialog());



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((DrawerExampleActivity) requireActivity()) .setToolbarTitle("Profile");
    }
}
