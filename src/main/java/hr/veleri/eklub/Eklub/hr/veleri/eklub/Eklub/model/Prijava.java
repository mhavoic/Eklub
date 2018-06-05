package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;
import java.util.Date;

public class Prijava {
    private String korisnickoIme;
    private Date vrijemePrijave;
    private boolean aktivnaPrijava;
    
    private final int POKUSAJ=1;
    private final int PRIJAVA=2;
    private final int AKTIVNA_SESIJA=3;
    private final int KRIVI_PODACI=4;

  	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public Date getVrijemePrijave() {
		return vrijemePrijave;
	}

	public void setVrijemePrijave(Date vrijemePrijave) {
		this.vrijemePrijave = vrijemePrijave;
	}

	public boolean isAktivnaPrijava() {
		return aktivnaPrijava;
	}

	public void setAktivnaPrijava(boolean aktivnaPrijava) {
		this.aktivnaPrijava = aktivnaPrijava;
	}

}
