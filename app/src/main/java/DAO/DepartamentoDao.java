package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Departamento;
import Util.SQLiteHelper;

public class DepartamentoDao {
    
    //region Other Methods
    
    //endregion
    
    //region Public Methods
    public Boolean getExisteDepartamentoById(Context context, String idDepartamento) throws SQLException {
        
        Boolean flag = false;
        Departamento departamento = new Departamento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            
            String[] args = new String[]{idDepartamento};

            Cursor c = myDataBase.query(Departamento.TABLE_NAME, null, "IdDepartamento=?", args, null, null, null);

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

    public Departamento getDepartamentoById(Context context, String idDepartamento) throws SQLException {
        
        Departamento departamento = new Departamento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            
            String[] args = new String[]{idDepartamento};

            Cursor c = myDataBase.query(Departamento.TABLE_NAME, null, "_id=?", args, null, null, null);
            

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                
                    departamento.set_id(c.getInt(c.getColumnIndex("_id")));
                    departamento.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));
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
        return departamento;
    }
        
    public Boolean getExisteDepartamentoByCustomer(Context context, String Parametro) throws SQLException {
        
        Boolean flag = false;
        Departamento departamento = new Departamento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            
            Cursor c = myDataBase.rawQuery("Select * from " + Departamento.TABLE_NAME + " where " + Parametro + "",null);

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
    
    public Departamento getDepartamentoByCustomer(Context context, String Parametro) throws SQLException {
        
        Departamento departamento = new Departamento();

        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
                      
            Cursor c = myDataBase.rawQuery("Select * from " + Departamento.TABLE_NAME + " where " + Parametro + "",null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    
                    departamento.set_id(c.getInt(c.getColumnIndex("_id")));
                    departamento.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));
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
        return departamento;
    }
    
    public static List<Departamento> getAllDepartamentosList(Context context) throws SQLException {
        List<Departamento> list = new ArrayList<Departamento>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Departamento.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Departamento departamento = new Departamento();
                    
                    departamento.set_id(c.getInt(c.getColumnIndex("_id")));
                    departamento.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));
    
                    list.add(departamento);
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
    
        public static List<Departamento> getAllDepartamentosListCustom(Context context, String parametro) throws SQLException {
        List<Departamento> list = new ArrayList<Departamento>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Departamento.TABLE_NAME + parametro,null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Departamento departamento = new Departamento();
                    
                    departamento.set_id(c.getInt(c.getColumnIndex("_id")));
                    departamento.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));
    
                    list.add(departamento);
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
    
    public static ArrayList<Departamento> getAllDepartamentosArrayList(Context context) throws SQLException {
        
        ArrayList<Departamento> list = new ArrayList<Departamento>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor c = myDataBase.query(Departamento.TABLE_NAME, null, null , null,
                    null, null, null);
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Departamento departamento = new Departamento();
                    
                    departamento.set_id(c.getInt(c.getColumnIndex("_id")));
                    departamento.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));
    
                    list.add(departamento);
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
    
    public static ArrayList<Departamento> getAllDepartamentosArrayListCustom(Context context, String parametro, String orderBy) throws SQLException {
        ArrayList<Departamento> list = new ArrayList<Departamento>();
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
            Cursor c = myDataBase.rawQuery("Select * from " + Departamento.TABLE_NAME + parametro,null);
            
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    Departamento departamento = new Departamento();
                    
                    departamento.set_id(c.getInt(c.getColumnIndex("_id")));
                    departamento.setIdDepartamento(c.getInt(c.getColumnIndex("IdDepartamento")));
                    departamento.setNomDepartamento(String.valueOf(c.getString(c.getColumnIndex("NomDepartamento"))));
    
                    list.add(departamento);
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
    
    public static Cursor getAllDepartamentosCursor(Context context, String parametro) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdDepartamento", "IdDepartamento", "NomDepartamento"};
        
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
            c = myDataBase.rawQuery("Select * from " + Departamento.TABLE_NAME + parametro,null);
            
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

    public static Cursor getAllDepartamentosCursor(Context context) throws SQLException {
        Cursor c = null;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);
        String[] columnas = new String[] {"_IdDepartamento", "IdDepartamento", "NomDepartamento"};


        try
        {
            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getReadableDatabase();
            c = myDataBase.rawQuery("Select * from " + Departamento.TABLE_NAME,null);

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
        
    public int insertarDepartamento(Context context, Departamento departamento) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {


            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int) myDataBase.insert(Departamento.TABLE_NAME, null, DepartamentoCV(departamento));
               

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }
    
    public int actualizarDepartamento(Context context, Departamento departamento) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.update(Departamento.TABLE_NAME, DepartamentoCV(departamento), "_id = " + departamento.getIdDepartamento() , null);
             

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }
    
        public int eliminarDepartamento(Context context, int IdDepartamento) throws SQLException {

        int result = 0;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = new SQLiteHelper(context);

        try {

            myDbHelper.openDataBase();
            myDataBase = myDbHelper.getWritableDatabase();

            result = (int)  myDataBase.delete(Departamento.TABLE_NAME, "_id = " + IdDepartamento , null);
             

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (myDataBase != null)
                myDataBase.close();
            myDbHelper.close();
        }
        return result;
    }
         
    public ContentValues DepartamentoCV(Departamento departamento){
        ContentValues values = new ContentValues();
        
        //values.put("_id", 	String.valueOf(departamento.get_id()));
        values.put("IdDepartamento", 	String.valueOf(departamento.getIdDepartamento()));
        values.put("NomDepartamento", 	String.valueOf(departamento.getNomDepartamento()));

        return values;
    }
    //endregion

}

