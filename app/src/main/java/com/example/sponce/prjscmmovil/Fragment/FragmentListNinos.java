package com.example.sponce.prjscmmovil.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

public class FragmentListNinos extends Fragment implements AdapterNinos.OnItemClickListener {


    ArrayList<Nino> listNinos;

    NinoBL ninoBL = new NinoBL();

    CCMNinoBL ccmNinoBL = new CCMNinoBL();

    public static FragmentListNinos crear() {
        return new FragmentListNinos();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Manejo de argumentos
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_ninos, container, false);

        View recyclerView = v.findViewById(R.id.reciclador);

        assert recyclerView != null;
        prepararLista((RecyclerView) recyclerView);

        return v;
    }

    private void prepararLista(@NonNull RecyclerView recyclerView) {

        try {
            listNinos = ninoBL.getAllNinosArrayList(getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(new AdapterNinos(listNinos, this));
    }


    @Override
    public void onClick(AdapterNinos.ViewHolder viewHolder, String idArticulo) {

    }
}
