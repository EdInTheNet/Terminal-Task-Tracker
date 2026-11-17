package main;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cli_logic.Cli_methods;
import cli_logic.Task;
import cli_logic.TaskState;

public class Main
{

	public static void main(String[] args)
	{
		// Utilitys
		List<Task> taskList = new ArrayList<>();
		boolean isAktiv = true;// wird verwendet um die Sityung offen Zu halten

		IO.println(
				"Wilkommen zu der Task Tracker CLI App, was kann ich für Sie tuen ?");
		IO.println("");

		try (Scanner userEingabe = new Scanner(System.in))
		{
			while (isAktiv == true)
			{

				IO.println("Momentan haben Sie " + taskList.size() + " Aufgaben");
				IO.println("");

				// Commands Displayed to User
				IO.println("Eine Aufgabe hinzufügen ? add");
				IO.println("Alle Aufgaben Anzeigen ? show all");
				IO.println("Einen Aufgaben Status ändern ? change");
				IO.println("Eine Aufgabe lösche ? delete");

				if (!userEingabe.hasNextLine())
				{ // Wenn keine weitere Zeile verfügbar ist, verlasse die Schleife.
					break; // Eingabestream wurde beendet
				}
				String command = userEingabe.nextLine().trim();

				switch (command)
				{
					case "add":
						IO.println(
								"Schreibe einen Titel für deinen neue Aufgabe");
						String titelEingabe = userEingabe.nextLine();
						IO.println("Aufgaben Titel: " + titelEingabe);

						IO.println("Schreibe auf was du zu tun hast");
						String contextEingabe = userEingabe.nextLine();
						IO.println("Aufgabe: " + contextEingabe);

						Cli_methods.addTask(taskList, titelEingabe,
								contextEingabe);
						break;
					case "show all":
						Cli_methods.showAllTasks(taskList);
						break;
					case "change":
						IO.println("Welche Aufgabe möchtest du ändern ? ");

						String titelSuchEingabe = userEingabe.nextLine();
						Task result = Cli_methods.searchForTask(taskList,
								titelSuchEingabe);
						if (result == null)
						{
							IO.println("Aufgabe nicht gefunden");
						}

						TaskState[] states = TaskState.values();
						for (TaskState stat : states)
						{
							IO.println(
									stat.ordinal() + ":" + "  " + stat.name());
						}

						IO.println("bitte wähle eine Nummer aus");
						try
						{
							int userAuswahl = Integer
									.parseInt(userEingabe.nextLine().trim());
							if (userAuswahl >= 0 && userAuswahl < states.length)
							{
								result.setTaskState(states[userAuswahl]);
								IO.println("Status erfolgreich geändert");
							}
							else
							{
								IO.println("Diese Eingabe ist nicht zulässig");
							}
						}
						catch (NumberFormatException err)
						{
							IO.println("Bitte gib eine Zulässige Zahl ein");
						}
						break;

					case "delete":
						IO.println("Welche Aufgabe möchten Sie entfernen ?");
						String titelEingabeZumLoeschen = userEingabe.nextLine();

						Cli_methods.deleteTask(taskList,
								titelEingabeZumLoeschen);
						break;
					case "exit":
						isAktiv = false;
						break;
					default:
						IO.println("Bitte gib eins der bereits gennaten Kommandos ein oder tppe exit ein um die Anwendugn zu verlassen");
						break;
				}
			}
		}
		catch (NoSuchElementException err)
		{
			IO.println("Eingabe beeandet. Die Anwendung wird geschlossen " + err);
		}
	}

}
