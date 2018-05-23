package com.example.gamal.echkofriends.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gamal.echkofriends.API_Handler.API_Handler;
import com.example.gamal.echkofriends.Adapter.AdapterInbox;
import com.example.gamal.echkofriends.MainActivity;
import com.example.gamal.echkofriends.R;
import com.example.gamal.echkofriends.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gamal on 22/05/2018.
 */

public class Fragment_inbox extends Fragment {
    List<User> list_inbox = new ArrayList<>();
    ListView listView;
    AdapterInbox adapter;
    MainActivity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_inbox,container,false);

        listView = (ListView)view.findViewById(R.id.list_inbox);

        inflateViews();
        activity = (MainActivity) getActivity();
        EventsView();

        return  view;
    }

    private void inflateViews()
    {
        if(adapter == null)
        {
            for(int i = 0; i<20;i++)
            {
                User user = new User();
                user.setNombre("Fulano de tal");
                user.setEndpointImg("");
               list_inbox.add(user);
            }
            adapter = new AdapterInbox(list_inbox);
            listView.setAdapter(adapter);
        }
        else
            adapter.notifyDataSetChanged();
    }
    private void EventsView()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = list_inbox.get(position);
                API_Handler.setInbox_user(user);
                activity.ChangeFragmment(new Fragment_chat(),"CHAT");
                //Toast.makeText(getContext(),"Msuica",Toast.LENGTH_LONG).show();
            }
        });
    }

}
