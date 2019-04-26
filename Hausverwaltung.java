import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;


/**
 * @author <Ruslan Jelbuldin>
 * Matrikelnummer: 01407036
 */

public class Hausverwaltung {
	private HausverwaltungDAO hausverwaltungDAO = null;
	String currentPath = null;
	List<Wohnung> wohnung = new ArrayList<>();
	
	public Hausverwaltung(String currentPath) {
		this.hausverwaltungDAO = HausverwaltungSerializationDAO.getInstance(currentPath);
		this.currentPath = currentPath;
	}
	
	public Wohnung getWohnungById(int id){
		if(Files.exists(Paths.get(this.currentPath + ".ser"))){
			return hausverwaltungDAO.getWohnungbyId(id);
		}else{
			System.out.println("File existiert nicht");
		}
		return hausverwaltungDAO.getWohnungbyId(id);
	}
	
	public void deleteWohnung(int id){
		if(Files.exists(Paths.get(this.currentPath + ".ser"))){
			this.hausverwaltungDAO.deleteWohnung(id);
		}else{
			System.out.println("File existiert nicht");
		}
	}

	public void add(Wohnung wohnung){
			this.hausverwaltungDAO.saveWohnung(wohnung);
	}
       
	
	public List<Wohnung> get(){
		if(!Files.exists(Paths.get(this.currentPath + ".ser"))){
			System.out.println("File existiert nicht");
		}
		return this.hausverwaltungDAO.getWohnungen();
	}
	
	public int count(){
		return this.hausverwaltungDAO.getWohnungen().size();
	}
	
	public int count(String typ){			
	int counter = 0;
		for(Wohnung w: this.hausverwaltungDAO.getWohnungen()){
			if(typ.equals(w.getTyp())){
				counter++;
			}
		}
		return counter;
	}
	
	public String meancosts(){
		double gesamtkosten = 0;
		List<Wohnung> wohnung = this.hausverwaltungDAO.getWohnungen();
		for(Wohnung w: wohnung){
			gesamtkosten += w.getGesamtkosten();
		}
		gesamtkosten = gesamtkosten / wohnung.size();
		return new DecimalFormat("#.00").format(Math.round(gesamtkosten * 100.0) / 100.0).replace(",", ".");
	}
	
	public List<Integer> oldest(){
		List<Wohnung> wohnung = this.hausverwaltungDAO.getWohnungen();
		int oldest = 3000;
		List<Integer> IdList = new ArrayList<>();
		if(wohnung.size() > 0){
		for(Wohnung w: wohnung){
			if(w.getBaujahr() == oldest){
				IdList.add(w.getId());
			}
			
			if(w.getBaujahr() < oldest){
				oldest = w.getBaujahr();
				IdList.clear();
				IdList.add(w.getId());
			}
		}
		
		}
		return IdList;
	}

	
	
}