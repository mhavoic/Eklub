package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;
import java.util.Date;
import java.util.List;

import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.DobnaKategorija;

public class Sportas {
    private int oib;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private int imeOca;
    private int imeMajke;
    private DobnaKategorija dobna_kategorija;
    private List<Korisnik> rodbina;

	public int getOib() {
		return oib;
	}

	public void setOib(int oib) {
		this.oib = oib;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public int getImeOca() {
		return imeOca;
	}

	public void setImeOca(int imeOca) {
		this.imeOca = imeOca;
	}

	public int getImeMajke() {
		return imeMajke;
	}

	public void setImeMajke(int imeMajke) {
		this.imeMajke = imeMajke;
	}

	public DobnaKategorija getDobna_kategorija() {
		return dobna_kategorija;
	}

	public void setDobna_kategorija(DobnaKategorija dobna_kategorija) {
		this.dobna_kategorija = dobna_kategorija;
	}

	public List<Korisnik> getRodbina() {
		return rodbina;
	}

	public void setRodbina(List<Korisnik> rodbina) {
		this.rodbina = rodbina;
	}

}
