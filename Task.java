
import java.text.MessageFormat;

// The class for creating task with id, status, name and description
public class Task {

  // private enum Status { NotStarted, InProgress, Done };
  private int id;
  private TaskStatus status;
  private String name;
  private String description;

  // @Override
  // public String toString() {
  //   return "Task " + id + " was added";
  // }

  // Class constructor
  public Task(int initialId, String initialName, TaskStatus initialStatus, String initialDescription ) {

    id = initialId;

    status = initialStatus;

    name = initialName;

    description = initialDescription;

  }

  // Get the id of the task
  public int getId() {
    return id;
  }

  // Get the status of the task
  public TaskStatus getStatus() { 
    return status;
  }

  // Set the status of the task
  public void setStatus(TaskStatus newStatus) { 
    status = newStatus;
  }

  // Get the name of the task
  public String getName() {   
    return name;
  }

  // Get the description of the task
  public String getDescription() {
    return description;
  }

  // A summary/overview of the task
  public String getTaskOverview() {

    String template = "{0} --- {1} --- {2} --- {3}\n";
  
    String formattedTask = MessageFormat.format( template, id, name, status, description );

    return formattedTask;

  }

}
