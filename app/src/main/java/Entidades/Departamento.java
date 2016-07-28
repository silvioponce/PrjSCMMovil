package Entidades;

public class Departamento {
    
    //region Variables Miembros
    private int _id;
    private int IdDepartamento;
    private String NomDepartamento;
    
    public static final String TABLE_NAME = "Departamentos";
    //endregion
    
    //region Constructors
    public Departamento() {
        
    }
    
    public Departamento(int _Id,  int idDepartamento, String nomDepartamento) {
        this._id = _Id;
        this.IdDepartamento = idDepartamento;
        this.NomDepartamento = nomDepartamento;
    }
    
    //endregion
    
    //region Public Properties
    public int get_id() {
        return _id;
    }

    public void set_id(int _Id) {
        _id = _Id;
    }

    public int getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        IdDepartamento = idDepartamento;
    }
    

    public String getNomDepartamento() {
        return NomDepartamento;
    }

    public void setNomDepartamento(String nomDepartamento) {
        NomDepartamento = nomDepartamento;
    }
    
    //endregion
}

