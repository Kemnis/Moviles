package com.example.gamal.echkofriends;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class LogIn extends AppCompatActivity {
    Button entrar;
    Button Register;
    TextView Email;
    TextView Password;
    CheckBox Session;
    LoginButton LB;
    LoginManager LM ;
    AccessToken accessToken;
    private static final String EMAIL = "email";

    CallbackManager cbm = CallbackManager.Factory.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        entrar = (Button) findViewById(R.id.btnLogIn);
        Register = (Button) findViewById(R.id.btnRegister);
        Session = (CheckBox) findViewById(R.id.chkSession);
        //Facebook
        LB =(LoginButton) findViewById(R.id.loginButton);
        LB.setReadPermissions(Arrays.asList(EMAIL));
        accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        // Callback registration
        LB.registerCallback(cbm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


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
        LM.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        cbm = CallbackManager.Factory.create();

        LM.getInstance().registerCallback(cbm,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

    }

    public void login()
    {
        Email = (TextView) findViewById(R.id.txtEmail);
        Password = (TextView) findViewById(R.id.txtPassword);
        String Correo = Email.getText().toString();
        String Contrasena = Password.getText().toString();
        Intent Login = new Intent(LogIn.this, MainActivity.class);

        if(Correo.isEmpty() || Contrasena.isEmpty()){
            showToast("Faltaron campos");
            return;
        }
        if(!isNetworkAvailable())
        {
            showToast("No cuentas con conectividad");
            return;
        }
        new NetworkingWebservice(LogIn.this).execute("log_in", new User(Correo, Contrasena), new NetCallback() {
            @Override
            public void onWorkFinish(Object data) {
                final String loginin = (String) data;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(loginin != ""){
                            showToast("Bienvenido " + loginin + "!");
                            startActivity(new Intent(LogIn.this,MainActivity.class));
                        } else {
                            showToast("Error al Logear:" + loginin);
                        }
                    }
                });
            }
        });
    }
    boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
    }
    void showToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }



}
