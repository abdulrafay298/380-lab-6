package edu.ucalgary.oop;

import java.util.Objects;

public interface IToDoList {
    // Method to add a new task
    void addTask(String task);
    
    // Method to mark a task as completed
    void completeTask(String task);
    
    // Method to delete a task
    void deleteTask(String task);
    
    // Method to edit a task
    void editTask(String oldTask, String newTask);
    
    // Method to undo the last action
    void undo();
    
    // Method to list all tasks
    void listTasks();
} 
// IToDoList
