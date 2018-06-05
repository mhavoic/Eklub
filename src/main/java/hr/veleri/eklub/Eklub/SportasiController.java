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
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Sportas;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Trening;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.User;

public class SportasiController extends HomePageController {
    private TableLayout layout;
    private List<Sportas> sportasi;
    private TableRow title;
    private Integer index = null;
    private Button new_insert;
    private Button delete;
    private Sportas sportas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sportasi_form);
        layout = (TableLayout) findViewById(R.id.sportasiLayout);

        title = (TableRow) findViewById(R.id.naslov);


        new_insert = (Button) findViewById(R.id.new_b7);
        delete = (Button)findViewById(R.id.del_b7);

        if(User.logedUser.getTip_korisnika() != 2){
            new_insert.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);
        }

        new_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initInsert(null);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sportas != null)
                {
                    new AlertDialog.Builder(SportasiController.this)
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
                    showError(getString(R.string.selectUser),SportasiController.this);
                }
            }
        });
        TreeMap<String,Boolean> naslovi = new TreeMap();
        naslovi.put("oib",false);
        naslovi.put("Ime",true);
        naslovi.put("Prezime",true);
        naslovi.put("Datum rođenja",true);
        naslovi.put("Ime oca",true);
        naslovi.put("Ime majke",true);
        naslovi.put("Dobna kategorija",true);
        setLayout(naslovi,title,layout,70,100,10);
    }

    private void initInsert(Sportas n){
        setContentView(R.layout.sportasi_insert);
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
                Sportas k;
                try {
                    k = new Sportas();
                    jsonobject = json.getJSONObject(i);
                   // k.setIme(jsonobject.getString("ime"));

                    this.sportasi.add(k);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
