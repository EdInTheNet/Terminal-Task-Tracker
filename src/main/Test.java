package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cli_logic.Cli_methods;
import cli_logic.Task;
import saving_system.SavingManagement;

public class Test
{
	
	public static void main(String[] args)
	{
		Test test = new Test();
		test.run();

	}
	
	public void run() {
		IO.println(SavingManagement.loadTaskListFromFile().size());//just to look if the rigt nummber of tasks is inthe file 
		List<Task> taskList = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			Cli_methods.addTask(taskList, "Test_Entry", "Tets Test Test ...");
			
		}
		Cli_methods.showAllTasks(taskList);
		SavingManagement.saveTasksToJson(taskList, SavingManagement.getStandardFilename());
		
		//try(Scanner input = new Scanner(System.in)){
			
		//	String mySearch = input.nextLine();
		//	Cli_methods.searchForTask(taskList, mySearch);	
			
		//}catch(InputMismatchException error){
		//	IO.println(error);
		//}

	}

}
