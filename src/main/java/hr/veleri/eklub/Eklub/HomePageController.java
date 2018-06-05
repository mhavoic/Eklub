package hr.veleri.eklub.Eklub;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.DobnaKategorija;
import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.User;

public class HomePageController extends MainActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TableLayout news;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_page);
        news = (TableLayout) findViewById(R.id.tableLayout);


        ArrayList<String> data = new ArrayList<String>();
        data.add("Samsung");
        data.add("Apple");
        data.add("Pixle");
        data.add("Vivo");

        for(int i=0;i<data.size();i++)
        {
            TableRow row=new TableRow(this);
            if(i%2 == 0)
                row.setBackgroundColor(Color.BLUE);

            row.setOnClickListener(mListener);
            String phone = data.get(i);
            TextView tv1=new TextView(this);
            tv1.setClickable(true);
            tv1.setText(phone);
            //TextView tv2=new TextView(this);
            //tv2.setText("test");
            row.addView(tv1);
           // row.addView(tv2);
            news.addView(row);
        }
    }

    View.OnClickListener mListener = new View.OnClickListener() {

        public void onClick(View v) {
            // v is the TableRow that was clicked
            v.setBackgroundColor(Color.RED);
            showError("kliknuo si ",HomePageController.this);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        setMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.pocetna_menu) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, HomePageController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.dobne_kategorije_menu) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, DobnaKategorijaController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.natjecanja_menu) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, NatjecanjaController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.ocijene_menu) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, OcjenjivanjeController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.ocijene_insert) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, OcjenjivanjeController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.sprotasi_menu) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, SportasiController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.statistika_menu) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, StatistikaController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.utakmica_menu) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, UtakmicaController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.trening_menu) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, TreninziController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if(id == R.id.korisnik_menu){
            Intent startNewActivityOpen = new Intent(HomePageController.this, KorisnikController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.izlaz) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, LoginController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if (id == R.id.dolazak_menu) {
            Intent startNewActivityOpen = new Intent(HomePageController.this, DolazakController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void test(){
        setContentView(R.layout.home_page);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.izlaz) {
            setContentView(R.layout.login_page);
            return true;
        }
        else if(id == R.id.dobne_kategorije_menu){
            Intent startNewActivityOpen = new Intent(HomePageController.this, DobnaKategorijaController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else if(id == R.id.korisnik_menu){
            Intent startNewActivityOpen = new Intent(HomePageController.this, KorisnikController.class);
            startActivityForResult(startNewActivityOpen, 0);
            finish();
            return true;
        }
        else  {
            setContentView(R.layout.treninzi_form);
            return true;
        }
    }
    /**
     * PRIKAZIVANJE STAVKI MENIJA OVISNO O AUTENTIFIKACIJI
     * */
    private void setMenu(Menu mainMenu){
        int userLevel = User.logedUser.getTip_korisnika();
        MenuItem m;
        if(userLevel > 1) {
            m = mainMenu.findItem(R.id.korisnik_menu);
            m.setVisible(false);
            m.setEnabled(false);
            m = mainMenu.findItem(R.id.dobne_kategorije_menu);
            m.setVisible(false);
            m.setEnabled(false);
            m = mainMenu.findItem(R.id.natjecanja_menu);
            m.setVisible(false);
            m.setEnabled(false);
            m = mainMenu.findItem(R.id.trening_menu);
            m.setVisible(false);
            m.setEnabled(false);
        }
        if(userLevel != 2 ){
            m = mainMenu.findItem(R.id.ocijene_menu);
            m.setVisible(false);
            m.setEnabled(false);
            m = mainMenu.findItem(R.id.utakmica_menu);
            m.setVisible(false);
            m.setEnabled(false);
            //TODO DOLASCI
        }
        if(userLevel != 3){
            m = mainMenu.findItem(R.id.ocijene_insert);
            m.setVisible(false);
            m.setEnabled(false);
        }
        if(userLevel < 2){
            m = mainMenu.findItem(R.id.statistika_menu);
            m.setVisible(false);
            m.setEnabled(false);
            m = mainMenu.findItem(R.id.sprotasi_menu);
            m.setVisible(false);
            m.setEnabled(false);
            m = mainMenu.findItem(R.id.dolazak_menu);
            m.setVisible(false);
            m.setEnabled(false);
        }
    }

    public void setLayout(TreeMap<String,Boolean> title,TableRow tr,TableLayout layout,int Height,int Width,int TextSize){
        int i=0;
        for (Map.Entry<String,Boolean> t :title.entrySet()) {
            Log.i("Naslov: ",t.getKey() + " "+t.getValue());
            if(!t.getValue())
                continue;
            //TODO NEIDE PO REDU
            i++;
            TextView column = new TextView(this);
            column.setText(t.getKey());
            column.setHeight(Height);
            column.setWidth(Width);
            column.setTextSize(TextSize);
            column.setGravity(Gravity.CENTER);
            //column.setVisibility(t.getValue() ? View.VISIBLE : View.INVISIBLE);
            tr.addView(column);
            layout.removeView(tr);
            layout.addView(tr);
        }
    }
}
