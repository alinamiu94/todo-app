package org.example.view;

import org.example.repository.TaskRepository;
import org.example.enums.Status;
import org.example.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AddTaskView extends View{

    private int userId;

    AddTaskView(int userId) {
        this.userId = userId;
    }
    public void show() {
        System.out.println("Add task: ");
    }
    public void addTask() {
        System.out.println("Enter task name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Enter task description: ");
        String description = scanner.nextLine();

        System.out.println("Enter task due date(dd/MM/yyyy): ");
        String dueDate = scanner.nextLine();

        Date date = null;

        try {
             date = new SimpleDateFormat("dd/MM/yyyy").parse(dueDate);
        } catch (ParseException parseException) {
            System.out.println("You entered an invalid format for date!");
            TaskMenuView taskMenuView= new TaskMenuView(userId);
            taskMenuView.show();
            taskMenuView.goToSelectedView();
        }

        TaskRepository taskRepository = new TaskRepository();
        Task task1 = new Task(name,description,userId,date, Status.TO_DO,new Date(),null);
        taskRepository.addTask(task1);
        System.out.println("Task added successfully!");
        TaskMenuView taskMenuView = new TaskMenuView(userId);
        taskMenuView.show();
        taskMenuView.goToSelectedView();
    }

}
