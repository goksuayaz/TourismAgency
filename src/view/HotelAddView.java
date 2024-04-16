package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class HotelAddView extends Layout {
    private JPanel wrap;
    private JLabel lbl_otel_save;
    private JTextField tf_name;
    private JTextField tf_mail;
    private JTextField tf_phone;
    private JTextField tf_adress;
    private JPanel pnl_left;
    private JRadioButton rbut_carpark;
    private JRadioButton rbut_wifi;
    private JRadioButton rbut_swim;
    private JRadioButton rbut_gym;
    private JRadioButton rbut_concierge;
    private JRadioButton rbut_spa;
    private JRadioButton rbut_roomservices;
    private JButton btn_save_add_menu;
    private JComboBox tf_star;
    private Hotel hotel;
    private HotelManager hotelManager;



    public HotelAddView() {
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.add(wrap);
        this.guiInitilaze(500, 500);
        if(this.hotel.getHotel_id() != 0) {
            this.tf_name.setText(this.hotel.getHotel_name());
            this.tf_mail.setText(this.hotel.getHotel_mail());
            this.tf_phone.setText(this.hotel.getHotel_phone());
            this.tf_adress.setText(this.hotel.getHotel_adress());
            this.tf_star.setSelectedItem(this.hotel.getHotel_star());
        }
        btn_save_add_menu.addActionListener(e -> {

            JTextField[] checkFieldList = {this.tf_name, this.tf_mail, this.tf_phone, this.tf_adress};

            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");

            } else {
                boolean result = true;
                this.hotel.setHotel_name(this.tf_name.getText());
                this.hotel.setHotel_mail(this.tf_mail.getText());
                this.hotel.setHotel_phone(this.tf_phone.getText());
                this.hotel.setHotel_adress(this.tf_adress.getText());
                this.hotel.setHotel_star((String)this.tf_star.getSelectedItem());
                this.hotel.setHotel_carkpark(this.rbut_carpark.isSelected());
                this.hotel.setHotel_wifi(this.rbut_wifi.isSelected());
                this.hotel.setHotel_pool(this.rbut_swim.isSelected());
                this.hotel.setHotel_fitness(this.rbut_gym.isSelected());
                this.hotel.setHotel_concierge(this.rbut_concierge.isSelected());
                this.hotel.setHotel_spa(this.rbut_spa.isSelected());
                this.hotel.setHotel_roomservice(this.rbut_roomservices.isSelected());
                result = this.hotelManager.save(hotel);
                if (result) {
                    Helper.showMsg("done");

                    dispose();

                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}




