package cli_logic;

import java.util.List;

public class Cli_methods
{
	// Static Methoden die man dann im Switch case verwenden kann

	public static void addTask(List<Task> taskList, String titel,
			String context)
	{
		taskList.add(new Task(titel, context));
		IO.println("Aufgabe: " + titel + " Erfolgreich hinzugefügt");
	}

	public static void showAllTasks(List<Task> taskList)
	{
		for (Task task : taskList)
		{
			IO.println(task.getTitel());
			IO.println(task.getTaskState());
			IO.println(task.getTaskContext());
			IO.println("");
		}

	}

	public static Task searchForTask(List<Task> taskList, String titel)
	{
		for(Task task : taskList) {
			if(task.getTitel().equals(titel)) {
				IO.println("Aufgabe:" + titel + " gefunden");
				return task;
			}
		}
		return null;
	}

	public static void deleteTask(List<Task> taskList, String titel)
	{
		boolean removed = taskList
				.removeIf(task -> task.getTitel().equals(titel));
		if (removed)
		{
			IO.println("Aufgabe" + titel + "erfolgreich entfernt");
		}
		else
		{
			IO.println("Aufgabe" + titel + "nicht vorhanden");
		}
	}

}
