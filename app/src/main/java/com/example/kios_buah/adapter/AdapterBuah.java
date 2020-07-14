package com.example.kios_buah.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;;


import com.example.kios_buah.R;
import com.example.kios_buah.model.Modelbuah;
import com.example.kios_buah.server.BaseURL;
import com.squareup.picasso.Picasso;
import java.util.List;

    public class AdapterBuah extends BaseAdapter {

        private Activity activity;
        private LayoutInflater inflater;
        private List<Modelbuah> item;

        public AdapterBuah(Activity activity, List<Modelbuah> item) {
            this.activity = activity;
            this.item = item;
        }

        @Override
        public int getCount() {
            return item.size();
        }

        @Override
        public Object getItem(int position) {
            return item.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (inflater == null)
                inflater = (LayoutInflater) activity
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null)
                convertView = inflater.inflate(R.layout.content_buah, null);


            TextView kodebuah = (TextView) convertView.findViewById(R.id.edtKodeBuah);
            TextView namabuah = (TextView) convertView.findViewById(R.id.edtnamaBuah);
            TextView asalbuah = (TextView) convertView.findViewById(R.id.edtasalbuah);
            TextView namadistributor  = (TextView) convertView.findViewById(R.id.edtnamadistributor);
            TextView hargabuah = (TextView) convertView.findViewById(R.id.edtHargaBuah);
            ImageView gambarGitar = (ImageView) convertView.findViewById(R.id.gambar);

            kodebuah.setText(item.get(position).getKodebuah());
            namabuah.setText(item.get(position).getNamabuah());
            asalbuah.setText(item.get(position).getAsalbuah());
            hargabuah.setText("Rp." + item.get(position).getHargabuah());
            namadistributor.setText(item.get(position).getNamadistributor());
            Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                    .resize(40, 40)
                    .centerCrop()
                    .into(gambarGitar);
            return convertView;
        }
}
