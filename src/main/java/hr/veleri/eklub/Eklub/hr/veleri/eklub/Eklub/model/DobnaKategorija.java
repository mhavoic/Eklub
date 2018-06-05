package hr.veleri.eklub.Eklub.hr.veleri.eklub.Eklub.model;

public class DobnaKategorija {
    private String naziv;
    private int donjaGranica;
    private int gornjaGranica;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getDonjaGranica() {
        return donjaGranica;
    }

    public void setDonjaGranica(int donjaGranica) {
        this.donjaGranica = donjaGranica;
    }

    public int getGornjaGranica() {
        return gornjaGranica;
    }

    public void setGornjaGranica(int gornjaGranica) {
        this.gornjaGranica = gornjaGranica;
    }
}
