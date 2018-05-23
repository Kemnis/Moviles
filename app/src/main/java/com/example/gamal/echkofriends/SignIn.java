package com.example.gamal.echkofriends;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamal.echkofriends.API_Handler.API_Handler;
import com.github.polok.localify.LocalifyClient;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class SignIn extends AppCompatActivity {

    ImageView m_imageView;
    TextView Username;
    TextView Email;
    TextView Password;

    TextView RePassword;
    Button Register;
    Button Examinar;
    Button Salir;
    SignIn activty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        activty = this;
        m_imageView = (ImageView) findViewById(R.id.imgUserPhoto);
        Username = (TextView) findViewById(R.id.txtName);
        Email = (TextView) findViewById(R.id.txtEmail);
        Password = (TextView) findViewById(R.id.txtPass);
        RePassword = (TextView) findViewById(R.id.txtRepass);
        Register = (Button) findViewById(R.id.btnSignup);
        Examinar = (Button) findViewById(R.id.btnBrowse);
        Salir = (Button) findViewById(R.id.btnExit);
        /*
        Picasso.with(this)
                .load("http://downloadicons.net/sites/default/files/black-minimalist-musical-icon-78564.png")
                .placeholder(R.drawable.ic_menu_manage)
                .resize(200,200)
                .into(m_imageView);
        */

        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String Nombre = Username.getText().toString();
                String Correo = Email.getText().toString();
                String Contrasena = Password.getText().toString();
                if(Correo.isEmpty() || Contrasena.isEmpty()){
                    showToast("Faltaron campos");
                    return;
                }
                if(!isNetworkAvailable())
                {
                    showToast("No cuentas con conectividad");
                    return;
                }
                new NetworkingWebservice(SignIn.this).execute("signup", new User(Nombre, Correo, Contrasena), new NetCallback() {
                    @Override
                    public void onWorkFinish(Object data) {
                        final String signupUser = (String) data;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(signupUser != ""){
                                    showToast("Cuenta creada satisfactoriamente" + signupUser);
                                    startActivity(new Intent(SignIn.this,LogIn.class));
                                } else {
                                    showToast("Error al crear cuenta." + signupUser);
                                }
                            }
                        });
                    }
                });
            }
        });

        Examinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isStoragePermissionGranted())
                {
                    API_Handler.ImageSelect(activty);
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && API_Handler.PHOTO == requestCode)
        {
            Bitmap bitmap;
            bitmap = (Bitmap)data.getExtras().get("data");
            if(bitmap!=null)
                m_imageView.setImageBitmap(bitmap);

        } else if(resultCode == RESULT_OK && API_Handler.IMAGE_STORAGE == requestCode)
        {
            Uri uri = data.getData();
            String path = API_Handler.getRealPathFromURI_BelowAPI11(getApplicationContext(),uri);
            // Decode image size
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);

            if(bitmap!=null)
            {
                m_imageView.setImageBitmap(bitmap);
            }
        }



    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(),"Permiso permitido",Toast.LENGTH_LONG).show();
                return true;
            } else {

                Toast.makeText(getApplicationContext(),"Permiso denegado",Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Toast.makeText(getApplicationContext(),"Permiso permitido",Toast.LENGTH_LONG).show();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(getApplicationContext(),"Permiso establecido",Toast.LENGTH_LONG).show();
        }
    }

}
