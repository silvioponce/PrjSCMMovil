package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sponce.prjscmmovil.R;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DAO.ComunidadDao;
import DAO.DepartamentoDao;
import DAO.MunicipioDao;
import Entidades.Comunidad;
import Entidades.Departamento;
import Entidades.Municipio;
import Entidades.Nino;


public class AdapterNinos extends RecyclerView.Adapter<AdapterNinos.ViewHolder> {

    ArrayList<Nino> listNinos;

    ComunidadDao comunidadDao = new ComunidadDao();
    MunicipioDao municipioDao = new MunicipioDao();
    DepartamentoDao departamentoDao = new DepartamentoDao();

    Context context;

    public AdapterNinos(ArrayList<Nino> listNinos, OnItemClickListener escuchaClicksExterna) {
        this.listNinos = new ArrayList<>(listNinos);
        this.escuchaClicksExterna = escuchaClicksExterna;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item_ninos, parent, false);

        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Nino nino = listNinos.get(position);

        Comunidad comunidad = null;
        Municipio municipio = null;
        Departamento departamento = null;

        try {
            comunidad = comunidadDao.getComunidadById(context, String.valueOf(nino.getIdComunidad()));
            municipio = municipioDao.getMunicipioById(context, String.valueOf(comunidad.getIdMunicipio()));
            departamento = departamentoDao.getDepartamentoById(context, String.valueOf(municipio.getIdDepartamento()));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        holder.tvName.setText(nino.getNomNino());
        holder.tvMadre.setText(nino.getNomMadre());
        holder.tvFechaNac.setText(new SimpleDateFormat("dd-MM-yyyy").format(nino.getFechaNac()));
        holder.tvSexo.setText(nino.getSexo());
        holder.tvComunidad.setText(comunidad.getNomComunidad());
        holder.tvMunicipio.setText(municipio.getNomMunicipio());
        holder.tvDepartamento.setText(departamento.getNomDepartamento());

    }

    @Override
    public int getItemCount() {
        if (listNinos != null) {
            return listNinos.size() > 0 ? listNinos.size() : 0;
        } else {
            return 0;
        }
    }

    private String obtenerId(int posicion) {
        if (posicion != RecyclerView.NO_POSITION) {
            return String.valueOf(listNinos.get(posicion).get_id());
        } else {
            return null;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName;
        TextView tvMadre;
        TextView tvFechaNac;
        TextView tvSexo;
        TextView tvComunidad;
        TextView tvDepartamento;
        TextView tvMunicipio;


        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.viewNomNino);
            tvMadre = (TextView) itemView.findViewById(R.id.viewNomMadre);
            tvFechaNac = (TextView) itemView.findViewById(R.id.viewFechaNac);
            tvSexo = (TextView) itemView.findViewById(R.id.viewSexo);
            tvComunidad = (TextView) itemView.findViewById(R.id.viewComunidad);
            tvDepartamento = (TextView) itemView.findViewById(R.id.viewDepartamento);
            tvMunicipio = (TextView) itemView.findViewById(R.id.viewMunicipio);

            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            escuchaClicksExterna.onClick(this, obtenerId(getAdapterPosition()));
        }

    }



    public interface OnItemClickListener {
        public void onClick(ViewHolder viewHolder, String idArticulo);
    }

    private OnItemClickListener escuchaClicksExterna;


    public void animateTo(List<Nino> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Nino> newModels) {
        for (int i = listNinos.size() - 1; i >= 0; i--) {
            final Nino nino = listNinos.get(i);
            if (!newModels.contains(nino)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Nino> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Nino nino = newModels.get(i);
            if (!listNinos.contains(nino)) {
                addItem(i, nino);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Nino> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Nino nino = newModels.get(toPosition);
            final int fromPosition = listNinos.indexOf(nino);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Nino removeItem(int position) {
        final Nino nino = listNinos.remove(position);
        notifyItemRemoved(position);
        return nino;
    }

    public void addItem(int position, Nino nino) {
        listNinos.add(position, nino);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Nino nino = listNinos.remove(fromPosition);
        listNinos.add(toPosition, nino);
        notifyItemMoved(fromPosition, toPosition);
    }




}
