package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sponce.prjscmmovil.R;

import java.sql.SQLException;
import java.util.List;

import DAO.ComunidadDao;
import DAO.DepartamentoDao;
import DAO.MunicipioDao;
import Entidades.Comunidad;
import Entidades.Departamento;
import Entidades.Municipio;
import Entidades.Nino;

/**
 * Created by sponce on 29/7/2016.
 */
public class AdapterNinos extends RecyclerView.Adapter<AdapterNinos.AdaptadorViewHolder> {

    List<Nino> listNinos;

    ComunidadDao comunidadDao = new ComunidadDao();
    MunicipioDao municipioDao = new MunicipioDao();
    DepartamentoDao departamentoDao = new DepartamentoDao();

    Context context;

    public AdapterNinos(List<Nino> listNinos) {
        this.listNinos = listNinos;
    }

    @Override
    public AdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_ninos, parent, false);

        AdaptadorViewHolder holder = new AdaptadorViewHolder(v);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(AdaptadorViewHolder holder, int position) {

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
        //tvFechaNac.setText(nino.getFechaNac().getDate());
        holder.tvSexo.setText(nino.getSexo());
        holder.tvComunidad.setText(comunidad.getNomComunidad());
        holder.tvMunicipio.setText(municipio.getNomMunicipio());
        holder.tvDepartamento.setText(departamento.getNomDepartamento());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class AdaptadorViewHolder extends RecyclerView.ViewHolder
{

    TextView tvName;
    TextView tvMadre;
    TextView tvFechaNac;
    TextView tvSexo;
    TextView tvComunidad;
    TextView tvDepartamento;
    TextView tvMunicipio;


    public AdaptadorViewHolder (View itemView)
    {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.viewNomNino);
        TextView tvMadre = (TextView) itemView.findViewById(R.id.viewNomMadre);
        TextView tvFechaNac = (TextView) itemView.findViewById(R.id.viewFechaNac);
        TextView tvSexo = (TextView) itemView.findViewById(R.id.viewSexo);
        TextView tvComunidad = (TextView) itemView.findViewById(R.id.viewComunidad);
        TextView tvDepartamento = (TextView) itemView.findViewById(R.id.viewDepartamento);
        TextView tvMunicipio = (TextView) itemView.findViewById(R.id.viewMunicipio);

    }
}


}