package saving_system;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cli_logic.Task;

public class SavingManagement
{
	private static final String FILENAME = "newfile.json";
	private static final File DIRECTORY = new File(
			System.getProperty("user.home"), "Documents/taskList");

	public static String getStandardFilename()
	{
		return FILENAME;
	}

	public static void saveTasksToJson(List<Task> taskList, String filename)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		if (!DIRECTORY.isDirectory() && !DIRECTORY.mkdir())
		{
			IO.println("Folder could not be created");
			return;
		} // checks the system for the existens of the directory

		File file = new File(DIRECTORY, filename);// summaryses the directory
													// path and the file name

		try (FileWriter write = new FileWriter(file))
		{
			gson.toJson(taskList, write);// writs the values uf taskList into
											// the file
			IO.println("Task saved to: " + file.getAbsolutePath());
		}
		catch (IOException e)
		{
			IO.println(e.getLocalizedMessage());
		}
	}

	public static List<Task> loadTaskListFromFile()
	{
		Gson gson = new GsonBuilder().create();
		ArrayList<Task> taskList = new ArrayList<>();
		File file = new File(DIRECTORY, SavingManagement.getStandardFilename());

		try (FileReader reader = new FileReader(file))
		{
			// Reads JSON from the given file and converts it into a Array
			Task[] tasks = gson.fromJson(reader, Task[].class);

			// If the file is empty gson kan return null
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
