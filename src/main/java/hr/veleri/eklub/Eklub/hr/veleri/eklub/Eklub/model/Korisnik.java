package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;
public class Korisnik {
    private String korisnickoIme;
    private String zaporka;
    private String ime;
    private String prezime;
    private Integer tip_korisnika;

 	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getZaporka() {
		return zaporka;
	}

	public void setZaporka(String zaporka) {
		this.zaporka = zaporka;
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

	public Integer getTip_korisnika() {
		return tip_korisnika;
	}

	public void setTip_korisnika(Integer tip_korisnika) {
		this.tip_korisnika = tip_korisnika;
	}

}
