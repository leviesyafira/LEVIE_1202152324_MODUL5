package com.example.levie.levie_1202152324_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ToDo extends AppCompatActivity {
    //mendeklarasi variable yang digunakan
    EditText td, des, prior;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        //set title menjadi To Do List
        setTitle("Add To Do List");

        //mengakses id edit text pada layout
        td = findViewById(R.id.todo);
        des = findViewById(R.id.desc);
        prior = findViewById(R.id.prio);
        db = new Database(this);
    }
    @Override
    public void onBackPressed() {
        //intent dari halaman to do ke first, dan menjalankan intent
        startActivity(new Intent(ToDo.this, First.class));
        //menutup aktivitas setelah intent dijalankan
        this.finish();
    }
    //method yang dijalanan ketika tombol tambah to do di klik
    public void tambah(View view) {
        //apabila data semuanya diisi
        if (db.inputdata(new itemTodo(td.getText().toString(), des.getText().toString(), prior.getText().toString()))){
            //maka akan keluar toast berikut
            Toast.makeText(this, "To Do Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }else {
            //apabila data tidak diisi maka akan muncul sepertiberikut
            Toast.makeText(this, "To Do Tidak dapat Ditambahkan", Toast.LENGTH_SHORT).show();
            td.setText(null);
            des.setText(null);
            prior.setText(null);
        }
    }
}
