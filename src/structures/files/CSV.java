package structures.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import structures.List;
import structures.Node;
import structures.exceptions.DomainException;

public class CSV{
	//Relative adrres
	//"./data/List.CSV"
	private static String path;
	
	public CSV(String path) {
		super();
		CSV.path = path;
	}

	public static void salve(Node node){
		try{
			//Veficate if exist the file
			boolean verification = new File(path).exists();
			
			//Open the writer for add data in file
			//StandardCharsets = work special characters
			//true = Need the "append" for increase data
			FileWriter writer = new FileWriter(path,StandardCharsets.ISO_8859_1,true); 
			if(!verification) {
				writer.write("Registration;Date\n");
			}
			
			//Write the node data
			writer.write(node.getRegistration() + ";" + node.getDate() + "\n");
			//Write the buffer data
			writer.flush();
			
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void read(Node node) throws ParseException, DomainException{
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			boolean firstLine = true;
			
			while((line = reader.readLine()) != null) {
				//Ignore the first line
				if(firstLine) {
					firstLine = false;
					continue;
				}
				//Split the line into parts using ; as separator
				String[] parts = line.split(";");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				int registration = Integer.parseInt(parts[0]);
				Date date = sdf.parse(parts[1]);
				
				List.addTheEnd(new Node(registration,date));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
