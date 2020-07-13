/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author Jennifer Florentina
 */
public class ConstantStyle {
    public static Image icon = Toolkit.getDefaultToolkit().getImage(ConstantStyle.class.getResource("../img/hotel.jpg"));  
    public static Font normal = new Font("Verdana", Font.PLAIN, 20);
    public static Font small = new Font("Verdana", Font.PLAIN, 14);
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy"); 
    public static NumberFormat kurensiIndonesia = NumberFormat.getCurrencyInstance(new Locale("in","ID"));

}
