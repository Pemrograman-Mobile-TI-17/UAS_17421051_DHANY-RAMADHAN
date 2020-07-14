package com.example.kios_buah.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kios_buah.R;
import com.example.kios_buah.admin.HomeAdminActivity;
import com.example.kios_buah.pembeli.HomePembeliActivity;
import com.example.kios_buah.server.BaseURL;
import com.example.kios_buah.session.PrefSetting;
import com.example.kios_buah.session.SessionManager;
import com.ornach.nobobutton.NoboButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegistrasiActivity extends AppCompatActivity {

    NoboButton btnregis;
    Button btnklog;
    EditText edtNamaLengkap, edtEmail, edtUsername, edtNomorTelp, edtPassword;
    ProgressDialog pDialog;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        getSupportActionBar().hide();
        mRequestQueue = Volley.newRequestQueue(this);

        edtNamaLengkap = (EditText) findViewById(R.id.edtnamaleng);
        edtEmail = (EditText) findViewById(R.id.edtemail);
        edtUsername = (EditText) findViewById(R.id.edtusname);
        edtNomorTelp = (EditText) findViewById(R.id.edtnotel);
        edtPassword = (EditText) findViewById(R.id.edtpsw);


        btnklog = (Button) findViewById(R.id.btnklog);
        btnregis = (NoboButton) findViewById(R.id.btnregis);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnklog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNamaLengkap = edtNamaLengkap.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strUserName = edtUsername.getText().toString();
                String strNomorTelp = edtNomorTelp.getText().toString();
                String strPassword = edtPassword.getText().toString();

                if (strNamaLengkap.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strUserName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strNomorTelp.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nomor Telepon Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else {
                    regis(strUserName, strNamaLengkap, strEmail, strNomorTelp, strPassword);
                }
            }
        });

    }

    public void regis(String userName, String namaLengkap, String email, String noTelp, String password){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userName", userName);
        params.put("namaLengkap", namaLengkap);
        params.put("email", email);
        params.put("noTelp", noTelp);
        params.put("role", "2");
        params.put("password", password);

        pDialog.setMessage("Mohon Tunggu...");
        showDialog();


        JsonObjectRequest req = new JsonObjectRequest(BaseURL.regis, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status= response.getBoolean("error");

                            if(status == false){
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });


        mRequestQueue.add(req);
    }

    @Override
    public  void onBackPressed(){
        Intent i = new Intent(RegistrasiActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

}
