package main;

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
		List<Task> taskList = SavingManagement.loadTaskListFromFile();
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
				IO.println(
						"Whant to see all your current tasks ? Enter: show all");
				IO.println("Whant to change a task state ? Enter: change");
				IO.println("Whant to delet a task ? Enter: delete");

				if (!userInput.hasNextLine())
				{ // if no second line is available , leave the loop
					break; // ends the inputstream
				}
				String command = userInput.nextLine().trim();

				switch (command)
				{
					case "add":
						IO.println("Writte a title for your new task");
						String titelInput = userInput.nextLine();
						IO.println("Task title: " + titelInput);

						IO.println("Writte what you got to do");
						String contextEingabe = userInput.nextLine();
						IO.println("task: " + contextEingabe);

						Cli_methods.addTask(taskList, titelInput,
								contextEingabe);
						SavingManagement.saveTasksToJson(taskList,
								SavingManagement.getStandardFilename());
						break;
					case "show all":
						Cli_methods.showAllTasks(taskList);
						break;
					case "change":
						try
						{
							IO.println("Select a Task ID to change its status:");
							int taskIndex = Integer.parseInt(userInput.nextLine().trim());

							Task task = Cli_methods.searchForTask(taskList,taskIndex);

							if (task == null)
							{
								IO.println("Task with ID: " + taskIndex + " not found");
								break;
							}

							TaskState[] states = TaskState.values();

							for (int i = 0; i < states.length; i++)
							{
								IO.println(i + ": " + states[i].name());
							}

							IO.println("Please select a number:");
							int selection = Integer.parseInt(userInput.nextLine().trim());

							if (selection < 0 || selection >= states.length)
							{
								IO.println("This input is not allowed");
								break;
							}

							TaskState selectedState = states[selection];

							task.setTaskState(selectedState);
							IO.println("Status successfully changed");

							if (selectedState == TaskState.CLOSED)
							{
								IO.println("Do you want to delete the task now? yes/no");
								String response = userInput.nextLine().trim();

								if (response.equalsIgnoreCase("yes"))
								{
									Cli_methods.deleteTask(taskList, taskIndex);
								}
							}

							SavingManagement.saveTasksToJson(taskList,SavingManagement.getStandardFilename());
						}
						catch (NumberFormatException e)
						{
							IO.println("Please enter a valid number.");
						}
						break;

					case "delete":
						IO.println("Which task do you wish to delete ?");
						int titelInputForDeletion = Integer
								.parseInt(userInput.nextLine().trim());

						Cli_methods.deleteTask(taskList, titelInputForDeletion);
						SavingManagement.saveTasksToJson(taskList,
								SavingManagement.getStandardFilename());
						break;
					case "exit":
						isAktiv = false;
						break;
					default:
						IO.println(
								"PLease enter on of the commands or enter exit to close the app ");
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
