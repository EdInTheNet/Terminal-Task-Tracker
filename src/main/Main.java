package main;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cli_logic.Cli_methods;
import cli_logic.Task;
import cli_logic.TaskState;
import saving_system.SavingManagement;

public class Main
{

	public static void main(String[] args)
	{
		// Utilitys
		List<Task> taskList = new ArrayList<>();
		boolean isAktiv = true;// wird verwendet um die Sityung offen Zu halten

		IO.println(
				"Welcome to the Task-Tracker CLI app, what do you need today ?");
		IO.println("");

		try (Scanner userInput = new Scanner(System.in))
		{
			while (isAktiv == true)
			{

				IO.println("Currently you got " + taskList.size() + " tasks");
				IO.println("");

				// Commands Displayed to User
				IO.println("Whant to add a new task ? Enter: add");
				IO.println("Whant to see all your current tasks ? Enter: show all");
				IO.println("Whant to change a task state ? Enter: change");
				IO.println("Whant to delet a task ? Enter: delete");

				if (!userInput.hasNextLine())
				{ // if no second line is avileble , leave the loop 
					break; // ends the inputstream 
				}
				String command = userInput.nextLine().trim();

				switch (command)
				{
					case "add":
						IO.println(
								"Writte a title for your new task");
						String titelInput = userInput.nextLine();
						IO.println("Task title: " + titelInput);

						IO.println("Writte what you got to do");
						String contextEingabe = userInput.nextLine();
						IO.println("task: " + contextEingabe);

						Cli_methods.addTask(taskList, titelInput,
								contextEingabe);
						SavingManagement.saveTasksToJson(taskList, SavingManagement.getStandardFilename());
						break;
					case "show all":
						Cli_methods.showAllTasks(taskList);
						break;
					case "change":
						IO.println("What task do you wish to change ? ");

						String titelSearchInput = userInput.nextLine();
						Task result = Cli_methods.searchForTask(taskList,
								titelSearchInput);
						if (result == null)
						{
							IO.println("Task not found");
							break;
						}

						TaskState[] states = TaskState.values();
						for (TaskState stat : states)
						{
							IO.println(
									stat.ordinal() + ":" + "  " + stat.name());
						}

						IO.println("please select a nummber");
						try
						{
							int userSelection = Integer
									.parseInt(userInput.nextLine().trim());
							if (userSelection >= 0 && userSelection < states.length)
							{
								result.setTaskState(states[userSelection]);
								IO.println("Status succesfully changed");
								SavingManagement.saveTasksToJson(taskList, SavingManagement.getStandardFilename());
							}
							else
							{
								IO.println("This input is not allowed");
							}
						}
						catch (NumberFormatException err)
						{
							IO.println("Please enter one of the nummbers that was shown to you ");
						}
						break;

					case "delete":
						IO.println("Which task do you wish to delete ?");
						String titelInputForDeletion = userInput.nextLine();

						Cli_methods.deleteTask(taskList,
								titelInputForDeletion);
						SavingManagement.saveTasksToJson(taskList, SavingManagement.getStandardFilename());
						break;
					case "exit":
						isAktiv = false;
						break;
					default:
						IO.println("PLease enter on of the commands or enter exit to close the app ");
						break;
				}
			}
		}
		catch (NoSuchElementException err)
		{
			IO.println("app has been closed " + err);
		}
	}

}
