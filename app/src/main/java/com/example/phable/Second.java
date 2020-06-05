package com.example.phable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class Second extends Fragment {
    ArrayList<String> name;
    ArrayList<String> mail;
    ArrayList<String> phone;
    int index;

    public Second(ArrayList<String> name, ArrayList<String> mail, ArrayList<String> phone, int index)
    {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.second, container, false);

        final EditText e1 = view2.findViewById(R.id.edittext1);
        final EditText e2 = view2.findViewById(R.id.edittext2);
        final EditText e3 = view2.findViewById(R.id.edittext3);

        if(index != -1) {
            e1.setText(name.get(index));
            e2.setText(mail.get(index));
            e3.setText(phone.get(index));
        }

        Button b2 = view2.findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = (e1.getText()).toString();
                String email = (e2.getText()).toString();
                String contact = (e3.getText()).toString();

                if(index == -1) {
                    name.add(user);
                    mail.add(email);
                    phone.add(contact);
                } else {
                    name.set(index, user);
                    mail.set(index, email);
                    phone.set(index, contact);
                }

                First first = new First(name, mail, phone);

                FragmentManager fragmentManager3 = getFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.framelayout1, first);
                fragmentTransaction3.commit();
            }
        });

        return view2;
    }
}