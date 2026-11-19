package cli_logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task
{
	// CORE DATA OF THE TASK OBJECT 
	private TaskState taskState;
	private final String titel;
	private final String taskContext;
	private final LocalDateTime createdAt;
	
	//GLOBAL ATTRIBUT FOR THE DATES
	public static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");//will be used for later IO output formatting 
	
	public Task(String titel, String taskContext) {
		this.taskState = TaskState.OPEN;
		this.titel = titel;
		this.taskContext = taskContext;
		this.createdAt = LocalDateTime.now();
	}
	
	// geter methods 
	public String getTitel() {
		return this.titel;
	}
	
	public String getTaskContext() {
		return this.taskContext;
	}
	
	public TaskState getTaskState() {
		return this.taskState;
	}
	
	public LocalDateTime getDateOfTask() {
		return createdAt;
	}
	
	// Seter method
	public void setTaskState(TaskState value) {
		this.taskState = value;
	}
	
}
