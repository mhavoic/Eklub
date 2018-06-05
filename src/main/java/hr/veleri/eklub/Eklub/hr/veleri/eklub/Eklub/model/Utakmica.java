package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;
import java.util.List;

public class Utakmica {
    private String domacin;
    private String gost;
    private String lokacija;
    private Natjecanje natjecanje;
    private Korisnik trener;

    public void unosUtakmice(){
    	//UNOS PODATAKA UTAKMICE
    }

    public List<Utakmica> pregledUtakmice(){
        return null;
        //LISTA SA PODACIMA O UTAKMICAMA
    }

    public void izmjenaUtakmice(Utakmica utakmica){
    	//IZMJENA POSTOJECE UTAKMICE
    }

	public String getDomacin() {
		return domacin;
	}

	public void setDomacin(String domacin) {
		this.domacin = domacin;
	}

	public String getGost() {
		return gost;
	}

	public void setGost(String gost) {
		this.gost = gost;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public Natjecanje getNatjecanje() {
		return natjecanje;
	}

	public void setNatjecanje(Natjecanje natjecanje) {
		this.natjecanje = natjecanje;
	}

	public Korisnik getTrener() {
		return trener;
	}

	public void setTrener(Korisnik trener) {
		this.trener = trener;
	}

}
