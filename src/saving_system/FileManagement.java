package saving_system;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cli_logic.Task;

public class FileManagement
{

	public static List<Task> loadTaskListFromFile()
	{
		Gson gson = new GsonBuilder().create();
		ArrayList<Task> taskList = new ArrayList<>();
		File directory = new File(System.getProperty("user.home"), "Documents/taskList");
		File file = new File(directory, SavingManagement.getStandardFilename());

		try (FileReader reader = new FileReader(file))
		{
			// Reads JSON from the given file and converts it into a Array
			Task[] tasks = gson.fromJson(reader, Task[].class);

			//If the file is empty gson kan return null 
			if (tasks != null)
			{
				for (Task task : tasks)
				{
					taskList.add(task);
				}
			}
		}
		catch (IOException e)
		{
			IO.println(e.getLocalizedMessage());
			return new ArrayList<>();
		}
		return taskList;
	}
}
