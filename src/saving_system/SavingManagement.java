package saving_system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cli_logic.Task;

public class SavingManagement
{
	private static final String FILENAME = "newfile.json";
	
	public static String getStandardFilename() {
		return FILENAME;
	}
	
	public static void saveTasksToJson(List<Task> taskList, String filename) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File directory = new File(System.getProperty("user.home"), "Documents/taskList"); // create a new Directory for the file 
		
		if(!directory.isDirectory() && !directory.mkdir()) {
			IO.println("Folder could not be created");
			return;
		}//checks the system for the existens of the directory 
		
		File file = new File(directory, filename);//summaryses the directory path and the file name 
				
		try(FileWriter write = new FileWriter(file)){
			gson.toJson(taskList, write);// writs the values uf taskList into the file 
			IO.println("Task saved to: " + file.getAbsolutePath());
		}catch(IOException e) {
			IO.println(e.getLocalizedMessage());
		}
	}
	
}
