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

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Model;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Natjecanje;

public class NatjecanjaController extends HomePageController {
    private TableLayout layout;
    private List<Natjecanje> natjecanja;
    private TableRow title;
    private Integer index = null;
    private Button new_insert;
    private Button delete;
    private Natjecanje natjecanje;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.natjecanje_form);
        layout = (TableLayout) findViewById(R.id.layout);

        title = (TableRow) findViewById(R.id.naslov);
        new_insert = (Button) findViewById(R.id.new_b2);
        delete = (Button)findViewById(R.id.del_b2);

        new_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initInsert(null);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(natjecanje != null)
                {
                    new AlertDialog.Builder(NatjecanjaController.this)
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
                    showError(getString(R.string.selectUser),NatjecanjaController.this);
                }
            }
        });
        TreeMap<String,Boolean> naslovi = new TreeMap();
        naslovi.put("id",false);
        naslovi.put("naziv",true);
        naslovi.put("opis",true);
        naslovi.put("broj ekipa",true);
        naslovi.put("pocetak",true);
        naslovi.put("kraj",true);
        naslovi.put("Dobna kategorija",true);
        setLayout(naslovi,title,layout,70,100,10);
    }

    private void initInsert(Natjecanje n){
        setContentView(R.layout.natjecanje_insert);
        //TODO INSERT /UPDATE
    }
    private void getNatjecanja(){
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
                Natjecanje k;
                try {
                    k = new Natjecanje();
                    jsonobject = json.getJSONObject(i);
                   // k.setIme(jsonobject.getString("ime"));

                    this.natjecanja.add(k);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
