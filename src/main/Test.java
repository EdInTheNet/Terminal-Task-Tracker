package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cli_logic.Cli_methods;
import cli_logic.Task;

public class Test
{

	public static void main(String[] args)
	{
		Test test = new Test();
		test.run();

	}
	
	public void run() {
		List<Task> taskList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			Cli_methods.addTask(taskList, "Test_Eintrag", "Tets Test Test ...");	
		}
		
		Cli_methods.addTask(taskList, "Test2", "ishjkshfjiasdhf");
		try(Scanner eingabe = new Scanner(System.in)){
			
			String meineSuche = eingabe.nextLine();
			Cli_methods.searchForTask(taskList, meineSuche);	
			
		}catch(InputMismatchException error){
			IO.println(error);
		}

	}

}
