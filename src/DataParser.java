import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

public class DataParser{
	int numArgs;
	String sql;
	String tableName;
	String[] parameters;
	ArrayList<String> queries = new ArrayList<String>();

	public DataParser(){
		numArgs=-1;
		sql="";
	}

	public DataParser(String dataset){
		parseFile(dataset);
	}

	public String getTable(String filename){
		return filename.substring(0, filename.indexOf(".")); 
	}

	public void formSQL(String[] data){
		sql = "insert into ";
		sql += tableName;
		sql += "(";
		for(int i=0; i < numArgs; i++){
			if(i != numArgs-1){
				sql += parameters[i];
				sql += ",";
			} 
			else if(i == numArgs-1){
				sql += parameters[i];
				sql += ") ";
			}
		}
		sql += "values ('";
		for(int i=0; i < numArgs; i++){
			//check if int or string here
			if(i != numArgs-1){
				sql += data[i];
				sql += "','";
			} 
			else if(i == numArgs-1){
				sql += data[i];
				sql += "')";
			}
		}
		sql += ";";
		queries.add(sql);

	}

	public void parseFile(String filename){
		tableName = getTable(filename);
		try (BufferedReader br = new BufferedReader(new FileReader(filename))){

            	String currentLine;
            	boolean firstLine = true;
            	while ((currentLine = br.readLine()) != null) {
            		if(firstLine){
            			parameters = currentLine.split(",");
            			numArgs = parameters.length;
            			firstLine = false;
            		}
            		else{
            			String[] temp = currentLine.split(",");
            			formSQL(temp);
            		}
            	}

        	} catch (IOException e) {
          		e.printStackTrace();
        	} 
	}
}