package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;
import java.util.Date;
import java.util.List;

public class Natjecanje {
    private String naziv;
    private Date pocetak;
    private Date kraj;
    private int brojEkipa;
    private String opis;
    private DobnaKategorija dobnaKategorija;

    public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	public int getBrojEkipa() {
		return brojEkipa;
	}

	public void setBrojEkipa(int brojEkipa) {
		this.brojEkipa = brojEkipa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public DobnaKategorija getDobnaKategorija() {
		return dobnaKategorija;
	}

	public void setDobnaKategorija(DobnaKategorija dobnaKategorija) {
		this.dobnaKategorija = dobnaKategorija;
	}

}
