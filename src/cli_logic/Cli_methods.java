package cli_logic;

import java.util.List;

public class Cli_methods
{
	// Static Methoden die man dann im Switch case verwenden kann

	public static void addTask(List<Task> taskList, String titel,
			String context)
	{
		taskList.add(new Task(titel, context));
		IO.println("Task: " + titel + " succesfully added");
	}

	public static void showAllTasks(List<Task> taskList)
	{
		int index = 0;
		for (Task task : taskList)
		{
			IO.println("Task ID: " + index);
			IO.println(task.getTitel());
			IO.println(task.getTaskState());
			IO.println(task.getTaskContext());
			IO.println(task.getDateOfTask().format(Task.DATE_FORMAT));// Implementation of the global DATE_FORMAT
			IO.println("");
			
			index++;
		}

	}

	public static Task searchForTask(List<Task> taskList, int index)
	{
		if(index >= 0 && index < taskList.size()) {
			Task searchResult = taskList.get(index);
			IO.println("Task " + searchResult.getTitel() + "found");
			return searchResult;
		}
		IO.println("No result found under the given ID");
		return null;
	}

	public static void deleteTask(List<Task> taskList, int index)
	{
		if(index >= 0 && index < taskList.size()) {
			taskList.remove(index);
			IO.println("Task ID " + index + " succesfully removed ");
		}else {
			IO.println("please enter a valid Task ID");
		}
	}

}
