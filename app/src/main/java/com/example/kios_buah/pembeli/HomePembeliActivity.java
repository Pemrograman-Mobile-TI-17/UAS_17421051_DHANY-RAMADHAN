package com.example.kios_buah.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kios_buah.R;
import com.example.kios_buah.adapter.AdapterBuah;
import com.example.kios_buah.model.Modelbuah;
import com.example.kios_buah.user.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.kios_buah.server.BaseURL;
import com.example.kios_buah.session.PrefSetting;
import com.example.kios_buah.session.SessionManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomePembeliActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterBuah adapter;
    ListView list;

    ArrayList<Modelbuah> newsList = new ArrayList<Modelbuah>();
    private RequestQueue mRequestQueue;

    FloatingActionButton floatingExit;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pembeli);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreference();

        session = new SessionManager(HomePembeliActivity.this);

        prefSetting.isLogin(session, prefs);

        getSupportActionBar().setTitle("Data Buah");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);

        floatingExit = (FloatingActionButton) findViewById(R.id.exit);

        newsList.clear();
        adapter = new AdapterBuah(HomePembeliActivity .this, newsList);
        list.setAdapter(adapter);
        getAllGitar();

        floatingExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomePembeliActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void getAllGitar() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.databuah, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data gitar = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final Modelbuah buah = new Modelbuah();
                                    final String _id = jsonObject.getString("_id");
                                    final String namabuah = jsonObject.getString("namabuah");
                                    final String Kodebuah = jsonObject.getString("kodebuah");
                                    final String asalbuah = jsonObject.getString("asalbuah");
                                    final String namadistributor = jsonObject.getString("namadistributor");
                                    final String hargabuah = jsonObject.getString("hargabuah");
                                    final String Gambar = jsonObject.getString("gambar");
                                    buah.setKodebuah(Kodebuah);
                                    buah.setNamabuah(namabuah);
                                    buah.setAsalbuah(asalbuah);
                                    buah.setNamadistributor(namadistributor);
                                    buah.setHargabuah(hargabuah);
                                    buah.setGambar(Gambar);
                                    buah.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(HomePembeliActivity.this, DetailPembeli.class);
                                            a.putExtra("kodebuah", newsList.get(position).getKodebuah());
                                            a.putExtra("namabuah", newsList.get(position).getNamabuah());
                                            a.putExtra("asalbuah", newsList.get(position).getAsalbuah());
                                            a.putExtra("namadistributor", newsList.get(position).getNamadistributor());
                                            a.putExtra("hargabuah", newsList.get(position).getHargabuah());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(buah);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}

