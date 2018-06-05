package hr.veleri.eklub.Eklub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Korisnik;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Model;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.User;

public class LoginController extends MainActivity {
    private Button cancel;
    private Button ok;
    private EditText userName;
    private EditText pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        userName = (EditText) findViewById(R.id.t_userName);
        pass = (EditText) findViewById(R.id.t_pass);
        cancel= (Button) findViewById(R.id.b_exit);
        ok = (Button) findViewById(R.id.b_ok);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void exit(){
        finish();
        System.exit(0);
    }


    public void login(){


        //RequestQueue requestQueue = Volley.newRequestQueue(this);


        if(userName.getText().length() > 0  && pass.getText().length() > 0) {

            Model m = new Model();
            m.urlMethod = "POST";
            m.url = "korisnici/login";
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("korIme", String.valueOf(userName.getText())));
            params.add(new BasicNameValuePair("zaporka", String.valueOf(pass.getText())));
            m.params = params;
            Log.i("Parametri",m.params.toString());
            JSONArray json = null;
            String j="";
            try {
                j = m.execute().get();
                json = new JSONArray(j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                showError(j,LoginController.this);
                clearText();
                return;
            }
            // = m.getResponse();

            if(json == null){
                showError( getString(R.string.login_no_response),LoginController.this);
                return;
            }
            if(json.length() > 1){
                showError( getString(R.string.login_multiple_response),LoginController.this);
                return;
            }
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonobject = null;
                try {
                    Korisnik k = new Korisnik();
                    jsonobject = json.getJSONObject(i);
                    k.setPrezime(jsonobject.getString("ime"));
                    k.setIme(jsonobject.getString("prezime"));
                    k.setKorisnickoIme(jsonobject.getString("korIme"));
                    k.setTip_korisnika(jsonobject.getInt("tip"));
                    User.logedUser = k;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Intent startNewActivityOpen = new Intent(LoginController.this, HomePageController.class);
            finish();
            startActivityForResult(startNewActivityOpen, 0);
        }else{
            showError( getString(R.string.login_empty_fields),LoginController.this);
            clearText();
        }

    }
    private void clearText(){
        userName.getText().clear();
        pass.getText().clear();
    }
}
