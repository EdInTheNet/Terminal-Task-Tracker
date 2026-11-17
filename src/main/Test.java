package main;

import com.google.gson.Gson;
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
		Gson gson = new Gson();
		Task task = new Task("Shopping", "Buy milk");
		IO.println(gson.toJson(task));
		
		Test test = new Test();
		test.run();

	}
	
	public void run() {
		List<Task> taskList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			Cli_methods.addTask(taskList, "Test_Entry", "Tets Test Test ...");	
		}
		
		Cli_methods.addTask(taskList, "Test2", "ishjkshfjiasdhf");
		try(Scanner input = new Scanner(System.in)){
			
			String mySearch = input.nextLine();
			Cli_methods.searchForTask(taskList, mySearch);	
			
		}catch(InputMismatchException error){
			IO.println(error);
		}

	}

}
