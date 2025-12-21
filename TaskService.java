import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TaskService {

    private static final String FILE_NAME = "tasks.json";
    private ArrayList<Task> tasks;
    private Gson gson;

    public TaskService() {
        gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .setPrettyPrinting()
        .create();
        tasks = loadTasks();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markComplete();
            return true;
        }
        return false;
    }

    public void save() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private ArrayList<Task> loadTasks() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();
            ArrayList<Task> loaded = gson.fromJson(reader, taskListType);
            return loaded != null ? loaded : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
