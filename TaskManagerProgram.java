import java.util.ArrayList;
import java.util.Scanner;

// The TaskManagerProgram class runs the program
public class TaskManagerProgram {

  // A function that only prints the options available for the user
  public static void PrintTaskManagerProgramOptions() {

    System.out.println("\nSelect one of the following options:\n");

    System.out.println("1. View Taks");

    System.out.println("2. Add a New Task");

    System.out.println("3. Change a Task Status");

    System.out.println("4. Delete a Task");

    System.out.println("5. Save Tasks");

    System.out.println("6. Quit\n");

  }

  // The main function that runs the program
  public static void main(String... args) {

    boolean isProgramRunning = true;

    // Getting the values of the TaskStatus Enum to store in a list for ease of use
    ArrayList<TaskStatus> statusList = new ArrayList<TaskStatus>();
        
    for (TaskStatus status : TaskStatus.values()) {

      statusList.add(status);

    }
    
    // Create an instance of the DataManager that create a file to store and retrieve data
    DataManager dataManager = new DataManager();
    
    ArrayList<Task> tasks = dataManager.getTasks();
    
    // create an instance of a scanner to get input from the user
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("\n====== Task Manager App ======\n\n");

    // The program runs as long as isProgramRunning is true
    while (isProgramRunning) {
      
      PrintTaskManagerProgramOptions();

      System.out.print("Enter your option: ");
      String userPick = scanner.nextLine();

      System.out.println("");

      // If user pick option 1: print all the task
      if ( Integer.parseInt(userPick.trim()) == 1 ) {

        if (tasks.size() == 0) {

          System.out.println("----- No Tasks -----");

          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

        } else {

          for ( Task task : tasks ) {
            System.out.println(task.getTaskOverview());
          }

          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

        }

      // If user pick 2: ask name and description of a task and create a new task
      } else if ( Integer.parseInt(userPick.trim()) == 2 ) {

        // System.out.println(tasks.size());
        int taskId = tasks.size() + 1;

        System.out.print("Enter the Task Name: ");

        String taskName = scanner.nextLine();

        System.out.print("Enter the Task Description: ");

        String taskDescription = scanner.nextLine();

        Task newTask = new Task( taskId, taskName, TaskStatus.NotStarted, taskDescription );

        tasks.add(newTask);

      // If user pick option 3: get the id of the targeted task, print the remaining status options, get and set new status selected by the user 
      } else if ( Integer.parseInt(userPick.trim()) == 3 ) {

        System.out.print("Enter the Task Id: ");

        String taskId = scanner.nextLine();

        try {
        
          int parsedTaskId = Integer.parseInt(taskId);          

          Task task = tasks.get(parsedTaskId - 1);

          System.out.println("Task " + task.getId() + " current status is: " + task.getStatus() );

          System.out.println("\nSelect the New Status:\n");

          for (int i = 0; i < statusList.size(); i++) {
            TaskStatus currentStatus = statusList.get(i);
            if (currentStatus != task.getStatus()) {
              System.out.println(i + 1 + ". " + currentStatus);
            }
          }

          System.out.print("\nEnter the New Status Id: ");

          String statusId = scanner.nextLine();

          int parsedStatusId = Integer.parseInt(statusId);

          task.setStatus(statusList.get(parsedStatusId - 1));

          System.out.print("----- Status Changed. -----\n");
          
          Thread.sleep(1000);

        } catch (Exception e) {

          System.out.println("Invalid Task Id");

        }

      // If user pick option 4: get the id of the task to be removed and remove it from the list
      } else if ( Integer.parseInt(userPick.trim()) == 4 ) {

        System.out.print("\nEnter the Task Id: ");

        String taskId = scanner.nextLine();

        try {
        
          int parsedTaskId = Integer.parseInt(taskId);

          tasks.remove(parsedTaskId - 1);

          System.out.print("----- Task Deleted -----\n");
          
          Thread.sleep(1000);

        } catch (Exception e) {

          System.out.println("Invalid Task Id");

        }

      // if user pick option 5: call the SaveTask method of the dataManager instance to save the list of tasks in a file named data.txt
      } else if ( Integer.parseInt(userPick.trim()) == 5 ) {
        
        dataManager.SaveTask(tasks);

      // If user pick option 6: set isProgramRunning variable to false to stop the program
      } else if ( Integer.parseInt(userPick.trim()) == 6 ) {
        
        isProgramRunning = false;

      // For any other option other than the ones above, print the following
      } else {

        System.out.println("Wrong option, enter the option number.");

      }

    }

    scanner.close();

    System.out.println("==============================================");
    System.out.println("======== Thanks for Using Our Task Manager");
    System.out.println("===============================================");
    
  }

}