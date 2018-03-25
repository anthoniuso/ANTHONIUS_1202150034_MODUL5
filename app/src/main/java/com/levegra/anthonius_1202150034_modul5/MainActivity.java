package com.levegra.anthonius_1202150034_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBInit database;
    private RecyclerView recycler;
    private RecyclerViewAdapter dataAdapter;
    private ArrayList<ModelAddData> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("ToDo App");

        recycler = findViewById(R.id.recView);
        listData = new ArrayList<>();
        database = new DBInit(this);
        database.readData(listData);

        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("prefer", 0);
        int color = sharedP.getInt("colour", R.color.white);

        dataAdapter = new RecyclerViewAdapter(this,listData, color);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(dataAdapter);

        slideDel();
    }

    public void slideDel() {

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ModelAddData current = dataAdapter.getData(position);

                if(direction==ItemTouchHelper.LEFT){

                    if(database.popData(current.getTodo())){
                        dataAdapter.deleteData(position);
                        Snackbar.make(findViewById(R.id.coordinator), "Berhasil Menghapus", 1500).show();
                    }
                }
            }
        };

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recycler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_settings){
            Intent i = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(i);
            this.finish();
        }
        return true;
    }

    public void addList(View view) {
        Intent i = new Intent(MainActivity.this, AddActivity.class);
        startActivity(i);
    }
}
