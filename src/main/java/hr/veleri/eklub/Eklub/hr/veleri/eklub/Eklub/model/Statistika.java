package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;
import java.util.List;

public class Statistika {
    private int minute_u_igri;
    private int skokovi_u_obrani;
    private int skokovi_u_napadu;
    private int asistencije;
    private int poeni;
    private int osobneGreske;
    private Sportas sportas;
    private Utakmica utakmica;

    public void unosStatistike(){
    	//UNOSENJE STATISTIKE
    }

	public int getMinute_u_igri() {
		return minute_u_igri;
	}

	public void setMinute_u_igri(int minute_u_igri) {
		this.minute_u_igri = minute_u_igri;
	}

	public int getSkokovi_u_obrani() {
		return skokovi_u_obrani;
	}

	public void setSkokovi_u_obrani(int skokovi_u_obrani) {
		this.skokovi_u_obrani = skokovi_u_obrani;
	}

	public int getSkokovi_u_napadu() {
		return skokovi_u_napadu;
	}

	public void setSkokovi_u_napadu(int skokovi_u_napadu) {
		this.skokovi_u_napadu = skokovi_u_napadu;
	}

	public int getAsistencije() {
		return asistencije;
	}

	public void setAsistencije(int asistencije) {
		this.asistencije = asistencije;
	}

	public int getPoeni() {
		return poeni;
	}

	public void setPoeni(int poeni) {
		this.poeni = poeni;
	}

	public int getOsobneGreske() {
		return osobneGreske;
	}

	public void setOsobneGreske(int osobneGreske) {
		this.osobneGreske = osobneGreske;
	}

	public Sportas getSportas() {
		return sportas;
	}

	public void setSportas(Sportas sportas) {
		this.sportas = sportas;
	}

	public Utakmica getUtakmica() {
		return utakmica;
	}

	public void setUtakmica(Utakmica utakmica) {
		this.utakmica = utakmica;
	}

}
