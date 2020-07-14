/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.DataController.listHotel;
import java.sql.*;
import java.util.ArrayList;
import model.Enums.BookingEnum;
import model.Room;

/**
 *
 * @author Jennifer Florentina
 */
public class RoomController {
    static DatabaseHandler conn = new DatabaseHandler();
    
    public static ArrayList<Room> cekRoomKosong(int idHotel, String tipe){
        ArrayList<Room> listRoomKosong = new ArrayList<>();
        
        for (int i = 0; i < listHotel.get(idHotel).getRoomList().size(); i++) {
            if(listHotel.get(idHotel-1).getRoomList().get(i).getTipe().equals(tipe)){
                if(!cekAdaTransaksi(idHotel,listHotel.get(idHotel-1).getRoomList().get(i).getNoKamar())){
                    listRoomKosong.add(listHotel.get(idHotel-1).getRoomList().get(i));
                }
            }
        }
       
        return (listRoomKosong);
    }
    
    public static boolean cekAdaTransaksi(int idHotel, int noKamar){
        conn.connect();
        boolean isExist = false;
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);
        System.out.println(date+" "+noKamar+" ");
        String query = "SELECT * FROM booking_transaksi WHERE idHotel = "+idHotel+" AND noKamar = "+noKamar
                +" AND (status = '"+BookingEnum.BOOKED.toString()+"' OR status = '"+BookingEnum.CHECKEDIN.toString()+"')"
                +" AND check_in <= '"+date+"'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
               isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (isExist);
    }
}
