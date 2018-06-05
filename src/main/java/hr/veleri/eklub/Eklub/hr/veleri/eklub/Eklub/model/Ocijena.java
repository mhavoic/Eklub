package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;
import java.util.List;

public class Ocijena {
    private String komentar;
    private int ocijena;
    private Korisnik korisnikTrener;
    private Korisnik korisnikRoditelj;

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public int getOcijena() {
		return ocijena;
	}

	public void setOcijena(int ocijena) {
		this.ocijena = ocijena;
	}

	public Korisnik getKorisnikTrener() {
		return korisnikTrener;
	}

	public void setKorisnikTrener(Korisnik korisnikTrener) {
		this.korisnikTrener = korisnikTrener;
	}

	public Korisnik getKorisnikRoditelj() {
		return korisnikRoditelj;
	}

	public void setKorisnikRoditelj(Korisnik korisnikRoditelj) {
		this.korisnikRoditelj = korisnikRoditelj;
	}

}
