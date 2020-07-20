package view.Helper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controller.CheckController;
import controller.DataController;
import static controller.DataController.insertBarangRusak;
import controller.PembayaranController;
import static controller.PembayaranController.getPersonById;
import static controller.PembayaranController.updatePoin;
import controller.RoomController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.Barang;
import model.Enums.BookingEnum;
import model.Member;
import model.Person;
import model.Transaction;
import model.TransactionManager;
import view.AdminMenuScreen;
import view.TransactionViewScreen;

/**
 *
 * @author Jennifer Florentina
 */
public class CheckOutPopUp implements ActionListener {

    JFrame checkOutPopUpFrame = new JFrame("Check Out Payment");
    JPanel panelBarang, panelPembayaran;
    JLabel judulBagianBarang, judulBagianPembayaran;
    ArrayList<JLabel> listLabelBarang = new ArrayList<>();
    ArrayList<JSpinner> listSpinnerBarang = new ArrayList<>();
    ArrayList<JRadioButton> listRButtonPembayaran = new ArrayList<>();
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton nextButton, calculateButton;
    int penanda, noKamar;
    JCheckBox cbMember;
    Person person = getPersonById(TransactionManager.getInstance().getTransaction().getIdUser());
    
    public CheckOutPopUp(int penanda, int noKamar) {
        this.penanda = penanda;
        this.noKamar = noKamar;
        checkOutPopUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        checkOutPopUpFrame.setIconImage(ConstantStyle.icon);

        panelBarang = new JPanel();
        panelBarang.setBounds(0, 0, 900, 350);
        panelBarang.setLayout(null);

        judulBagianBarang = new JLabel("Barang Rusak : ");
        judulBagianBarang.setBounds(20, 15, 300, 40);
        judulBagianBarang.setFont(ConstantStyle.normal);

        int height = 60;
        for (int i = 0; i < DataController.listBarang.size(); i++) {
            String namaBarang = DataController.listBarang.get(i).getNamaBarang();
            listLabelBarang.add(new JLabel(namaBarang));
            listLabelBarang.get(i).setFont(ConstantStyle.small);
            listLabelBarang.get(i).setBounds(20, height, 100, 30);
            SpinnerModel value = new SpinnerNumberModel(0, 0, DataController.listBarang.get(i).getJumlah(), 1);
            listSpinnerBarang.add(new JSpinner(value));
            listSpinnerBarang.get(i).setFont(ConstantStyle.small);
            listSpinnerBarang.get(i).setBounds(140, height, 100, 30);
            height += 38;
            panelBarang.add(listLabelBarang.get(i));
            panelBarang.add(listSpinnerBarang.get(i));
        }
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(500, 280, 150, 30);
        calculateButton.addActionListener(this);

        panelBarang.add(judulBagianBarang);
        panelBarang.add(calculateButton);

        panelPembayaran = new JPanel();
        panelPembayaran.setBounds(0, 350, 900, 380);
        panelPembayaran.setLayout(null);

        judulBagianPembayaran = new JLabel("Pembayaran : ");
        judulBagianPembayaran.setBounds(20, 15, 300, 40);
        judulBagianPembayaran.setFont(ConstantStyle.normal);

        height = 60;
        for (int i = 0; i < DataController.listJenisPembayaran.size(); i++) {
            String jenisBayar = DataController.listJenisPembayaran.get(i).getJenis();
            listRButtonPembayaran.add(new JRadioButton(jenisBayar));
            listRButtonPembayaran.get(i).setFont(ConstantStyle.small);
            listRButtonPembayaran.get(i).setBounds(20, height, 200, 30);
            height += 38;
            buttonGroup.add(listRButtonPembayaran.get(i));
            panelPembayaran.add(listRButtonPembayaran.get(i));
        }
        
        if (person instanceof Member) {
           cbMember=new JCheckBox("Use Member Poin");  
           cbMember.setBounds(20,height,200,30);   
           cbMember.setFont(ConstantStyle.small);
           panelPembayaran.add(cbMember);
        }
        
        nextButton = new JButton("Next >>");
        nextButton.setBounds(500, 320, 150, 30);
        nextButton.setEnabled(false);
        nextButton.addActionListener(this);

        panelPembayaran.add(judulBagianPembayaran);
        panelPembayaran.add(nextButton);
        panelPembayaran.setBackground(Color.WHITE);

        checkOutPopUpFrame.add(panelBarang);
        checkOutPopUpFrame.add(panelPembayaran);
        checkOutPopUpFrame.setSize(700, 750);
        checkOutPopUpFrame.setLocationRelativeTo(null);
        checkOutPopUpFrame.setLayout(null);
        checkOutPopUpFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        switch (choice) {
            case "Calculate":
                int a = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (a == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < listSpinnerBarang.size(); i++) {
                        if ((int) listSpinnerBarang.get(i).getValue() > 0) {
                            Barang barang = new Barang(DataController.listBarang.get(i).getIdBarang(), DataController.listBarang.get(i).getHarga(), (int) listSpinnerBarang.get(i).getValue(), DataController.listBarang.get(i).getNamaBarang());
                            TransactionManager.getInstance().getTransaction().addBarangRusak(barang);
                            insertBarangRusak(DataController.listBarang.get(i).getIdBarang(), TransactionManager.getInstance().getTransaction().getIdTransaksi(), (int) listSpinnerBarang.get(i).getValue());
                        }
                    }
                    JOptionPane.showMessageDialog(null, ConstantStyle.kurensiIndonesia.format(TransactionManager.getInstance().getTransaction().getRawBill()));
                    nextButton.setEnabled(true);
                }
                break;
            case "Next >>":
                nextButtonAction();
                break;
        }
    }

    public void nextButtonAction() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Transaction oldTransaction = TransactionManager.getInstance().getTransaction();
        Transaction newTransaction = new Transaction(oldTransaction.getIdHotel(), oldTransaction.getIdJenisPembayaran(), oldTransaction.getIdUser(), noKamar,
                oldTransaction.getJumlahGuest(), oldTransaction.getUangMuka() - oldTransaction.getUangMuka() / 2, date, oldTransaction.getTanggalCheckOut(), date, BookingEnum.CHECKEDIN);
        if (penanda == 1 && noKamar != 0) {
            RoomController.makeCheckOutTransaction(oldTransaction.getIdTransaksi(), oldTransaction.getUangMuka());
        }
        int a = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (a == JOptionPane.YES_OPTION) {
            boolean pilihan = false;
            for (int i = 0; i < listRButtonPembayaran.size(); i++) {
                if (listRButtonPembayaran.get(i).isSelected()) {
                    TransactionManager.getInstance().getTransaction().setIdJenisPembayaran(i + 1);
                    pilihan = true;
                    break;
                }
            }
            if (pilihan) {
                JOptionPane.showMessageDialog(null, TransactionManager.getInstance().getTransaction().printBill());
                if (person instanceof Member) {
                    if(cbMember.isSelected()){  
                        int diskonPoinMember =  controller.PembayaranController.usePoinMember(TransactionManager.getInstance().getTransaction().getIdUser());
                        JOptionPane.showMessageDialog(null, "Diskon poin member : "+ ConstantStyle.kurensiIndonesia.format(diskonPoinMember) + "\nTotal Bayar : "+ConstantStyle.kurensiIndonesia.format(TransactionManager.getInstance().getTransaction().getBill()-diskonPoinMember));
                    } 
                }
                a = JOptionPane.showOptionDialog(null, "Lanjutkan Pembayaran?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (a == JOptionPane.YES_OPTION) {
                    if (!PembayaranController.updatePembayaran(TransactionManager.getInstance().getTransaction().getIdTransaksi(), TransactionManager.getInstance().getTransaction().getIdJenisPembayaran())) {
                        JOptionPane.showMessageDialog(null, "Failed to make payment!", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                    if (!CheckController.updateCheckOut(TransactionManager.getInstance().getTransaction().getIdTransaksi())) {
                        JOptionPane.showMessageDialog(null, "Failed to make payment!", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Payment succeed!!");
                        controller.PembayaranController.tambahPoinMember(TransactionManager.getInstance().getTransaction().getIdUser());
                        JOptionPane.showMessageDialog(null, ConstantStyle.kurensiIndonesia.format(TransactionManager.getInstance().getTransaction().getBill()));
                    }
                    checkOutPopUpFrame.dispose();
                    if (penanda == 1 && noKamar != 0) {
                        JOptionPane.showMessageDialog(null, "Make new transaction for new room!!");
                        if (RoomController.makeNewTransaction(newTransaction, BookingEnum.CHECKEDIN)) {
                            JOptionPane.showMessageDialog(null, "Update Transaction Succeed!!");
                            new TransactionViewScreen();
                            new AdminMenuScreen();
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Choose payment!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
