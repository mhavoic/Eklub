package hr.veleri.eklub.Eklub;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Korisnik;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Model;

public class KorisnikController extends HomePageController implements NavigationView.OnNavigationItemSelectedListener{

    private TableLayout userLayout;
    private List<Korisnik> korisnici;
    private TableRow title;
    private Integer index = null;
    private Button new_insert;
    private Button delete;
    private String korisnickoIme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korisnik_form);
        userLayout = (TableLayout) findViewById(R.id.usrLayout);
        title = (TableRow) findViewById(R.id.naslov);
        new_insert = (Button) findViewById(R.id.new_b);
        delete = (Button)findViewById(R.id.del_b);

        new_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initInsert(null);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(korisnickoIme != null)
                {
                new AlertDialog.Builder(KorisnikController.this)
                        .setTitle(getString(R.string.deleteTitle))
                        .setMessage(getString(R.string.deleteTitle))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //TODO BRISANJE KORISNIKA
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                }
                else{
                    showError(getString(R.string.selectUser),KorisnikController.this);
                }
            }
        });
        //todo ne stavlja elemente po redu
        TreeMap naslovi = new TreeMap();
        naslovi.put("Ime",true);
        naslovi.put("Prezime",true);
        naslovi.put("Tip korisnika",true);
        naslovi.put("Korisničko ime",false);
        naslovi.put("lozinka",false);
        setLayout(naslovi,title,userLayout,150,250,18);
        korisnici = new ArrayList<>();
        getKorisnike();
        int i = 0;
        for (Korisnik k: korisnici ) {
            i++;
            TableRow row=new TableRow(this);
            if(i%2 == 0)
                row.setBackgroundColor(Color.parseColor("#137ed9"));
            else
                row.setBackgroundColor(Color.parseColor("#00b0ff"));

            row.setOnClickListener(mListener);

            TextView tv1=new TextView(this);
            //tv1.setClickable(true);
            tv1.setText(k.getIme());
            tv1.setHeight(120);
            tv1.setWidth(250);
            tv1.setGravity(Gravity.CENTER);
            TextView tv2=new TextView(this);
            tv2.setText(k.getPrezime());
            tv2.setHeight(120);
            tv2.setWidth(250);
            tv2.setGravity(Gravity.CENTER);
            TextView tv3=new TextView(this);
            tv3.setText(String.valueOf(k.getTip_korisnika()));
            tv3.setHeight(120);
            tv3.setWidth(250);
            tv3.setGravity(Gravity.CENTER);
            TextView tv4=new TextView(this);
            tv4.setText(k.getKorisnickoIme());
            tv4.setHeight(120);
            tv4.setWidth(250);
            tv4.setGravity(Gravity.CENTER);
            tv4.setVisibility(View.INVISIBLE);
            TextView tv5=new TextView(this);
            tv5.setText(k.getZaporka());
            tv5.setHeight(120);
            tv5.setWidth(250);
            tv5.setGravity(Gravity.CENTER);
            tv5.setVisibility(View.INVISIBLE);
            row.setId(i);
            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);
            row.addView(tv4);
            row.addView(tv5);
            userLayout.addView(row);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void getKorisnike(){

        Model m = new Model();
        m.urlMethod = "GET";
        m.url = "korisnici";
        JSONArray json = null;
        String j="";
        try {
            j=m.execute().get();
            json = new JSONArray(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(json != null) {
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonobject = null;
                Korisnik k;
                try {
                    k = new Korisnik();
                    jsonobject = json.getJSONObject(i);
                    k.setIme(jsonobject.getString("ime"));
                    k.setPrezime(jsonobject.getString("prezime"));
                    k.setTip_korisnika( jsonobject.getInt("tip"));
                    k.setKorisnickoIme(jsonobject.getString("korIme"));
                    k.setKorisnickoIme(jsonobject.getString("korIme"));
                    k.setZaporka(jsonobject.getString("zaporka"));
                    this.korisnici.add(k);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                }
    }

    /**
     * INICIJACIJA FORME ZA UNOS/IZMJENU KORISNIKA
     * */

    private EditText ime;
    private EditText prezime;
    private EditText korIme;
    private EditText zaporka;
    private Spinner tip_korisnika;

    public void initInsert(Korisnik k){

        setContentView(R.layout.korisnik_insert);

        tip_korisnika = (Spinner) findViewById(R.id.tip_s);
        ime = (EditText) findViewById(R.id.ime);
        prezime = (EditText) findViewById(R.id.prezime);
        korIme = (EditText) findViewById(R.id.korIme);
        zaporka = (EditText) findViewById(R.id.pass);

        String[] tip_kor = new String[] {"1", "2", "3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tip_kor);
        tip_korisnika.setAdapter(adapter);
        TextView lbl_korIme = (TextView) findViewById(R.id.lbl_korIme);
        TextView lbl_pass = (TextView) findViewById(R.id.lbl_pass);
        Button unos = (Button) findViewById(R.id.unos_b);
        Button povratak = (Button) findViewById(R.id.povratak_b);


        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNewActivityOpen = new Intent(KorisnikController.this, KorisnikController.class);
                startActivityForResult(startNewActivityOpen, 0);
                finish();
            }
        });

        if(k != null) {
            tip_korisnika.setSelection(k.getTip_korisnika()-1);
            ime.setText(k.getIme());
            prezime.setText(k.getPrezime());
            korIme.setText(k.getKorisnickoIme());
            korIme.setVisibility(View.INVISIBLE);
            zaporka.setVisibility(View.INVISIBLE);
            lbl_korIme.setVisibility(View.INVISIBLE);
            lbl_pass.setVisibility(View.INVISIBLE);
            unos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unos(true);
                }
            });
        }else{
            unos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    unos(false);
                }
            });
        }
    }
    //METODA ZA UNOS SVIH VRIJEDNOSTI U BAZU
    public void unos(boolean isUpdate){

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("ime", String.valueOf(ime.getText())));
        params.add(new BasicNameValuePair("prezime", String.valueOf(prezime.getText())));
        params.add(new BasicNameValuePair("tip", String.valueOf(tip_korisnika.getSelectedItem())));
        params.add(new BasicNameValuePair("korIme", String.valueOf(korIme.getText())));

        JSONArray json = null;
        String j="";
        Model m = new Model();

        if(!isUpdate){

            params.add(new BasicNameValuePair("zaporka", String.valueOf(zaporka.getText())));
            m.urlMethod = "POST";
            m.url = "korisnici/add";
        }
        else{
            m.urlMethod = "PUT";
            m.url = "korisnici/update";
        }
        m.params = params;
        Log.i("Parametri",m.params.toString());
        try {
            j = m.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        showError(j,KorisnikController.this);
        Intent startNewActivityOpen = new Intent(KorisnikController.this, KorisnikController.class);
        finish();
        startActivityForResult(startNewActivityOpen, 0);

    }

    View.OnClickListener mListener = new View.OnClickListener() {

        public void onClick(View v) {
            if(index != null){
                TableRow row = (TableRow)findViewById(index);
                if(index%2 == 0)
                    row.setBackgroundColor(Color.parseColor("#137ed9"));
                else
                    row.setBackgroundColor(Color.parseColor("#00b0ff"));
            }
            if((index == null ? -1 : index) != v.getId()) {
                index = v.getId();
                v.setBackgroundColor(Color.parseColor("#D3D3D3"));
                TableRow row = (TableRow) v;
                TextView child = (TextView) row.getChildAt(3);
                korisnickoIme = child.getText().toString();
            }else{
                Korisnik k = new Korisnik();
                TableRow row = (TableRow) v;
                TextView child;

                child = (TextView) row.getChildAt(2);
                k.setTip_korisnika(Integer.valueOf(String.valueOf(child.getText())));
                child = (TextView) row.getChildAt(0);
                k.setIme(child.getText().toString());
                child = (TextView) row.getChildAt(1);
                k.setPrezime(child.getText().toString());
                child = (TextView) row.getChildAt(3);
                k.setKorisnickoIme(child.getText().toString());
                child = (TextView) row.getChildAt(4);
                k.setZaporka(child.getText().toString());
                initInsert(k);
            }
        }
    };
}
