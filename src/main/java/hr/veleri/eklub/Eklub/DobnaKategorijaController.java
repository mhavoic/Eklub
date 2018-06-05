package hr.veleri.eklub.Eklub;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.DobnaKategorija;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Model;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Natjecanje;

public class DobnaKategorijaController extends HomePageController {

    private TableLayout layout;
    private List<DobnaKategorija> kategorije;
    private TableRow title;
    private Integer index = null;
    private Button new_insert;
    private Button delete;
    private DobnaKategorija kategorija;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dobna_kategorija_form);
            layout = (TableLayout) findViewById(R.id.kategorijaLayout);

            title = (TableRow) findViewById(R.id.naslov);
            new_insert = (Button) findViewById(R.id.new_b9);
            delete = (Button)findViewById(R.id.del_b9);

            new_insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initInsert(null);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(kategorija != null)
                    {
                        new AlertDialog.Builder(DobnaKategorijaController.this)
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
                        showError(getString(R.string.selectUser),DobnaKategorijaController.this);
                    }
                }
            });
            TreeMap<String,Boolean> naslovi = new TreeMap();
            naslovi.put("id",false);
            naslovi.put("Naziv",true);
            naslovi.put("Donja granica",true);
            naslovi.put("Gornja granica",true);
            setLayout(naslovi,title,layout,70,100,20);
        }}

    private void initInsert(Natjecanje n){
        setContentView(R.layout.dobna_kategorija_insert);
        //TODO INSERT /UPDATE
    }
    private void getKategorije(){
        Model m = new Model();
        m.urlMethod = "GET";
        m.url = "korisnici";//TODO
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
                DobnaKategorija k;
                try {
                    k = new DobnaKategorija();
                    jsonobject = json.getJSONObject(i);
                    // k.setIme(jsonobject.getString("ime"));

                    this.kategorije.add(k);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
