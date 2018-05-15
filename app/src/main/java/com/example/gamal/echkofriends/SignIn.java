package com.example.gamal.echkofriends;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class SignIn extends AppCompatActivity {

    ImageView m_imageView;
    TextView Username;
    TextView Email;
    TextView Password;

    TextView RePassword;
    Button Register;
    Button Examinar;
    Button Salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        m_imageView = (ImageView) findViewById(R.id.imgUserPhoto);
        Username = (TextView) findViewById(R.id.txtName);
        Email = (TextView) findViewById(R.id.txtEmail);
        Password = (TextView) findViewById(R.id.txtPass);
        RePassword = (TextView) findViewById(R.id.txtRepass);
        Register = (Button) findViewById(R.id.btnSignup);
        Examinar = (Button) findViewById(R.id.btnBrowse);
        Salir = (Button) findViewById(R.id.btnExit);
        Picasso.with(this)
                .load("http://downloadicons.net/sites/default/files/black-minimalist-musical-icon-78564.png")
                .placeholder(R.drawable.ic_menu_manage)
                .resize(200,200)
                .into(m_imageView);

        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String Nombre = Username.getText().toString();
                String Contraseña = Password.getText().toString();
                if(Nombre.isEmpty() || Contraseña.isEmpty()){
                    showToast("Faltaron campos");
                    return;
                }
                if(!isNetworkAvailable())
                {
                    showToast("No cuentas con conectividad");
                    return;
                }
                new NetworkingWebservice(SignIn.this).execute("signup", new User(Nombre, Contraseña), new NetCallback() {
                    @Override
                    public void onWorkFinish(Object data) {
                        final User signupUser = (User) data;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(signupUser.getId() != 0){
                                    showToast("Cuenta creada satisfactoriamente");
                                    startActivity(new Intent(SignIn.this,LogIn.class));
                                } else {
                                    showToast("Error al crear cuenta. Usuario ya existe");
                                }
                            }
                        });
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
