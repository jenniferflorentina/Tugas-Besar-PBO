/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.Helper.ConstantStyle;
import model.Enums.TipeUserEnum;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.PersonManager;
/**
 *
 * @author Jennifer Florentina
 */
public class LogInScreen implements ItemListener, ActionListener{
    JFrame loginFrame = new JFrame("Login");
    JPanel textfieldPanel=new JPanel();
    JLabel usernameLabel, passwordLabel;
    JToggleButton eyeButton; 
    JTextField uname;
    JPasswordField pass;
    JButton submitButton;
    Image eye = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../asset/img/eye.png")); 
    Image noEye = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../asset/img/noeye.png"));
    
    public LogInScreen(){
       loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       loginFrame.setIconImage(ConstantStyle.icon);
       
       usernameLabel = new JLabel("Username     : ");
       usernameLabel.setBounds(20,20,180,50);
       usernameLabel.setFont(ConstantStyle.normal);
       
       passwordLabel = new JLabel("Password      : ");
       passwordLabel.setBounds(20,90,180,50);
       passwordLabel.setFont(ConstantStyle.normal);
       
       uname = new JTextField(100);
       uname.setBounds(180,20,380,50);
       uname.setFont(ConstantStyle.normal);
       uname.setBorder(null);
       
       pass = new JPasswordField(100);   
       pass.setBounds(180,90,330,50);
       pass.setEchoChar('\u25CF');
       pass.setFont(ConstantStyle.normal);
       pass.setBorder(null);
       
       eye = eye.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
       noEye = noEye.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
       eyeButton = new JToggleButton(new ImageIcon(eye));
       eyeButton.setBounds(510,90,50,50);
       eyeButton.addItemListener(this);
       eyeButton.setBackground(null);
       eyeButton.setFocusable(false);
       eyeButton.setFocusPainted(false);
       eyeButton.setBorder(null);
       
       textfieldPanel = new JPanel();
       textfieldPanel.setBounds(380, 150, 590, 170);
       textfieldPanel.setBackground(new Color(221,243,245));
       textfieldPanel.add(usernameLabel);
       textfieldPanel.add(uname);
       textfieldPanel.add(passwordLabel); 
       textfieldPanel.add(pass); 
       textfieldPanel.setLayout(null);
       textfieldPanel.add(eyeButton);
       
       submitButton = new JButton("Login");
       submitButton.setBounds(820,350,150,50);
       submitButton.addActionListener(this);
       submitButton.setFont(ConstantStyle.normal);
       
       loginFrame.add(textfieldPanel);
       loginFrame.add(submitButton);
       loginFrame.getContentPane().setBackground(Color.WHITE);
       loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       loginFrame.setLocationRelativeTo(null);
       loginFrame.setLayout(null);  
       loginFrame.setVisible(true);
       
       
       
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (eyeButton.isSelected()){  
            eyeButton.setIcon(new ImageIcon(noEye));
            pass.setEchoChar((char)0); 
        }else{  
            eyeButton.setIcon(new ImageIcon(eye));
            pass.setEchoChar('\u25CF');
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = uname.getText();
        String password = new String(pass.getPassword());
        if(username.equals("")&&password.equals("")){
            JOptionPane.showMessageDialog(null,"Insert username and password!","Alert",JOptionPane.WARNING_MESSAGE); 
        }else if(username.equals("")){
            JOptionPane.showMessageDialog(null,"Insert username!","Alert",JOptionPane.WARNING_MESSAGE);
        }else if(password.equals("")){
            JOptionPane.showMessageDialog(null,"Insert password!","Alert",JOptionPane.WARNING_MESSAGE);
        }else if(Controller.cekPassword(username,password)){
            PersonManager.getInstance().setPerson(Controller.getPerson(username));
            if(PersonManager.getInstance().getPerson().getTipeUser() == TipeUserEnum.ADMIN){
                loginFrame.dispose();
                new AdminMenuScreen();
            }else if(PersonManager.getInstance().getPerson().getTipeUser() == TipeUserEnum.MEMBER){
                loginFrame.dispose();
                new MemberMenuScreen();
            }else{
                loginFrame.dispose();
                new UserMenuScreen();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Insert username and password correctly!","Alert",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
}
