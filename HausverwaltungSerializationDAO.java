import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <Ruslan Jelbuldin>
 * Matrikelnummer: 01407036
 */

public class HausverwaltungSerializationDAO implements HausverwaltungDAO {
	private String currentPath;
	private List<Wohnung> wohnung = new ArrayList<Wohnung>();
	private static HausverwaltungSerializationDAO instance;


	private HausverwaltungSerializationDAO(String currentPath) {
		super();
		this.currentPath = currentPath;
	}
	
    public static HausverwaltungSerializationDAO getInstance(String currentPath) {
    	if(instance == null)
         instance = new HausverwaltungSerializationDAO(currentPath);
    	return instance;
    }
	

	@Override
	public List<Wohnung> getWohnungen() {
		if(!Files.exists(Paths.get(this.currentPath + ".ser"))){
			throw new IllegalArgumentException("Error: Parameter ungueltig.");
		}
		try {
	         FileInputStream fileIn = new FileInputStream(currentPath + ".ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         this.wohnung = (List<Wohnung>)in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         System.out.println("Error: Fehler bei Deserialisierung");
	         System.exit(1);
	      } catch (ClassNotFoundException e) {
	    	  System.out.println("Error: Fehler bei Deserialisierung");
		         e.printStackTrace();
		         System.exit(1);
		}
		return wohnung;
	}

	@Override
	public Wohnung getWohnungbyId(int id) {
		for(Wohnung wohnung: getWohnungen()){
			if(wohnung.getId() == id){
				return wohnung;
			}
		}
		return null;
	}
	
	@Override
	public void deleteWohnung(int id){
		boolean vorhanden = false;
		
		for(Wohnung w: getWohnungen()){
			if(w.getId() == id){
				this.wohnung.remove(w);
				vorhanden = true;
				break;
			}
		}
		if(vorhanden == false){
			throw new IllegalArgumentException("Error: Wohnung nicht vorhanden. (id=" + id + ")");
		}
				try {
					FileOutputStream fileOut = new FileOutputStream(currentPath + ".ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut); 
					out.writeObject(this.wohnung);
					out.close();
					fileOut.close();
				}catch(IOException i) { 
					i.printStackTrace(); 
					System.out.println("Error: Fehler bei Serialisierung");
					System.exit(1);
				}
				System.out.println("Info: Wohnung " + id + " deleted.");	
	}
	
	
	@Override
	public void saveWohnung(Wohnung wohnung) {
		if(Files.exists(Paths.get(this.currentPath + ".ser"))){
		for(Wohnung w: getWohnungen()){
			if(w.getId() == wohnung.getId()){
				throw new IllegalArgumentException("Error: Wohnung bereits vorhanden. (id=" + wohnung.getId() + ")");
			}
		}
		}
		this.wohnung.add(wohnung);
		try {
			FileOutputStream fileOut = new FileOutputStream(currentPath + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut); 
			out.writeObject(this.wohnung);
			out.close();
			fileOut.close();
		}
			catch(IOException i) { 
				i.printStackTrace(); 
				System.out.println("Error: Fehler bei Serialisierung");
				System.exit(1);
				}
		System.out.println("Info: Wohnung " + wohnung.getId() + " added.");
	}
	
}