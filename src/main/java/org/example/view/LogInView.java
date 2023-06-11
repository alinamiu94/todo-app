package org.example.view;

import org.example.repository.UserRepository;
import org.example.model.User;
import org.mindrot.jbcrypt.BCrypt;



public class LogInView extends View{
    public void show() {
        System.out.println("Log in!");
    }


    public void logIn() {
        System.out.println("Enter your user name: ");
        String s = getUserInputString();

        System.out.println("Enter your password: ");
        String p = getUserInputString();
        UserRepository userRepository = new UserRepository();
        User user = userRepository.findUserByUserName(s);

        if(user == null) {
            System.out.println("User doesn't exist!");
            HomeView homeView = new HomeView();
            homeView.show();
            homeView.goToSelectedView();
        } else {
            if(BCrypt.checkpw(p,user.getPassword())) {
                System.out.println("The password is valid.");
                TaskMenuView taskMenuView = new TaskMenuView(user.getId());
                taskMenuView.show();
                taskMenuView.goToSelectedView();

            } else {
                System.out.println("Password is not correct.");
                HomeView homeView = new HomeView();
                homeView.show();
                homeView.goToSelectedView();

            }

        }
    }






}
