package Entidades;

public class Municipio {
    
    //region Variables Miembros
    private int _id;
    private int IdMunicipio;
    private String NomMunicipio;
    private int IdDepartamento;
    
    public static final String TABLE_NAME = "Municipios";
    //endregion
    
    //region Constructors
    public Municipio() {
        
    }
    
    public Municipio(int _Id,  int idMunicipio, String nomMunicipio, int idDepartamento) {
        this._id = _Id;
        this.IdMunicipio = idMunicipio;
        this.NomMunicipio = nomMunicipio;
        this.IdDepartamento = idDepartamento;
    }
    
    //endregion
    
    //region Public Properties
    public int get_id() {
        return _id;
    }

    public void set_id(int _Id) {
        _id = _Id;
    }

    public int getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        IdMunicipio = idMunicipio;
    }
    

    public String getNomMunicipio() {
        return NomMunicipio;
    }

    public void setNomMunicipio(String nomMunicipio) {
        NomMunicipio = nomMunicipio;
    }
    

    public int getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        IdDepartamento = idDepartamento;
    }
    
    //endregion
}

