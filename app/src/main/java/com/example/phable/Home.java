package com.example.phable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class Home extends AppCompatActivity {
    ArrayList<String> name;
    ArrayList<String> mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("AppData", 0);

        String user = pref.getString("name", null);
        String email = pref.getString("mail", null);

        name = (user == null || user.length() <= 2) ? new ArrayList<String>() : new ArrayList<String>(Arrays.asList((user.substring(1, user.length() - 1)).split(", ")));
        mail = (email == null || email.length() <= 2) ? new ArrayList<String>() : new ArrayList<String>(Arrays.asList((email.substring(1, email.length() - 1)).split(", ")));

        First first = new First(name, mail);

        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.replace(R.id.framelayout1, first);
        fragmentTransaction1.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("AppData", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", name.toString());
        editor.putString("mail", mail.toString());
        editor.apply();

        super.onBackPressed();
    }
}