package com.example.gamal.echkofriends;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;

public class LogIn extends AppCompatActivity {
    Button entrar;
    Button Register;
    TextView Username;
    TextView Password;
    CheckBox Session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        entrar = (Button) findViewById(R.id.btnLogIn);
        Register = (Button) findViewById(R.id.btnRegister);
        Username = (TextView) findViewById(R.id.txtUsername);
        Password = (TextView) findViewById(R.id.txtPassword);
        Session = (CheckBox) findViewById(R.id.chkSession);

        entrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent Signin = new Intent(LogIn.this, SignIn.class);
                startActivity(Signin);
            }
        });

    }

    public void login()
    {
        final String Usuario = Username.getText().toString();
        Intent Login = new Intent(LogIn.this, MainActivity.class);

        Login.putExtra("Nombre",Usuario);
        if(Usuario.isEmpty())
        {
            Toast.makeText(LogIn.this,"Faltaron Campos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Logea nuevoLogeo = new Logea();
            nuevoLogeo.execute(Usuario);
            startActivity(Login);
        }
    }
    class Logea extends AsyncTask<String,String,String>
    {
        ProgressDialog progressLogin;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressLogin = new ProgressDialog(LogIn.this);
            progressLogin.setTitle("Cargando.");
            progressLogin.setMessage("Porfavor espere.");
            progressLogin.setCancelable(false);
            progressLogin.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressLogin.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            String nombre="";
            while (nombre =="")
            {
                nombre = params[0];
            }
            return null;
        }

    }
}
