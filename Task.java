// import static java.lang.StringTemplate.STR;

import java.text.MessageFormat;

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

  
  public Task(int initialId, String initialName, TaskStatus initialStatus, String initialDescription ) {

    id = initialId;

    status = initialStatus;

    name = initialName;

    description = initialDescription;

  }

  public int getId() {
    return id;
  }

  public TaskStatus getStatus() { 
    return status;
  }

  public void setStatus(TaskStatus newStatus) { 
    status = newStatus;
  }

  public String getName() {   
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getTaskOverview() {

    String template = "{0} --- {1} --- {2} --- {3}\n";
  
    String formattedTask = MessageFormat.format( template, id, name, status, description );

    return formattedTask;

  }

}
