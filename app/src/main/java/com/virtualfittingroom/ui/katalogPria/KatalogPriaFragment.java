package com.virtualfittingroom.ui.katalogPria;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.virtualfittingroom.R;

public class KatalogPriaFragment extends Fragment {

    private KatalogPriaViewModel mViewModel;

    public static KatalogPriaFragment newInstance() {
        return new KatalogPriaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_katalog_pria, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KatalogPriaViewModel.class);
        // TODO: Use the ViewModel
    }

}