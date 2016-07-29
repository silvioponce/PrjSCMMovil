package com.example.sponce.prjscmmovil.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.sponce.prjscmmovil.R;

import java.sql.SQLException;
import java.util.ArrayList;

import Adapter.NinosAdapter;
import BL.CCMNinoBL;
import BL.NinoBL;
import Entidades.Nino;

public class FragmentListNinos extends Fragment implements View.OnClickListener {

    private NinoBL ninoBL = new NinoBL();
    private ListView lista;
    EditText txtBuscar;
    NinosAdapter adapter;
    ImageButton btnBuscar;

    private ActionMode mActionMode;
    public int selectedItem = -1;
    ArrayList<Nino> arrayOfNinos;

    public FragmentListNinos() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentListNinos newInstance(String param1, String param2) {
        FragmentListNinos fragment = new FragmentListNinos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_lista_ninos, container, false);
        lista = (ListView) v.findViewById(R.id.lstNinos);
        txtBuscar = (EditText) v.findViewById(R.id.txtBuscar);
        btnBuscar = (ImageButton) v.findViewById(R.id.btnBuscar);

        lista.setSelected(true);

        getActivity().setTitle("Buscar Nino(a)");


        arrayOfNinos = new ArrayList<Nino>();
        try {
            arrayOfNinos = ninoBL.getAllNinosArrayList(getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new NinosAdapter(getActivity(), arrayOfNinos);
        lista.setAdapter(adapter);

        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = adapter.getItem(position).get_id();
                view.setSelected(true);
                if (mActionMode != null) mActionMode.finish();


            }
        });


        btnBuscar.setOnClickListener(this);

        registerForContextMenu(lista);

        return v;
    }

    // TODO: Metodos particulares de cada Fragmento o Actividad


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
