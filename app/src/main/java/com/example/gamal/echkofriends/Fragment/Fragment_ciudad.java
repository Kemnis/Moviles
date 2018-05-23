package com.example.gamal.echkofriends.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gamal.echkofriends.Adapter.AdapterSong;
import com.example.gamal.echkofriends.Model.Song;
import com.example.gamal.echkofriends.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gamal on 22/05/2018.
 */

public class Fragment_ciudad extends Fragment {
    ListView listView;
    List<Song> list_Song = new ArrayList<>();
    AdapterSong adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_ciudad,container,false);

        listView = (ListView)view.findViewById(R.id.listView_ciudad);

        FloatingActionButton fabplay = (FloatingActionButton) view.findViewById(R.id.fabPlay);
        FloatingActionButton fabnext = (FloatingActionButton) view.findViewById(R.id.fabNext);
        FloatingActionButton fabprevious = (FloatingActionButton) view.findViewById(R.id.fabPrevious);

        fabprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fabplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fabnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        inflateViews();

        return view;
    }

    private void inflateViews()
    {
        if(adapter==null)
        {
            for(int i = 0;i<20;i++)
            {
                list_Song.add(
                        new Song("example","Genero Example","")
                );
            }

            adapter = new AdapterSong(list_Song,getContext());
            listView.setAdapter(adapter);
        }
        else
            adapter.notifyDataSetChanged();
    }
}
