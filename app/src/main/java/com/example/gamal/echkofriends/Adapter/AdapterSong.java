package com.example.gamal.echkofriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gamal.echkofriends.Model.Song;
import com.example.gamal.echkofriends.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gamal on 22/05/2018.
 */

public class AdapterSong extends BaseAdapter {
    List<Song> list_songs;
    Context context;

    public AdapterSong(List<Song> list_songs, Context context) {
        this.list_songs = list_songs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list_songs.size();
    }

    @Override
    public Object getItem(int position) {
        return list_songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        Song song = list_songs.get(position);

        if(view == null)
        {
            view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_song,null);
        }
        TextView txtViewTitle = (TextView)view.findViewById(R.id.txtViewTitulo);
        TextView txtViewGenero = (TextView)view.findViewById(R.id.txtViewGenero);
        CircleImageView imageViewImageSong = (CircleImageView)view.findViewById(R.id.imgViewSong);

        txtViewTitle.setText(song.getName());
        txtViewGenero.setText(song.getGenero());
        //... Picasso endpoint Image address

        return view;
    }
}
