package org.example.view;



public class TaskMenuView extends View{

    private int userId;

    TaskMenuView(int userId) {
        this.userId = userId;
    }
    public void show() {
        System.out.println("Task menu");
        System.out.println("1. Add task");
        System.out.println("2. View tasks");
        System.out.println("3. Back to home page.");
    }


    public void goToSelectedView() {
        int i = getUserInputInt();
        if(i == 1) {
            AddTaskView addTaskView = new AddTaskView(userId);
            addTaskView.show();
            addTaskView.addTask();
        } else if (i == 2) {
         ViewTasks viewTasks = new ViewTasks(userId);
         viewTasks.show();
         viewTasks.viewAllTasks();
        } else {
            HomeView homeView = new HomeView();
            homeView.show();
            homeView.goToSelectedView();
        }
    }
}
