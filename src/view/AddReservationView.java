package view;

import business.ReservationManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AddReservationView extends Layout {
    private JPanel wrapper;
    private JTextField tf_res_hotel_name;
    private JTextField tf_res_city;
    private JTextField tf_res_star;
    private JRadioButton rb_gym;
    private JButton btn_reservation_add;
    private JRadioButton rb_carpark;
    private JRadioButton rb_wifi;
    private JRadioButton rb_swim_pool;
    private JRadioButton rb_spa;
    private JRadioButton rb_room_services;
    private JTextField tf_res_roomtype;
    private JTextField tf_res_room_field;
    private JTextField tf_res_bed_capacity;
    private JTextField tf_res_start_date;
    private JTextField tf_res_finish_date;
    private JRadioButton rb_television;
    private JRadioButton rb_game_console;
    private JRadioButton rb_cash_vault;
    private JRadioButton rb_res_projection;
    private JRadioButton rb_res_minibar;
    private JTextField tf_res_total_amount;
    private JTextField tf_res_total_guest_number;
    private JTextField tf_res_guess_mail;
    private JTextField tf_res_guess_name;
    private JTextField tf_res_guess_tel_no;
    private JTextField tf_res_guess_id_no;
    private JTextField tf_res_pension;
    private ReservationManager reservationManager = new ReservationManager();
    private Season season;
    private Pension pension;
    private Reservation reservation;
    private SeasonManager seasonManager;
    private RoomManager roomManager;

    private Room room;
    private String check_in_date;
    private String check_out_date;
    private Double adult_price;
    private Double child_price;



    public AddReservationView(Room room, String check_in_date, String check_out_date, int adult_numb, int child_numb, Reservation reservation) {


        this.add(wrapper);
        this.guiInitilaze(1000, 1000);
        this.wrapper = wrapper;


        this.room = room;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;


        if (this.reservation == null) ;
        {
            this.reservation = new Reservation();
            this.roomManager = new RoomManager();

        }
        int guest_count = adult_numb + child_numb;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate checkindate = LocalDate.parse(check_in_date, formatter);
        LocalDate checkoutdate = LocalDate.parse(check_out_date, formatter);
        long day_count = ChronoUnit.DAYS.between(checkindate, checkoutdate);


        double total_price = ((this.room.getAdult_price() * adult_numb)+ this.room.getChild_price() * child_numb) * day_count;



        this.tf_res_hotel_name.setText(this.room.getHotel().getHotel_name());
        this.tf_res_city.setText(this.room.getHotel().getHotel_adress());
        this.tf_res_star.setText(this.room.getHotel().getHotel_star());
        this.rb_carpark.setSelected(this.room.getHotel().isHotel_carkpark());
        this.rb_wifi.setSelected(this.room.getHotel().isHotel_wifi());
        this.rb_swim_pool.setSelected(this.room.getHotel().isHotel_pool());
        this.rb_gym.setSelected(this.room.getHotel().isHotel_fitness());
        this.rb_spa.setSelected(this.room.getHotel().isHotel_spa());
        this.rb_room_services.setSelected(this.room.getHotel().isHotel_roomservice());
        this.tf_res_roomtype.setText(this.room.getType());
        this.tf_res_bed_capacity.setText(String.valueOf(this.room.getBed_capacity()));
        this.tf_res_pension.setText(this.room.getPension().getPension_type());
        this.tf_res_room_field.setText(String.valueOf(this.room.getSquare_meter()));
        this.tf_res_start_date.setText(String.valueOf(this.check_in_date));

        this.tf_res_finish_date.setText(String.valueOf(this.check_out_date));
        this.rb_television.setSelected(this.room.isTelevision());
        this.rb_game_console.setSelected(this.room.isGame_console());
        this.rb_cash_vault.setSelected(this.room.isCash_box());
        this.rb_res_projection.setSelected(this.room.isProjection());
        this.rb_res_minibar.setSelected(this.room.isMinibar());
        this.tf_res_total_amount.setText(String.valueOf(total_price));
        this.tf_res_total_guest_number.setText(String.valueOf(guest_count));


        btn_reservation_add.addActionListener(e -> {
            JTextField[] checkfieldEmpty = {this.tf_res_guess_name,this.tf_res_guess_id_no,this.tf_res_guess_mail,this.tf_res_guess_tel_no};
            if (Helper.isFieldListEmpty(checkfieldEmpty)){
                Helper.showMsg("fill");
            }else{
                boolean result;

                // Rezervasyon bilgilerini atama
                this.reservation.setTotal_price(Double.parseDouble(this.tf_res_total_amount.getText()));
                this.reservation.setGuest_count(Integer.parseInt(this.tf_res_total_guest_number.getText()));
                this.reservation.setGuest_name(this.tf_res_guess_name.getText());
                this.reservation.setGuest_citizen_id(this.tf_res_guess_id_no.getText());
                this.reservation.setGuest_mail(this.tf_res_guess_mail.getText());
                this.reservation.setGuest_phone(this.tf_res_guess_tel_no.getText());
                this.reservation.setRoom_id(this.room.getId());
                this.reservation.setCheck_in_date(LocalDate.parse(this.check_in_date,formatter));
                this.reservation.setCheck_out_date(LocalDate.parse(this.check_out_date,formatter));

                result = this.reservationManager.save(this.reservation);
                if (result){
                    Helper.showMsg("done");

                   // this.roomManager.getById(this.room.setStock(this.room.getStock()-1));
                    this.roomManager.updateStock(this.room);
                    dispose();
                }else {
                    Helper.showMsg("error");
                }

            }
        });
    }
}

