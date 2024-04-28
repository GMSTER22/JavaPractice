import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.Stream;

public class DataManager {
  
  // ArrayList<Task> tasks;

  ArrayList<Task> tasks = new ArrayList<Task>();
  
  Path dataFilePath = Paths.get("data.text"); 
  
  public DataManager() {

    Boolean isDataFile = Files.exists(dataFilePath);

    String fileTextSeparator = "@@@";

    if (isDataFile) {

      // If data file exists then read from the file and get data
      try (Stream<String> lines = Files.lines(dataFilePath)) {

        // System.out.println("Previously saved data will be added to the list\n\n");

        lines.forEach(line -> {

          String[] taskArr = line.split(fileTextSeparator);

          int taskId = Integer.parseInt(taskArr[0].trim());

          String taskName = taskArr[1].trim();

          TaskStatus taskStatus = TaskStatus.valueOf(taskArr[2].trim());

          String taskDesciption = taskArr[3].trim();

          Task newTask = new Task(taskId, taskName, taskStatus, taskDesciption);

          tasks.add(newTask);

        });

      } catch (Exception e) {

        // TODO: handle exception
        // System.err.println(e);
        System.err.println("Try again later");

      }

    } else {

      // If data file doesn't exists then create a new data file to save data
      try {
        
        Files.createFile(dataFilePath);

      } catch (FileAlreadyExistsException x) {

        System.err.format("file named %s" + " already exists%n", dataFilePath.getFileName());

      } catch (Exception e) {

        // TODO: handle exception
        System.err.print(e);

      }

    }

  }

  public ArrayList<Task> getTasks() {

    return tasks;

  }

  public void SaveTask( ArrayList<Task> tasks ) {

    ArrayList<String> lines = new ArrayList<String>();

    for (Task task : tasks) {
      
      String template = "{0} @@@ {1} @@@ {2} @@@ {3}";
  
      String formattedTask = MessageFormat.format( template, task.getId(), task.getName(), task.getStatus(), task.getDescription() );
  
      lines.add(formattedTask);

    }

    try {

      Files.write(dataFilePath, lines);
      System.out.println("Tasks Saved");

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
