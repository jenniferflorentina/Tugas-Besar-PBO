/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.Helper.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Jennifer Florentina
 */
public class StartScreen implements ActionListener{
    JFrame startFrame = new JFrame("Welcome to Our Hotel");
    JButton loginButton, registerButton;
    JLabel greetingImage, greetingText;
    
    public StartScreen(){
       startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       startFrame.setIconImage(ConstantStyle.icon);  
       loginButton = new JButton("Log In");
       loginButton.setBounds(600,450,150,50);
       loginButton.addActionListener(this);
       loginButton.setFont(view.Helper.ConstantStyle.normal);
       
       registerButton = new JButton("Registrasi");
       registerButton.setBounds(600,550,150,50);
       registerButton.addActionListener(this);
       registerButton.setFont(ConstantStyle.normal);
       
       Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../asset/img/logo.png"));
       greetingImage = new JLabel(new ImageIcon(logo));
       greetingImage.setBounds(380,100,200,200);
       greetingText = new JLabel("Welcome to HOTEL!");
       greetingText.setBounds(650,100,500,200);
       greetingText.setFont(new Font("Verdana", Font.PLAIN, 32));
       
       startFrame.add(greetingImage);
       startFrame.add(greetingText);
       startFrame.getContentPane().add(loginButton); // Adds Button to content pane of frame
       startFrame.getContentPane().add(registerButton); // Adds Button to content pane of frame
       startFrame.getContentPane().setBackground(Color.WHITE);
       startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       startFrame.setLocationRelativeTo(null);
       startFrame.setLayout(null);  
       startFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        switch(choice){
            case "Log In":
                startFrame.dispose();
                new LogInScreen();
                break;
            case "Registrasi":
                startFrame.dispose();
                new RegisterScreen();
                break;
        }}
}
