package org.example.view;

import org.example.repository.TaskRepository;
import org.example.model.Task;

import java.util.List;


public class ViewTasks extends View{

    private int userId ;

    ViewTasks(int userId) {
        this.userId = userId;
    }
    public void show() {
        System.out.println("View tasks.");
    }
    public void viewAllTasks() {
        TaskRepository taskRepository = new TaskRepository();
        List<Task> taskList = taskRepository.getTasksBYUserId(userId);
        taskList.stream().forEach(t -> System.out.println(t.getId() + " " + t.getName() + " " + t.getStatus()));
        System.out.println("Enter 0 to go back to task menu view or enter task id to view details: ");

        int s = getUserInputInt();
        if(s == 0) {
            TaskMenuView taskMenuView = new TaskMenuView(userId);
            taskMenuView.show();
            taskMenuView.goToSelectedView();
        } else {
            DetailedTaskView detailedTaskView = new DetailedTaskView(s,userId);
            detailedTaskView.show();
        }


    }
}
