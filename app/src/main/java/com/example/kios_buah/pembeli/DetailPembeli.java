package com.example.kios_buah.pembeli;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kios_buah.R;
import com.example.kios_buah.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailPembeli extends AppCompatActivity {
    EditText edtKodebuah, edtnamabuah, edtasalbuah, edtnamadistributor, edtHargabuah;
    ImageView imgGambar;

    String strKodebuah, strnamabuah, strasalbuah, strnamadistributor, strHargabuah, strGamabr, _id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembeli);

        edtKodebuah = (EditText) findViewById(R.id.edtKodeBuah);
        edtnamabuah = (EditText) findViewById(R.id.edtnamaBuah);
        edtasalbuah = (EditText) findViewById(R.id.edtasalbuah);
        edtnamadistributor = (EditText) findViewById(R.id.edtnamadistributor);
        edtHargabuah = (EditText) findViewById(R.id.edtHargaBuah);

        imgGambar = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strKodebuah = i.getStringExtra("kodebuah");
        strnamabuah = i.getStringExtra("namabuah");
        strasalbuah = i.getStringExtra("asalbuah");
        strnamadistributor = i.getStringExtra("namadistributor");
        strHargabuah = i.getStringExtra("hargabuah");
        strGamabr = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtKodebuah.setText(strKodebuah);
        edtnamabuah.setText(strnamabuah);
        edtasalbuah.setText(strasalbuah);
        edtnamadistributor.setText(strnamadistributor);
        edtHargabuah.setText(strHargabuah);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGamabr)
                .into(imgGambar);
    }
}

