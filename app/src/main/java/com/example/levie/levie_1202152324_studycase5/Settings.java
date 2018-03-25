package com.example.levie.levie_1202152324_studycase5;

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

public class Settings extends AppCompatActivity {
    //mendeklarasi variable yang digunakan
    int wrn;
    TextView color;
    AlertDialog.Builder alert;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //membuat alert dialog baru bernama alert
        alert = new AlertDialog.Builder(this);

        //melakukan inisialisasi shared preference
        SharedPreferences shp = getApplicationContext().getSharedPreferences("shp", 0);
        edit = shp.edit();
        //mengakses text view pada layout
        color = findViewById(R.id.warna);
        wrn = shp.getInt("background", R.color.putih);
        //set shape color dengan color id yang terpilih
        color.setText(getWarna(wrn));
    }

    //method dijalankan ketika pilihan di menu sudah dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

    //tombol back ditekan
    @Override
    public void onBackPressed() {
        //intent pindah dari settings ke first halaman dan memulai intent
        startActivity(new Intent(Settings.this, First.class));
        //menutup aktivity setelah intent di jalanlan
        finish();
    }

    //mendapatkan string warna yang akan digunakan untuk mengubah shape color
    public String getWarna(int i){
        if (i==R.color.biru){
            return "Biru";
        }else if (i==R.color.merah){
            return "Merah";
        }else if (i==R.color.hijau){
            return "Hijau";
        }else if (i==R.color.putih){
            return "Putih";
        }else {
            return "Kuning";
        }
    }

    //mendapatkan id dari warna yang akan digunakan
    public int getIntColor(int i){
        if (i==R.color.biru){
            return R.id.btn_blue;
        }else if (i==R.color.merah){
            return R.id.btn_red;
        }else if (i==R.color.hijau){
            return R.id.btn_green;
        }else if(i==R.color.putih){
            return R.id.btn_white;
        }else {
            return R.id.btn_kuning;
        }
    }

    public void pencet(View view) {
        //melakukan set judul dari alert dialog menjadi Shape Color
        alert.setTitle("Choose Color");
        //membuat view baru
        View v = getLayoutInflater().inflate(R.layout.warna,null);
        //tampilan view yang dibuat
        alert.setView(v);

        //mengakses radio group pada layout
        final RadioGroup rg = v.findViewById(R.id.rg);
        rg.check(getIntColor(wrn));

        //ketika menekan Ok pada alert dialog
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int cek = rg.getCheckedRadioButtonId();
                switch (cek){
                    case R.id.btn_blue:
                        wrn = R.color.biru;
                        break;
                    case R.id.btn_green:
                        wrn = R.color.hijau;
                        break;
                    case R.id.btn_red:
                        wrn = R.color.merah;
                        break;
                    case R.id.btn_white:
                        wrn = R.color.putih;
                        break;
                    case R.id.btn_kuning:
                        wrn = R.color.kuning;
                        break;
                }
                //melakukan set shape color menjadi color id yang dipilih
                color.setText(getWarna(wrn));
                //menaruh shared preference
                edit.putInt("background", wrn);
                //commit shared preference
                edit.commit();
            }
        });
        //ketika menekan Cancel pada alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                //membuat dan menampilkan alert dialog
            }
        });alert.create().show();
    }
}
