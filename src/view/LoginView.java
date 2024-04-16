package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel l_top;
    private JLabel l_welcome;
    private JLabel l_management;
    private JPanel l_button;
    private JTextField username_log;
    private JPasswordField pass_log;
    private JButton log_button;
    private JLabel l_username;
    private JLabel l_pass;
    private final UserManager userManager;

    public LoginView(){
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(400,400);


        log_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.isFieldListEmpty(new JTextField[]{username_log, pass_log})){
                    Helper.showMsg("fill");
                }else{
                    User loginUser = userManager.findByLogin(username_log.getText(), pass_log.getText());
                    if(loginUser == null){
                        Helper.showMsg("User Not Found");
                    }else{

                        if(loginUser.getRole().equals("admin")){
                            AdminView adminView = new AdminView(loginUser);
                            dispose();
                        }
                        else{
                            EmployeeView employeeView = new EmployeeView(loginUser);
                            dispose();
                        }
                    }
                }


            }
        });
    }
}
