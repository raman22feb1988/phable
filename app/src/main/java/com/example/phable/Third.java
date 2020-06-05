package com.example.phable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Third extends RecyclerView.Adapter<Third.ViewHolder> {
    Context con;
    int _resource;

    ArrayList<String> name;
    ArrayList<String> mail;
    ArrayList<String> phone;

    View listItem;
    TextView t1;
    TextView t2;
    TextView t6;
    RecyclerView r1;

    public Third(Context context, int resource, ArrayList<String> name, ArrayList<String> mail, ArrayList<String> phone, RecyclerView r1) {
        // TODO Auto-generated constructor stub
        con = context;
        _resource = resource;

        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.r1 = r1;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.textview1);
            t2 = itemView.findViewById(R.id.textview2);
            t6 = itemView.findViewById(R.id.textview6);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        listItem = layoutInflater.inflate(_resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        t1.setText(name.get(position));
        t2.setText(mail.get(position));
        t6.setText(phone.get(position));

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                LayoutInflater layoutInflater = LayoutInflater.from(con);
                final View yourCustomView = layoutInflater.inflate(R.layout.detail, null);

                TextView t3 = yourCustomView.findViewById(R.id.textview3);
                TextView t4 = yourCustomView.findViewById(R.id.textview4);
                TextView t5 = yourCustomView.findViewById(R.id.textview5);

                t3.setText(name.get(position));
                t4.setText(mail.get(position));
                t5.setText(phone.get(position));

                AlertDialog dialog = new AlertDialog.Builder(con)
                        .setTitle("User Details")
                        .setView(yourCustomView)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                name.remove(position);
                                mail.remove(position);
                                phone.remove(position);

                                Third third = new Third(con, R.layout.third, name, mail, phone, r1);
                                r1.setAdapter(third);
                            }
                        })
                        .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Second second = new Second(name, mail, phone, position);

                                FragmentManager fragmentManager4 = ((AppCompatActivity) con).getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                                fragmentTransaction4.replace(R.id.framelayout1, second);
                                fragmentTransaction4.commit();
                            }
                        }).create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}