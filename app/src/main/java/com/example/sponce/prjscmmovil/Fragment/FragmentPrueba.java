package com.example.sponce.prjscmmovil.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sponce.prjscmmovil.R;

import java.sql.SQLException;
import java.util.ArrayList;

import Adapter.AdapterNinos;
import BL.NinoBL;
import Entidades.CCMNino;
import Entidades.Nino;
import BL.CCMNinoBL;
import com.example.sponce.prjscmmovil.R;


public class FragmentPrueba extends Fragment {


    public FragmentPrueba() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prueba, container, false);
    }




}
