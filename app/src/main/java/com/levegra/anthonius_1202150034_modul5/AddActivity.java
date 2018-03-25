package com.levegra.anthonius_1202150034_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText todo, description, priority;
    private DBInit database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add ToDo");

        todo = (EditText) findViewById(R.id.todoEdit);
        description = (EditText) findViewById(R.id.descEdit);
        priority = (EditText) findViewById(R.id.priorityEdit);
        Button addBtn = (Button) findViewById(R.id.addButton);
        database = new DBInit(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (database.inputdata(new ModelAddData(todo.getText().toString(), description.getText().toString(), priority.getText().toString()))){
                    Toast.makeText(AddActivity.this, "List berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddActivity.this, MainActivity.class));
                    AddActivity.this.finish();
                } else {
                    Toast.makeText(AddActivity.this, "Seluruh field harus diisi", Toast.LENGTH_SHORT).show();
                    todo.setText(null);
                    description.setText(null);
                    priority.setText(null);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(AddActivity.this, MainActivity.class);
        startActivity(i);
        this.finish();
    }

    public void addTodo(View view) {
    }

}
