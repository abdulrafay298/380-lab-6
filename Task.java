package edu.ucalgary.oop;

import java.util.Objects;

public class Task {
    private String id;
    private String title;
    private boolean isComplete;

    public Task(String id, String title, boolean isComplete) {
        this.id = id;
        this.title = title;
        this.isComplete = isComplete;
    }

    // Deep copy constructor
    public Task(Task original) {
        this.id = original.id;
        this.title = original.title;
        this.isComplete = original.isComplete;
    }

    // Deep copy method
    public Task copy() {
        return new Task(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return Objects.equals(id, task.id) &&
               Objects.equals(title, task.title) &&
               isComplete == task.isComplete;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isComplete);
    }
}
