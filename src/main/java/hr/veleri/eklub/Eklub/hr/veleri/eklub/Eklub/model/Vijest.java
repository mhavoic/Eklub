import hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model.Korisnik;

public class Vijest {
    private String naslov;
    private String sadrzaj;
    private Korisnik korisnikVoditelj;

    public void unosVijesti(){
    	//UNOS NOVE VIJESTI
    }

    public void pregledVijesti(){
    	//PREGLED VIJESTI
    }

    public void izmjenaVijesti(){
    	//IZMJENA VIJESTI
    }

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public Korisnik getKorisnikVoditelj() {
		return korisnikVoditelj;
	}

	public void setKorisnikVoditelj(Korisnik korisnikVoditelj) {
		this.korisnikVoditelj = korisnikVoditelj;
	}

}
