package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;
import java.util.Date;
import java.util.List;

public class Trening {
    private Date termin;
    private String lokacija;
    private DobnaKategorija dobnaKategorija;
    private Korisnik voditelj;
    private Korisnik trener;

    public void unosTreninga(){
    	//KREIRANJE TRENIGNA
    }

    public List<Trening> pregledTreninga(){
        //LISTA SA PODACIMA O SVIM TRENINZIMA
    	return null;
    }

    public void izmjenaTreninga(Trening trening){
    	//IZMJENA TRENINGA
    }

	public Date getTermin() {
		return termin;
	}

	public void setTermin(Date termin) {
		this.termin = termin;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public DobnaKategorija getDobnaKategorija() {
		return dobnaKategorija;
	}

	public void setDobnaKategorija(DobnaKategorija dobnaKategorija) {
		this.dobnaKategorija = dobnaKategorija;
	}

	public Korisnik getVoditelj() {
		return voditelj;
	}

	public void setVoditelj(Korisnik voditelj) {
		this.voditelj = voditelj;
	}

	public Korisnik getTrener() {
		return trener;
	}

	public void setTrener(Korisnik trener) {
		this.trener = trener;
	}

}
