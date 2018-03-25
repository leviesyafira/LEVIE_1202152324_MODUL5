package com.example.levie.levie_1202152324_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class First extends AppCompatActivity {
    //melakukan deklarasi variable yang digunakan
    Database db;
    RecyclerView rv;
    adapter adapter;
    ArrayList<itemTodo> listitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //melakukan akses recyclerview yang ada pada layout
        rv = findViewById(R.id.rv_list);
        //melakukan pembuatan araylist baru
        listitem = new ArrayList<>();
        //melakukan pembuatan database baru
        db = new Database(this);
        //memanggil method getAllitem
        db.getAllItem(listitem);

        //menginisialisasi shared preference
        SharedPreferences shp = this.getApplicationContext().getSharedPreferences("shp", 0);
        int warna = shp.getInt("background", R.color.putih);

        //melakukan pembuatan adapter baru
        adapter = new adapter(this, listitem, warna);
        //menghindari perubahan ukuran yang tidak perlu ketika menambahkan / hapus item pada recycler view
        rv.setHasFixedSize(true);
        //tampilan layout linear
        rv.setLayoutManager(new LinearLayoutManager(this));
        //menginisiasi adapter untuk recycler view
        rv.setAdapter(adapter);

        //menjalankan method hapus data pada to do list
        geser();
    }

    //pembuatan method untuk menghapus item pada to do list
    public void geser() {
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback sc = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int posisi = viewHolder.getAdapterPosition();
                itemTodo now = adapter.getItem(posisi);
                //apabila item di swipe ke arah kiri atau ke kanan
                if (direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    if (db.hapusdata(now.getTodo())){
                        //menghapus data
                        adapter.removeitem(posisi);
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper helper =  new ItemTouchHelper(sc);
        helper.attachToRecyclerView(rv);
    }
    //membuat menu pada activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //method yang dijalankan saat item dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item
        int id = item.getItemId();
        //apabila item yang dipilih adalah setting
        if (id==R.id.setting){
            //membuat intent dari halaman awal ke settings dan memulai intent
            startActivity(new Intent(First.this, Settings.class));
            //menutup aktivitas setelah intent dijalankan
            finish();
        }
        return true;
    }
    //method yang akan dijalankan ketika tombol tambah di klik
    public void masuk(View view) {
        //intent dari halaman first ke halaman To do dan memulai intent
        startActivity(new Intent(First.this, ToDo.class));
        //menutup aktivitas setelah intent dijalankan
        finish();
    }
}
