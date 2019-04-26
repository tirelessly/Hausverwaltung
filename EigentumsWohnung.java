import java.text.DecimalFormat;

/**
 * @author <Ruslan Jelbuldin>
 * Matrikelnummer: 01407036
 */

public class EigentumsWohnung extends Wohnung {
	
	private double betriebskosten, ruecklage;
	
	public EigentumsWohnung(int id, double flaeche, int zimmeranzahl, int stockwerk, int baujahr, int plz,
			String strasse, int hausnummer, int top, double betriebskosten, double ruecklage) {
		super(id, flaeche, zimmeranzahl, stockwerk, baujahr, plz, strasse, hausnummer, top);
		
			if(betriebskosten < 0 || ruecklage < 0)
				throw new IllegalArgumentException("Error: Parameter ungueltig.");
		
		this.betriebskosten = betriebskosten;
		this.ruecklage = ruecklage;
	}
	
	
	public String getTyp() {
		return "EW";
	}
	
	public double getGesamtkosten(){
		
		double kostenGesamt = this.betriebskosten * this.getFlaeche();
		double ruecklageGesamt = this.ruecklage * this.getFlaeche();
		double gesamt = kostenGesamt + ruecklageGesamt;
		double zuschlag = 0.02 * this.getStockwerk() * gesamt;
		return zuschlag + kostenGesamt + ruecklageGesamt;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return String.format("%-16s","Typ:") + "EW\n" + 
				String.format("%-16s","Id:") + getId() + "\n" + 
				String.format("%-16s","Flaeche:") + Wohnung.getDecimalFormat().format(getFlaeche()) + "\n" +
				String.format("%-16s","Zimmer:") + getZimmeranzahl() + "\n" +
				String.format("%-16s","Stock:") + getStockwerk() + "\n" +
				String.format("%-16s","Baujahr:") + getBaujahr() + "\n" +
				String.format("%-16s","PLZ:") + getPlz() + "\n" +
				String.format("%-16s","Strasse:") + getStrasse() + "\n" +
				String.format("%-16s","Hausnummer:") + getHausnummer() + "\n" +
				String.format("%-16s","Top:") + getTop() + "\n" +
				String.format("%-16s","Betriebskosten:") + Wohnung.getDecimalFormat().format(this.betriebskosten) + "\n" +
				String.format("%-16s","Ruecklage:") + this.ruecklage + "\n";
	}

	

	
	
	

	

	

	
	
}