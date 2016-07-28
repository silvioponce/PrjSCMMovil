package Entidades;

public class Rol {
    
    //region Variables Miembros
    private int _id;
    private int IdRol;
    private String NomRol;
    private String DesRol;
    
    public static final String TABLE_NAME = "Roles";
    //endregion
    
    //region Constructors
    public Rol() {
        
    }
    
    public Rol(int _Id,  int idRol, String nomRol, String desRol) {
        this._id = _Id;
        this.IdRol = idRol;
        this.NomRol = nomRol;
        this.DesRol = desRol;
    }
    
    //endregion
    
    //region Public Properties
    public int get_id() {
        return _id;
    }

    public void set_id(int _Id) {
        _id = _Id;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int idRol) {
        IdRol = idRol;
    }
    

    public String getNomRol() {
        return NomRol;
    }

    public void setNomRol(String nomRol) {
        NomRol = nomRol;
    }
    

    public String getDesRol() {
        return DesRol;
    }

    public void setDesRol(String desRol) {
        DesRol = desRol;
    }
    
    //endregion
}

