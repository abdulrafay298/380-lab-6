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
            task.setCompleted(true);
            undoStack.push(() -> task.setCompleted(false));
        }
    }

    public void deleteTask(String id) {
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            undoStack.push(() -> tasks.add(task));
        }
    }

    public void editTask(String id, String newTitle, boolean isCompleted) {
        Task task = findTaskById(id);
        if (task != null) {
            String oldTitle = task.getTitle();
            boolean oldCompleted = task.isCompleted();
            task.setTitle(newTitle);
            task.setCompleted(isCompleted);
            undoStack.push(() -> {
                task.setTitle(oldTitle);
                task.setCompleted(oldCompleted);
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
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
