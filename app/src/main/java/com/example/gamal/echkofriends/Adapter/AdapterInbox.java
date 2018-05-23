package com.example.gamal.echkofriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gamal.echkofriends.R;
import com.example.gamal.echkofriends.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gamal on 22/05/2018.
 */

public class AdapterInbox extends BaseAdapter {

    List<User> listUsers;

    public AdapterInbox(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return listUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view  = convertView;

        if(view == null)
        {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_inbox,null);
        }

        TextView txtViewName = (TextView)view.findViewById(R.id.txtViewUser);
        CircleImageView imageViewImagePerfil = (CircleImageView)view.findViewById(R.id.imgViewUser);

        User user = listUsers.get(position);
        txtViewName.setText(user.getNombre());
        //...Picasso
        return view;
    }
}
