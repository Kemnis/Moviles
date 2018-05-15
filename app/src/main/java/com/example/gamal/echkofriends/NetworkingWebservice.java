package com.example.gamal.echkofriends;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.util.Output;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gama on 14/05/2018.
 */

public class NetworkingWebservice extends AsyncTask<Object, Integer, Object> {
    static final String SERVER_PATH = "http://192.168.16.145/web_service.php";
    static final int TIMEOUT = 3000;

    Context m_Context;
    ProgressDialog m_ProgressDialog;

    public NetworkingWebservice(Context m_Context) {
        this.m_Context = m_Context;
    }

    protected void onPreExecute()
    {
        super.onPreExecute();
        m_ProgressDialog = new ProgressDialog(m_Context);
        m_ProgressDialog.setTitle("Cargando.");
        m_ProgressDialog.setMessage("Porfavor espere.");
        m_ProgressDialog.setCancelable(false);
        m_ProgressDialog.show();
    }

    protected Object doInBackground(Object... params)
    {
        String action = (String) params[0];
        if (action.equals("signup"))
        {
            User signupUser = signup((User) params[1]);
            //Llamamos a nuestro callback
            NetCallback netCallback = (NetCallback) params[2];
            netCallback.onWorkFinish(signupUser);
        } else if (action.equals("getImages"))
        {
            List<MyImage> imageList =getImages();
            //Llamamos a nuestro callback+
            NetCallback netcallback = (NetCallback) params[1];
            netcallback.onWorkFinish(imageList);
        }
        return null;
    }

    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        m_ProgressDialog.dismiss();
    }

    private User signup(User user)
    {
        String postParams = "&action=signup&userJson=" + user.toJSON();
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(SERVER_PATH);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(TIMEOUT);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setFixedLengthStreamingMode(postParams.getBytes().length);

            OutputStream out = new BufferedOutputStream(conn.getOutputStream());
            out.write(postParams.getBytes());
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();
            Log.w("RESPONSE CODE","" + responseCode);

            InputStream in = new BufferedInputStream(conn.getInputStream());
            String responseString = inputStreamToString(in);
            try{
                JSONObject jsonobject = new JSONObject(responseString);
                int errorCode = jsonobject.getInt("error_code");
                int insertId= jsonobject.getInt("id");
                user.setId(insertId);
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<MyImage> getImages(){
        List<MyImage> imagesList = new ArrayList<>();
        String postParams = "&action=getImages";
        String response ="";
        HttpURLConnection conn = null;
        URL url=null;
        try{
            url = new URL(SERVER_PATH);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(TIMEOUT);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setFixedLengthStreamingMode(postParams.getBytes().length);

            OutputStream out = new BufferedOutputStream(conn.getOutputStream());
            out.write(postParams.getBytes());
            out.flush();
            out.close();
            int responseCode = conn.getResponseCode();
            Log.w("RESPONSE CODE", "" + responseCode);

            InputStream in = new BufferedInputStream(conn.getInputStream());
            String jsonResponse = inputStreamToString(in);
            try {
                JSONArray jsonArray = new JSONArray(jsonResponse);
                for (int i = 0; i<jsonArray.length(); i++)
                {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String name = object.getString("image_name");
                    String path = object.getString("image_path");
                    imagesList.add(new MyImage(name, path));
                }
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        return imagesList;
    }

    private String inputStreamToString(InputStream is){
        String rLine = "";
        StringBuilder response = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            while((rLine = rd.readLine()) != null)
            {
                response.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
