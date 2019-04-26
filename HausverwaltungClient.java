import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.xml.bind.*;

/**
 * @author <Ruslan Jelbuldin>
 * Matrikelnummer: 01407036
 */

public class HausverwaltungClient {
	public static void main(String[] args) throws JAXBException {
	
		
		JAXBContext context = JAXBContext.newInstance(String.class);
		

		Hausverwaltung verwaltung = new Hausverwaltung(args[0]);
		
		if(args.length == 2 && args[1].equals("list")){
			for(Wohnung wohnung: verwaltung.get()){
				System.out.println(wohnung.toString());
			}
			
		}else if(args.length == 14 && args[1].equals("add") && args[2].equals("EW")){
			try{
				Wohnung ew = new EigentumsWohnung(Integer.parseInt(args[3]), Double.parseDouble(args[4]), 
						Integer.parseInt(args[5]), Integer.parseInt(args[6]),Integer.parseInt(args[7]), Integer.parseInt(args[8]),
						args[9], Integer.parseInt(args[10]), Integer.parseInt(args[11]), Double.parseDouble(args[12]),Double.parseDouble(args[13]));
        				verwaltung.add(ew);
				}catch(IllegalArgumentException e){
					if(e.getMessage().equals("Error: Baujahr ungueltig.")){
				System.out.println(e.getMessage());
					}else{
						System.out.println("Error: Parameter ungueltig.");
					}
			}
		}else if(args.length == 14 && args[1].equals("add") && args[2].equals("MW")){
			try{
				Wohnung mw = new MietWohnung(Integer.parseInt(args[3]), Double.parseDouble(args[4]), 
						Integer.parseInt(args[5]), Integer.parseInt(args[6]),Integer.parseInt(args[7]), Integer.parseInt(args[8]),
						args[9], Integer.parseInt(args[10]), Integer.parseInt(args[11]), Double.parseDouble(args[12]),Integer.parseInt(args[13]));
				verwaltung.add(mw);
			}catch(IllegalArgumentException e){
				if(e.getMessage().equals("Error: Baujahr ungueltig.")){
			System.out.println(e.getMessage());
				}else{
					System.out.println("Error: Parameter ungueltig.");
				}
		}
		}else if(args.length == 3 && args[1].equals("list")){
			try{
				if(verwaltung.getWohnungById(Integer.parseInt(args[2])) == null){
					System.out.println("Error: Wohnung nicht vorhanden. (id=" + Integer.parseInt(args[2]) + ")");
				}
				System.out.println(verwaltung.getWohnungById(Integer.parseInt(args[2])));
				
			}catch(IllegalArgumentException n){
				System.out.println("Parameter ungueltig.");
			}
		}else if(args.length == 3 && args[1].equals("delete")){
			try{
				verwaltung.deleteWohnung(Integer.parseInt(args[2]));
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}else if(args.length == 2 && args[1].equals("count")){
			try{
				System.out.println(verwaltung.count());
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}else if(args.length == 3 && args[1].equals("count") && args[2].equals("EW")){
			try{
				System.out.println(verwaltung.count("EW"));
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}else if(args.length == 3 && args[1].equals("count") && args[2].equals("MW")){
			try{
				System.out.println(verwaltung.count("MW"));
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}else if(args.length == 2 && args[1].equals("meancosts")){
			try{
				System.out.println(verwaltung.meancosts());
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}
		else if(args.length == 2 && args[1].equals("oldest")){
			try{
				for(Integer i: verwaltung.oldest()){
					System.out.println("Id: " + i);
				}
			}catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}
			
		else{
			System.out.println("Error: Parameter ungueltig.");
		}
				
	}
}