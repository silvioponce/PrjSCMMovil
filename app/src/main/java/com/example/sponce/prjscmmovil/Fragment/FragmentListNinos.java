package com.example.sponce.prjscmmovil.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.sponce.prjscmmovil.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterNinos;
import BL.NinoBL;
import Entidades.Nino;
import BL.CCMNinoBL;

public class FragmentListNinos extends Fragment implements AdapterNinos.OnItemClickListener, View.OnClickListener, TextWatcher {

    private RecyclerView recyclerView;
    private AdapterNinos adapter;
    ArrayList<Nino> list;

    NinoBL ninoBL = new NinoBL();

    CCMNinoBL ccmNinoBL = new CCMNinoBL();

    EditText txtBuscar;
    ImageButton btnBuscar;

    private EscuchaFragmento escucha;

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

        recyclerView = (RecyclerView) v.findViewById(R.id.reciclador);

        txtBuscar = (EditText) v.findViewById(R.id.txtBuscar);
        btnBuscar = (ImageButton) v.findViewById(R.id.btnBuscar);

        assert recyclerView != null;
        prepararLista(recyclerView);


        btnBuscar.setOnClickListener(this);
        txtBuscar.addTextChangedListener(this);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void prepararLista(@NonNull RecyclerView recyclerView) {
        list = new ArrayList<>();
        try {
            list = ninoBL.getAllNinosArrayList(getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter = new AdapterNinos(list, this);
        recyclerView.setAdapter(adapter);
    }

    private List<Nino> filter(List<Nino> Ninos, String query) {
        query = query.toLowerCase();

        ArrayList<Nino> filteredCompanyList = new ArrayList<>();
        for (Nino client : Ninos) {
            final String textName = client.getNomNino().toLowerCase();
            if (textName.contains(query)) {
                filteredCompanyList.add(client);
            }
        }
        return filteredCompanyList;
    }

   @Override
    public void onClick(AdapterNinos.ViewHolder viewHolder, String idArticulo) {
      // cargarDetalle(idArticulo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuscar:
                String newText = txtBuscar.getText().toString();
                final List<Nino> filteredModelList = filter(list, newText);

                Log.v("App", newText + ", " + list.size() + ", " + filteredModelList.size());
                adapter.animateTo(filteredModelList);
                recyclerView.scrollToPosition(0);

            default:
                return;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String newText = txtBuscar.getText().toString();
        final List<Nino> filteredModelList = filter(list, newText);

        Log.v("App", newText + ", " + list.size() + ", " + filteredModelList.size());
        adapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EscuchaFragmento) {
            escucha = (EscuchaFragmento) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " debes implementar EscuchaFragmento");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        escucha = null;
    }

    public void cargarDetalle(String idArticulo) {
        if (escucha != null) {
            escucha.alSeleccionarItem(idArticulo);
        }
    }

    public interface EscuchaFragmento {
        void alSeleccionarItem(String idArticulo);
    }
}
