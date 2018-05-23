package com.example.gamal.echkofriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamal.echkofriends.Model.Mensaje;
import com.example.gamal.echkofriends.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gamal on 22/05/2018.
 */

public class AdapterMensaje extends BaseAdapter {

    List<Mensaje> list_mensajes;
    Context context;

    public AdapterMensaje(List<Mensaje> list_mensajes, Context context) {
        this.list_mensajes = list_mensajes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list_mensajes.size();
    }

    @Override
    public Object getItem(int position) {
        return list_mensajes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null)
        {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message,null);
        }

        TextView txtViewUser = (TextView)view.findViewById(R.id.txtViewUser);
        TextView txtViewTimeSend = (TextView)view.findViewById(R.id.txtvViewTimeSend);
        TextView txtViewMessage = (TextView)view.findViewById(R.id.txtViewMessage);
        ImageView imgViewImage = (ImageView)view.findViewById(R.id.imageViewImage);

        Mensaje message = list_mensajes.get(position);

        txtViewUser.setText(message.getName());
        txtViewMessage.setText(message.getMessage());
        txtViewTimeSend.setText(message.getTimeSend());
        //... picasso

        return view;
    }
}
