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
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Trening;

public class TreninziController extends HomePageController {
    private TableLayout layout;
    private List<Trening> treninzi;
    private TableRow title;
    private Integer index = null;
    private Button new_insert;
    private Button delete;
    private Trening trening;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treninzi_form);
        layout = (TableLayout) findViewById(R.id.treniziLayout);

        title = (TableRow) findViewById(R.id.naslov);
        new_insert = (Button) findViewById(R.id.new_b4);
        delete = (Button)findViewById(R.id.del_b4);

        new_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initInsert(null);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trening != null)
                {
                    new AlertDialog.Builder(TreninziController.this)
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
                    showError(getString(R.string.selectUser),TreninziController.this);
                }
            }
        });
        TreeMap<String,Boolean> naslovi = new TreeMap();
        naslovi.put("id",false);
        naslovi.put("Termin",true);
        naslovi.put("Lokacija",true);
        naslovi.put("Dobna kategorija",true);
        naslovi.put("Trener",true);
        setLayout(naslovi,title,layout,70,100,10);
    }

    private void initInsert(Trening n){
        setContentView(R.layout.treninzi_insert);
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
                Trening k;
                try {
                    k = new Trening();
                    jsonobject = json.getJSONObject(i);
                   // k.setIme(jsonobject.getString("ime"));

                    this.treninzi.add(k);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
