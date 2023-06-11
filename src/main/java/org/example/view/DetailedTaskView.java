package org.example.view;

import org.example.repository.TaskRepository;
import org.example.enums.Status;
import org.example.model.Task;

import java.util.Date;

public class DetailedTaskView extends View{
    private int taskId;
    private int userId;

    public DetailedTaskView(int taskId, int userId) {
        this.taskId = taskId;
        this.userId = userId;
    }

    public void show() {
        System.out.println("Detailed task.");
        TaskRepository taskRepository = new TaskRepository();
        Task task = taskRepository.getTaskById(taskId);
        System.out.println(task);
        System.out.println("1. Delete task: ");
        System.out.println("2. Change status to in progress: ");
        System.out.println("3. Change status to done: ");
        System.out.println("4. Go to task menu view ");

        int s = getUserInputInt();

        if(s == 1) {
            taskRepository.deleteTask(taskId);
            ViewTasks viewTasks = new ViewTasks(userId);
            viewTasks.show();
            viewTasks.viewAllTasks();
        } else if(s == 2) {
            taskRepository.updateStatus(Status.IN_PROGRESS, taskId);
            show();
        } else if(s == 3) {
            taskRepository.updateStatus(Status.DONE,taskId);
            taskRepository.updateFinishedAtByTaskId(new Date(),taskId);
            show();
        } else {
            TaskMenuView taskMenuView = new TaskMenuView(userId);
            taskMenuView.show();
            taskMenuView.goToSelectedView();
        }
    }


}
