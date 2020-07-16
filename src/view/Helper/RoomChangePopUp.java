/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Helper;

import controller.DataController;
import static controller.DataController.listHotel;
import controller.RoomController;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import model.Room;
import model.TransactionManager;

/**
 *
 * @author Jennifer Florentina
 */
public class RoomChangePopUp implements ActionListener{
    JFrame roomChangePopUpFrame = new JFrame("Change Room");
    JLabel dataHotel, judulPilihRoom;
    ArrayList<JRadioButton> listRButtonRoom = new ArrayList<>();
    ButtonGroup buttonGroup =new ButtonGroup();
    JButton nextButton;
    
    public RoomChangePopUp() {
        roomChangePopUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        roomChangePopUpFrame.setIconImage(ConstantStyle.icon);  
        
        String str = "<html><pre>"+listHotel.get(TransactionManager.getInstance().getTransaction().getIdHotel()-1).toString()+"</pre></html>";
        dataHotel = new JLabel(str);
        dataHotel.setBounds(10,10,600,150);
        dataHotel.setFont(ConstantStyle.normal);
        
        judulPilihRoom = new JLabel("Jenis Room Tersedia :");
        judulPilihRoom.setBounds(10,150,600,30);
        judulPilihRoom.setFont(ConstantStyle.normal);
        
        int height = 200;
        ArrayList<Room> listRoom = listHotel.get(TransactionManager.getInstance().getTransaction().getIdHotel()-1).getRoomList();
        ArrayList<String> listStringRoom = new ArrayList<>();
        for (int i = 0; i < listRoom.size(); i++) {
            if(!listStringRoom.contains(listRoom.get(i).getTipe())){
                listStringRoom.add(listRoom.get(i).getTipe());
            }
        }
        for (int i = 0; i < listStringRoom.size(); i++) {
            listRButtonRoom.add(new JRadioButton(listStringRoom.get(i)));
            listRButtonRoom.get(i).setFont(ConstantStyle.small);
            listRButtonRoom.get(i).setBounds(20,height,200,35);
            height+=40;
            buttonGroup.add(listRButtonRoom.get(i));
            roomChangePopUpFrame.add(listRButtonRoom.get(i)); 
        }
        nextButton = new JButton("Next >>");
        nextButton.setBounds(500,450,150,30);
        nextButton.addActionListener(this);

        roomChangePopUpFrame.add(dataHotel); 
        roomChangePopUpFrame.add(judulPilihRoom); 
        roomChangePopUpFrame.add(nextButton); 
        roomChangePopUpFrame.setSize(700, 650); 
        roomChangePopUpFrame.setLocationRelativeTo(null);
        roomChangePopUpFrame.setLayout(null);  
        roomChangePopUpFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int a=JOptionPane.showOptionDialog(null,"Are you sure?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(a==JOptionPane.YES_OPTION){
            String tipe ="";
            for (int i = 0; i < listRButtonRoom.size(); i++) {
                if(listRButtonRoom.get(i).isSelected()){
                   tipe = listRButtonRoom.get(i).getActionCommand();
                   break;
                }
            }
            if(!tipe.equals("")){
                ArrayList<Room> listRoomKosong = RoomController.cekRoomKosong(TransactionManager.getInstance().getTransaction().getIdHotel(), tipe);
                if(listRoomKosong.isEmpty()){
                    JOptionPane.showMessageDialog(null,"No Room Available!","Alert",JOptionPane.WARNING_MESSAGE);
                }else{
                    roomChangePopUpFrame.dispose();
                    new ChooseRoomPopUp(listRoomKosong,0);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Choose New Type Room!","Alert",JOptionPane.WARNING_MESSAGE);
            }
        } 
    }
}
