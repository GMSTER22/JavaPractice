import java.util.ArrayList;
import java.util.Scanner;

public class TaskManagerProgram {

  public static void PrintTaskManagerProgramOptions() {

    System.out.println("\nSelect one of the following options:\n");

    System.out.println("1. View Taks");

    System.out.println("2. Add a New Task");

    System.out.println("3. Change a Task Status");

    System.out.println("4. Delete a Task");

    System.out.println("5. Save Tasks");

    System.out.println("6. Quit\n");

  }

  public static void main(String... args) {

    boolean isProgramRunning = true;

    ArrayList<TaskStatus> statusList = new ArrayList<TaskStatus>();
        
    for (TaskStatus status : TaskStatus.values()) {

      statusList.add(status);

    }
    
    DataManager dataManager = new DataManager();
    
    ArrayList<Task> tasks = dataManager.getTasks();
    
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("\n====== Task Manager App ======\n\n");
    // for( Task task : tasks ) {
    //   System.out.println(task);
    // }

    while (isProgramRunning) {
      
      PrintTaskManagerProgramOptions();

      System.out.print("Enter your option: ");
      String userPick = scanner.nextLine();

      System.out.println("");

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


      } else if ( Integer.parseInt(userPick.trim()) == 2 ) {

        // System.out.println(tasks.size());
        int taskId = tasks.size() + 1;

        System.out.print("Enter the Task Name: ");

        String taskName = scanner.nextLine();

        System.out.print("Enter the Task Description: ");

        String taskDescription = scanner.nextLine();

        Task newTask = new Task( taskId, taskName, TaskStatus.NotStarted, taskDescription );

        tasks.add(newTask);

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

      } else if ( Integer.parseInt(userPick.trim()) == 5 ) {
        
        dataManager.SaveTask(tasks);

      } else if ( Integer.parseInt(userPick.trim()) == 6 ) {
        
        isProgramRunning = false;

      } else {

        System.out.println("Wrong option, enter the option number.");

      }

    }

    scanner.close();

    System.out.println("==============================================");
    System.out.println("======== Thanks for Using Our Task Manager");
    System.out.println("===============================================");

    // List<String> lines = Files.readAllLines(dataPath);

    // System.out.println(lines);

    // System.out.println(dataPath);

    // System.out.println(isDataFile);
    
  }

}