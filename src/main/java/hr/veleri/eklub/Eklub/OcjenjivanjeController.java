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

import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Korisnik;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Model;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Natjecanje;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Ocijena;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.User;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Utakmica;

public class OcjenjivanjeController extends HomePageController {
    private TableLayout layout;
    private List<Ocijena> ocjene;
    private TableRow title;
    private int level;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.level = User.logedUser.getTip_korisnika();
        if(level == 2){
            setContentView(R.layout.pregled_ocjena_form);
            layout = (TableLayout) findViewById(R.id.ocjeneLayout);

            title = (TableRow) findViewById(R.id.naslov);

            TreeMap<String,Boolean> naslovi = new TreeMap();
            naslovi.put("id",false);
            naslovi.put("domacin",true);
            naslovi.put("gost",true);
            naslovi.put("lokacija",true);
            naslovi.put("rezultat",true);
            naslovi.put("natjecanje",true);
            naslovi.put("trener",true);
            setLayout(naslovi,title,layout,70,100,10);
        }
        else{
            initInsert();
        }


    }

    private void initInsert(){
        setContentView(R.layout.ocjenjivanje_insert);
        //TODO INSERT /UPDATE
    }
    private void getOcjene(){
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
                Ocijena k;
                try {
                    k = new Ocijena();
                    jsonobject = json.getJSONObject(i);
                   // k.setIme(jsonobject.getString("ime"));

                    this.ocjene.add(k);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
