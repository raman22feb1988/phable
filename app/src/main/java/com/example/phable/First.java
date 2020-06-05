package com.example.phable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class First extends Fragment {
    ArrayList<String> name;
    ArrayList<String> mail;
    ArrayList<String> phone;

    public First(ArrayList<String> name, ArrayList<String> mail, ArrayList<String> phone)
    {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.first, container, false);

        RecyclerView r1 = view1.findViewById(R.id.recyclerview1);

        r1.setHasFixedSize(true);
        r1.setLayoutManager(new LinearLayoutManager(getContext()));

        Third third = new Third(getContext(), R.layout.third, name, mail, phone, r1);
        r1.setAdapter(third);

        Button b1 = view1.findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Second second = new Second(name, mail, phone, -1);

                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.framelayout1, second);
                fragmentTransaction2.commit();
            }
        });

        return view1;
    }
}