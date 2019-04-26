/**
 * @author <Ruslan Jelbuldin>
 * Matrikelnummer: 01407036
 */
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;


public abstract class Wohnung implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}
	
	
	private int id, zimmeranzahl, stockwerk, baujahr, plz, hausnummer, top;
	private double flaeche;
	private String adresse, strasse;
	private int currentYear = Calendar.getInstance().get(Calendar.YEAR);

	public Wohnung(int id, double flaeche, int zimmeranzahl, int stockwerk, int baujahr, 
						int plz,String strasse, int hausnummer, int top) {
	
	if(flaeche <= 0 || zimmeranzahl <= 0 || stockwerk < 0 || plz <= 0 || hausnummer <= 0 || top <= 0)
				throw new IllegalArgumentException("Error: Parameter ungueltig.");
	    
	
	if(baujahr < 1500 || baujahr > currentYear)
				throw new IllegalArgumentException("Error: Baujahr ungueltig.");
			
		setId(id);
		setFlaeche(flaeche);
		setZimmeranzahl(zimmeranzahl);
		setStockwerk(stockwerk);
		setBaujahr(baujahr);
		setPlz(plz);
		setStrasse(strasse);
		setHausnummer(hausnummer);
		setTop(top);
		
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public int getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getZimmeranzahl() {
		return zimmeranzahl;
	}

	public void setZimmeranzahl(int zimmeranzahl) {
		this.zimmeranzahl = zimmeranzahl;
	}

	public int getStockwerk() {
		return stockwerk;
	}

	public void setStockwerk(int stockwerk) {
		this.stockwerk = stockwerk;
	}

	public int getBaujahr() {
		return baujahr;
	}

	public void setBaujahr(int baujahr) {
		this.baujahr = baujahr;
	}

	public double getFlaeche() {
		return flaeche;
	}

	public void setFlaeche(double flaeche) {
		this.flaeche = flaeche;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int alter(){
		return currentYear - this.baujahr;
	}
	
	public abstract double getGesamtkosten();
	
	public abstract String getTyp();
	
}