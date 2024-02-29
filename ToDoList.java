package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ToDoList {
    private List<Task> tasks;
    private Stack<Runnable> undoStack;

    public ToDoList() {
        this.tasks = new ArrayList<>();
        this.undoStack = new Stack<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        undoStack.push(() -> tasks.remove(task));
    }

    public void completeTask(String id) {
        Task task = findTaskById(id);
        if (task != null) {
            boolean oldComplete = task.isComplete;
            task.isComplete = true;
            undoStack.push(() -> task.isComplete = oldComplete);
        }
    }

    public void deleteTask(String id) {
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            undoStack.push(() -> tasks.add(task));
        }
    }

    public void editTask(String id, String newTitle, boolean isComplete) {
        Task task = findTaskById(id);
        if (task != null) {
            String oldTitle = task.title;
            boolean oldCompleted = task.isComplete;
            task.title = newTitle;
            task.isComplete = isComplete;
            undoStack.push(() -> {
                task.title = oldTitle;
                task.isComplete = oldCompleted;
            });
        }
    }

    public List<Task> listTasks() {
        return tasks;
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            undoStack.pop().run();
        }
    }

    private Task findTaskById(String id) {
        return tasks.stream()
                .filter(task -> task.id.equals(id))
                .findFirst()
                .orElse(null);
}
}
