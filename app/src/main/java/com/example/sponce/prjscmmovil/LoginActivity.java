package com.example.sponce.prjscmmovil;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import BL.AdminBL;
import BL.ComunidadBL;
import BL.DepartamentoBL;
import BL.MunicipioBL;
import BL.UsuarioBL;
import Entidades.Comunidad;
import Entidades.Departamento;
import Entidades.Municipio;
import Entidades.Usuario;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.


    EditText mUserView;
    EditText mPasswordView;

    private String mensaje;

    Usuario usuario = new Usuario();
    Comunidad comunidad = new Comunidad();
    ComunidadBL comunidadBL = new ComunidadBL();

    Municipio municipio = new Municipio();
    MunicipioBL municipioBL = new MunicipioBL();
    Departamento departamento;
    DepartamentoBL departamentoBL = new DepartamentoBL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        mUserView = (EditText) findViewById(R.id.user);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.btnLogin);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (verificaCampos()) {
                    login();
                }
            }
        });

    }

    private boolean login() {
        boolean flag = false;

        UsuarioBL usuarioBL = new UsuarioBL();
        ComunidadBL comunidadBL = new ComunidadBL();
        AdminBL adminBL = new AdminBL();

        try {
            if (adminBL.getCreateDB(getApplicationContext())) {
                String msg = null;
                usuario = usuarioBL.getVerificaUsuario(getApplicationContext(), mUserView.getText().toString(), mPasswordView.getText().toString());


                comunidad = comunidadBL.getComunidadById(getApplicationContext(), String.valueOf(usuario.getIdComunidad()));

                msg = comunidad.getNomComunidad();

                //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                //Toast.makeText(getApplicationContext(), "Conexion 2: " + executeCmd("ping -c 1 -w 1 google.com", false), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "Conexion 2: " + adminBL.isOnline(getApplicationContext()), Toast.LENGTH_SHORT).show();

                //validaUsuario();

                if (usuario.getIdUsuario() != 0) {

                    if (usuario.getEstado() != true) {
                        mensaje = "El usuario ha sido Deshabilitado";

                    }
                    startActivity(new Intent(this, MDIActivity.class));
                    GuardarPreferecias();


                } else {
                    mensaje = "Error al ingresar las Credenciales";
                }


            } else {
                mensaje = "Error al Crear la Base de Datos...";
            }

            if (!mensaje.isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Información");
                builder.setMessage(mensaje);
                builder.setPositiveButton("OK", null);
                builder.show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        flag = false;
        return flag;
    }

    public void GuardarPreferecias() {
        int IdMunicipio = 0;
        int IdDepartamento = 0;


        try {
            comunidad = comunidadBL.getComunidadById(getApplicationContext(), String.valueOf(usuario.getIdComunidad()));
            municipio = municipioBL.getMunicipioById(getApplicationContext(), String.valueOf(comunidad.getIdMunicipio()));

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


        SharedPreferences sharedPreferences = getSharedPreferences("PreferenciasUsuario", getApplication().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("IdUsuario", usuario.getIdUsuario());
        editor.putString("NomUsuario", usuario.getNomUsuario());
        editor.putInt("IdComunidad", usuario.getIdComunidad());

        editor.putInt("IdMunicipio", municipio.getIdMunicipio());
        editor.putInt("IdDepartamento", municipio.getIdDepartamento());

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private boolean verificaCampos() {

        // Reset errors.
        mUserView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String user = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean flag = true;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_user));
            focusView = mPasswordView;
            flag = false;
        }

        // Check for a valid user address.
        if (TextUtils.isEmpty(user)) {
            mUserView.setError(getString(R.string.error_field_required));
            focusView = mUserView;
            flag = false;
        } else if (!isUserValid(user)) {
            mUserView.setError(getString(R.string.error_invalid_user));
            focusView = mUserView;
            flag = false;
        }

        // Check for a valid user address.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            flag = false;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            flag = false;
        }

        if (!flag) {

            focusView.requestFocus();
        }

        return flag;
    }

    private boolean isUserValid(String email) {
        //TODO: Replace this with your own logic
        return email.length() > 4;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 2;
    }

}

