package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Municipio;
import Util.SQLiteHelper;

public class MunicipioDao {
    
    //region Other Methods
    
    //endregion
    
    //region Public Methods
    public Boolean getExisteMunicipioById(Context context, String idMunicipio) throws SQLException {
        
        Boolean flag = false;
        Municipio municipio = new Municipio();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            
            String[] args = new String[]{idMunicipio};

            Cursor c = myDataBase.query(Municipio.TABLE_NAME, null, "IdMunicipio=?", args, null, null, null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    flag = true;
                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return flag;
    }

    public Municipio getMunicipioById(Context context, String idMunicipio) throws SQLException {
        
        Municipio municipio = new Municipio();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            
            String[] args = new String[]{idMunicipio};

            Cursor c = myDataBase.query(Municipio.TABLE_NAME, null, "_id=?", args, null, null, null);
            

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                
                    municipio.set_id(c.getInt(c.getColumnIndex("_id")));
                    municipio.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
                    c.moveToNext();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return municipio;
    }
        
    public Boolean getExisteMunicipioByCustomer(Context context, String Parametro) throws SQLException {
        
        Boolean flag = false;
        Municipio municipio = new Municipio();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            
            Cursor c = myDataBase.rawQuery("Select * from " + Municipio.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    flag = true;
                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return flag;
    }
    
    public Municipio getMunicipioByCustomer(Context context, String Parametro) throws SQLException {
        
        Municipio municipio = new Municipio();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
                      
            Cursor c = myDataBase.rawQuery("Select * from " + Municipio.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    
                    municipio.set_id(c.getInt(c.getColumnIndex("_id")));
                    municipio.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
                    c.moveToNext();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return municipio;
    }
    
    public static List<Municipio> getAllMunicipiosList(Context context) throws SQLException {
        List<Municipio> list = new ArrayList<Municipio>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Municipio.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Municipio municipio = new Municipio();
                    
                    municipio.set_id(c.getInt(c.getColumnIndex("_id")));
                    municipio.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
    
                    list.add(municipio);
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return list;
    }
    
        public static List<Municipio> getAllMunicipiosListCustom(Context context, String parametro) throws SQLException {
        List<Municipio> list = new ArrayList<Municipio>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        
        if (parametro!=null)
        {
            parametro = " where " + parametro;
        }
        else {
            parametro = "";
        }
        
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.rawQuery("Select * from " + Municipio.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Municipio municipio = new Municipio();
                    
                    municipio.set_id(c.getInt(c.getColumnIndex("_id")));
                    municipio.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
    
                    list.add(municipio);
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return list;
    }
    
    public static ArrayList<Municipio> getAllMunicipiosArrayList(Context context) throws SQLException {
        
        ArrayList<Municipio> list = new ArrayList<Municipio>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Municipio.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Municipio municipio = new Municipio();
                    
                    municipio.set_id(c.getInt(c.getColumnIndex("_id")));
                    municipio.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
    
                    list.add(municipio);
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return list;
    }
    
    public static ArrayList<Municipio> getAllMunicipiosArrayListCustom(Context context, String parametro, String orderBy) throws SQLException {
        ArrayList<Municipio> list = new ArrayList<Municipio>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        
        if (parametro!=null)
        {
            parametro = " where " + parametro;
        }
        else {
            parametro = "";
        }
        
        if (orderBy!=null)
        {
            orderBy = " Order By " + orderBy + " DESC" ;
        }
        else {
            orderBy = "";
        }
        
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.rawQuery("Select * from " + Municipio.TABLE_NAME + parametro,null);
            
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Municipio municipio = new Municipio();
                    
                    municipio.set_id(c.getInt(c.getColumnIndex("_id")));
                    municipio.setIdMunicipio(c.getInt(c.getColumnIndex("IdMunicipio")));
                    municipio.setNomMunicipio(String.valueOf(c.getString(c.getColumnIndex("NomMunicipio"))));
                    municipio.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
    
                    list.add(municipio);
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return list;
    }
    
    public static Cursor getAllMunicipiosCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdMunicipio", "IdMunicipio", "NomMunicipio", "IdDepartamento"};
        
        if (parametro!=null)
        {
            parametro = " where " + parametro;
        }
        else {
            parametro = "";
        }
        
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            c = myDataBase.rawQuery("Select * from " + Municipio.TABLE_NAME + parametro,null);
            
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    c.moveToNext();
                }
            } /*
		 *
		 * else { // throws exeptions }
		 */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return c;
    }
        
    public int insertarMunicipio(Context context, Municipio municipio) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(Municipio.TABLE_NAME, null, MunicipioCV(municipio));
               

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }
    
    public int actualizarMunicipio(Context context, Municipio municipio) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(Municipio.TABLE_NAME, MunicipioCV(municipio), "_id = " + municipio.getIdMunicipio() , null);
             

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }
    
        public int eliminarMunicipio(Context context, int IdMunicipio) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.delete(Municipio.TABLE_NAME, "_id = " + IdMunicipio , null);
             

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }
         
    public ContentValues MunicipioCV(Municipio municipio){
        ContentValues values = new ContentValues();
        
        //values.put("_id", 	String.valueOf(municipio.get_id()));
        values.put("IdMunicipio", 	String.valueOf(municipio.getIdMunicipio()));
        values.put("NomMunicipio", 	String.valueOf(municipio.getNomMunicipio()));
        values.put("IdDepartamento", 	String.valueOf(municipio.getIdDepartamento()));

        return values;
    }
    //endregion

}

