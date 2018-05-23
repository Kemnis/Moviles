package com.example.gamal.echkofriends.Fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gamal.echkofriends.Adapter.AdapterMensaje;
import com.example.gamal.echkofriends.Model.Mensaje;
import com.example.gamal.echkofriends.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gamal on 22/05/2018.
 */

public class Fragment_chat extends Fragment {
    ListView listView;
    List<Mensaje> listMensajes = new ArrayList<>();
    AdapterMensaje adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_chat,container,false);
        listView = (ListView)view.findViewById(R.id.list_chat);
        inflateViews();
        return view;
    }
    private void inflateViews()
    {
        if(adapter == null)
        {
             for(int i= 0;i<20;i++)
             {
                 listMensajes.add(
                         new Mensaje("Fulano de tal","00:00","Hola que hace","")
                 );
             }
             adapter = new AdapterMensaje(listMensajes,getContext());
             listView.setAdapter(adapter);
        }
        else
            adapter.notifyDataSetChanged();
    }
}


