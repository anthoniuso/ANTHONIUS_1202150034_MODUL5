package com.levegra.anthonius_1202150034_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    private TextView warna;
    int idWarna;
    AlertDialog.Builder alert;
    SharedPreferences.Editor sharedP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");

        alert = new AlertDialog.Builder(this);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("prefer", 0);
        sharedP = preferences.edit();
        idWarna = preferences.getInt("colourPass", R.color.white);

        warna = findViewById(R.id.listColour);
        warna.setText(getListColour(idWarna));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(i);
        this.finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.onBackPressed();
        }
        return true;
    }

    public String getListColour(int integer) {
        if (integer == R.color.blue){
            return "Blue";
        }else if (integer == R.color.red){
            return "Red";
        }else if (integer == R.color.green){
            return "Green";
        }else{
            return "Default";
        }
    }

    public int getColourid(int integer) {
        if (integer == R.color.blue){
            return R.id.biru;
        }else if (integer == R.color.red){
            return R.id.merah;
        }else if (integer == R.color.green){
            return R.id.hijau;
        }else{
            return R.id.putih;
        }
    }

    public void pilihWarna(View view) {
        alert.setTitle("List Colour");
        View newView = getLayoutInflater().inflate(R.layout.colour_setting, null);
        alert.setView(newView);

        final RadioGroup groupRadio = newView.findViewById(R.id.colourRadioBtn);
        groupRadio.check(getColourid(idWarna));

        alert.setPositiveButton("Pilih", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int a = groupRadio.getCheckedRadioButtonId();
                switch (a) {
                    case R.id.hijau:
                        idWarna = R.color.green;
                        break;
                    case R.id.merah:
                        idWarna = R.color.red;
                        break;
                    case R.id.putih:
                        idWarna = R.color.white;
                        break;
                    case R.id.biru:
                        idWarna = R.color.blue;
                        break;
                }

                warna.setText(getListColour(idWarna));

                sharedP.putInt("colourPass", idWarna);

                sharedP.commit();
            }
        });

        alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alert.create().show();
    }

}