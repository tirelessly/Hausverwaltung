/**
 * @author <Ruslan Jelbuldin>
 * Matrikelnummer: 01407036
 */

public class MietWohnung extends Wohnung {
	private double miete;
	private int anzMieter;
	
	public MietWohnung(int id, double flaeche, int zimmeranzahl, int stockwerk, int baujahr, int plz, String strasse,
			int hausnummer, int top, double miete, int anzMieter) {
		super(id, flaeche, zimmeranzahl, stockwerk, baujahr, plz, strasse, hausnummer, top);
			if(miete <= 0 || anzMieter < 1)
				throw new IllegalArgumentException("Error: Parameter ungueltig");
		
		this.miete = miete;
		this.anzMieter = anzMieter;
	}

	private static final long serialVersionUID = 1L;
	
	public String getTyp() {
		return "MW";
	}
	
	public double getGesamtkosten(){
		double mietkostenGesamt = this.miete * this.getFlaeche();
		double zuschlag = 0;
		if(this.anzMieter > 1){
			zuschlag = 0.025 * this.anzMieter * mietkostenGesamt;
			if(this.anzMieter > 5){
				zuschlag = 0.1 * this.anzMieter * mietkostenGesamt;
			}
		}
		return zuschlag + mietkostenGesamt;
	}
	
	
	
	
	
	

	@Override
	public String toString() {
		return String.format("%-16s","Typ:") + "MW\n" + 
				String.format("%-16s","Id:") + getId() + "\n" + 
				String.format("%-16s","Flaeche:") + Wohnung.getDecimalFormat().format(getFlaeche()) + "\n" +
				String.format("%-16s","Zimmer:") + getZimmeranzahl() + "\n" +
				String.format("%-16s","Stock:") + getStockwerk() + "\n" +
				String.format("%-16s","Baujahr:") + getBaujahr() + "\n" +
				String.format("%-16s","PLZ:") + getPlz() + "\n" +
				String.format("%-16s","Strasse:") + getStrasse() + "\n" +
				String.format("%-16s","Hausnummer:") + getHausnummer() + "\n" +
				String.format("%-16s","Top:") + getTop() + "\n" +
				String.format("%-16s","Miete/m2:") + Wohnung.getDecimalFormat().format(this.miete) + "\n" +
				String.format("%-16s","Anzahl Mieter:") + this.anzMieter + "\n";
	}


	
	
}