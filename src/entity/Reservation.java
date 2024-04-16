package entity;

import java.time.LocalDate;

public class Reservation {

    public int id;
    public int room_id;
    public LocalDate check_in_date;
    public LocalDate check_out_date;
    public double total_price;
    public int guest_count;
    public String guest_name;
    public String guest_citizen_id;
    public String guest_phone;
    public String guest_mail;
    private int adult_count;
    private int child_count;

    public Reservation() {
    }

    public Reservation(int id, int room_id, LocalDate check_in_date, LocalDate check_out_date, double total_price, int guest_count, String guest_name, String guest_citizen_id, String guest_phone, String guest_mail) {
        this.id = id;
        this.room_id = room_id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.total_price = total_price;
        this.guest_count = guest_count;
        this.guest_name = guest_name;
        this.guest_citizen_id = guest_citizen_id;
        this.guest_phone = guest_phone;
        this.guest_mail = guest_mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getGuest_count() {
        return guest_count;
    }

    public void setGuest_count(int guest_count) {
        this.guest_count = guest_count;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_citizen_id() {
        return guest_citizen_id;
    }

    public void setGuest_citizen_id(String guest_citizen_id) {
        this.guest_citizen_id = guest_citizen_id;
    }

    public String getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(String guest_phone) {
        this.guest_phone = guest_phone;
    }

    public String getGuest_mail() {
        return guest_mail;
    }

    public void setGuest_mail(String guest_mail) {
        this.guest_mail = guest_mail;
    }

    public int getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(int adult_count) {
        this.adult_count = adult_count;
    }

    public int getChild_count() {
        return child_count;
    }

    public void setChild_count(int child_count) {
        this.child_count = child_count;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", total_price=" + total_price +
                ", guest_count=" + guest_count +
                ", guest_name='" + guest_name + '\'' +
                ", guest_citizen_id='" + guest_citizen_id + '\'' +
                ", guest_phone='" + guest_phone + '\'' +
                ", guest_mail='" + guest_mail + '\'' +
                '}';
    }
}
