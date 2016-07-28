package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Rol;
import Util.SQLiteHelper;

public class RolDao {
    
    //region Other Methods
    
    //endregion
    
    //region Public Methods
    public Boolean getExisteRolById(Context context, String idRol) throws SQLException {
        
        Boolean flag = false;
        Rol rol = new Rol();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            
            String[] args = new String[]{idRol};

            Cursor c = myDataBase.query(Rol.TABLE_NAME, null, "IdRol=?", args, null, null, null);

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

    public Rol getRolById(Context context, String idRol) throws SQLException {
        
        Rol rol = new Rol();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            
            String[] args = new String[]{idRol};

            Cursor c = myDataBase.query(Rol.TABLE_NAME, null, "_id=?", args, null, null, null);
            

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                
                    rol.set_id(c.getInt(c.getColumnIndex("_id")));
                    rol.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    rol.setNomRol(String.valueOf(c.getString(c.getColumnIndex("NomRol"))));
                    rol.setDesRol(String.valueOf(c.getString(c.getColumnIndex("DesRol"))));
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
        return rol;
    }
        
    public Boolean getExisteRolByCustomer(Context context, String Parametro) throws SQLException {
        
        Boolean flag = false;
        Rol rol = new Rol();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            
            Cursor c = myDataBase.rawQuery("Select * from " + Rol.TABLE_NAME + " where " + Parametro + "",null);

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
    
    public Rol getRolByCustomer(Context context, String Parametro) throws SQLException {
        
        Rol rol = new Rol();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
                      
            Cursor c = myDataBase.rawQuery("Select * from " + Rol.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    
                    rol.set_id(c.getInt(c.getColumnIndex("_id")));
                    rol.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    rol.setNomRol(String.valueOf(c.getString(c.getColumnIndex("NomRol"))));
                    rol.setDesRol(String.valueOf(c.getString(c.getColumnIndex("DesRol"))));
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
        return rol;
    }
    
    public static List<Rol> getAllRolesList(Context context) throws SQLException {
        List<Rol> list = new ArrayList<Rol>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Rol.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Rol rol = new Rol();
                    
                    rol.set_id(c.getInt(c.getColumnIndex("_id")));
                    rol.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    rol.setNomRol(String.valueOf(c.getString(c.getColumnIndex("NomRol"))));
                    rol.setDesRol(String.valueOf(c.getString(c.getColumnIndex("DesRol"))));
    
                    list.add(rol);
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
    
        public static List<Rol> getAllRolesListCustom(Context context, String parametro) throws SQLException {
        List<Rol> list = new ArrayList<Rol>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Rol.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Rol rol = new Rol();
                    
                    rol.set_id(c.getInt(c.getColumnIndex("_id")));
                    rol.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    rol.setNomRol(String.valueOf(c.getString(c.getColumnIndex("NomRol"))));
                    rol.setDesRol(String.valueOf(c.getString(c.getColumnIndex("DesRol"))));
    
                    list.add(rol);
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
    
    public static ArrayList<Rol> getAllRolesArrayList(Context context) throws SQLException {
        
        ArrayList<Rol> list = new ArrayList<Rol>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Rol.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Rol rol = new Rol();
                    
                    rol.set_id(c.getInt(c.getColumnIndex("_id")));
                    rol.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    rol.setNomRol(String.valueOf(c.getString(c.getColumnIndex("NomRol"))));
                    rol.setDesRol(String.valueOf(c.getString(c.getColumnIndex("DesRol"))));
    
                    list.add(rol);
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
    
    public static ArrayList<Rol> getAllRolesArrayListCustom(Context context, String parametro, String orderBy) throws SQLException {
        ArrayList<Rol> list = new ArrayList<Rol>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Rol.TABLE_NAME + parametro,null);
            
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Rol rol = new Rol();
                    
                    rol.set_id(c.getInt(c.getColumnIndex("_id")));
                    rol.setIdRol(c.getInt(c.getColumnIndex("IdRol")));
                    rol.setNomRol(String.valueOf(c.getString(c.getColumnIndex("NomRol"))));
                    rol.setDesRol(String.valueOf(c.getString(c.getColumnIndex("DesRol"))));
    
                    list.add(rol);
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
    
    public static Cursor getAllRolesCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdRol", "IdRol", "NomRol", "DesRol"};
        
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
            c = myDataBase.rawQuery("Select * from " + Rol.TABLE_NAME + parametro,null);
            
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
        
    public int insertarRol(Context context, Rol rol) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(Rol.TABLE_NAME, null, RolCV(rol));
               

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }
    
    public int actualizarRol(Context context, Rol rol) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(Rol.TABLE_NAME, RolCV(rol), "_id = " + rol.getIdRol() , null);
             

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }
    
        public int eliminarRol(Context context, int IdRol) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.delete(Rol.TABLE_NAME, "_id = " + IdRol , null);
             

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }
         
    public ContentValues RolCV(Rol rol){
        ContentValues values = new ContentValues();
        
        //values.put("_id", 	String.valueOf(rol.get_id()));
        values.put("IdRol", 	String.valueOf(rol.getIdRol()));
        values.put("NomRol", 	String.valueOf(rol.getNomRol()));
        values.put("DesRol", 	String.valueOf(rol.getDesRol()));

        return values;
    }
    //endregion

}

