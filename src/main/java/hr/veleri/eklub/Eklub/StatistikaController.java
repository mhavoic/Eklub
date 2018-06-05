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
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Statistika;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.User;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Utakmica;

public class StatistikaController extends HomePageController {
    private TableLayout layout;
    private List<Statistika> statistike;
    private TableRow title;
    private Integer index = null;
    private Button new_insert;
    private Button delete;
    private Statistika statistika;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.utakmica_form);
        layout = (TableLayout) findViewById(R.id.utakmiceLayout);

        title = (TableRow) findViewById(R.id.naslov);
        new_insert = (Button) findViewById(R.id.new_b6);
        delete = (Button)findViewById(R.id.del_b6);

        if(User.logedUser.getTip_korisnika() == 3){
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
                if(statistika != null)
                {
                    new AlertDialog.Builder(StatistikaController.this)
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
                    showError(getString(R.string.selectUser),StatistikaController.this);
                }
            }
        });
        TreeMap<String,Boolean> naslovi = new TreeMap();
        naslovi.put("id",false);
        naslovi.put("Utakmica",true);
        naslovi.put("Sportaš",true);
        naslovi.put("minute",true);
        naslovi.put("skokoviNapad",true);
        naslovi.put("skokoviObrana",true);
        naslovi.put("asistencije",true);
        naslovi.put("Osobne pogreške",true);
        naslovi.put("poeni",true);
        setLayout(naslovi,title,layout,70,80,10);
    }

    private void initInsert(Natjecanje n){
        setContentView(R.layout.statistika_insert);
        //TODO INSERT /UPDATE
    }
    private void getStatistike(){
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
                Statistika k;
                try {
                    k = new Statistika();
                    jsonobject = json.getJSONObject(i);
                   // k.setIme(jsonobject.getString("ime"));

                    this.statistike.add(k);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
