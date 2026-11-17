package cli_logic;

public class Task
{
	
	private TaskState taskState;
	private final String titel;
	private final String taskContext;
	
	public Task(String titel, String taskContext) {
		this.taskState = TaskState.OPEN;
		this.titel = titel;
		this.taskContext = taskContext;
	}
	
	// geter methoden 
	public String getTitel() {
		return this.titel;
	}
	
	public String getTaskContext() {
		return this.taskContext;
	}
	
	public TaskState getTaskState() {
		return this.taskState;
	}
	
	// Seter methoden 
	public void setTaskState(TaskState value) {
		this.taskState = value;
	}
	
	
}
