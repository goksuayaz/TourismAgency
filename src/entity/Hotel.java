package entity;

import business.HotelManager;
import core.ComboItem;

public class Hotel {

    private int hotel_id;

    private String hotel_name;

    private String hotel_adress;

    private String hotel_mail;

    private String hotel_phone;

    private String hotel_star;

    private boolean hotel_carkpark;

    private boolean hotel_wifi;

    private boolean hotel_pool;

    private boolean hotel_fitness;

    private boolean hotel_concierge;

    private boolean hotel_spa;

    private boolean hotel_roomservice;

    private Pension pension;

    private HotelManager hotelManager;

    public Hotel(){

    }

    public Hotel(int hotel_id, String hotel_name, String hotel_adress, String hotel_mail, String hotel_phone, String hotel_star, boolean hotel_carkpark, boolean hotel_wifi, boolean hotel_pool, boolean hotel_fitness, boolean hotel_concierge, boolean hotel_spa, boolean hotel_roomservice) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_adress = hotel_adress;
        this.hotel_mail = hotel_mail;
        this.hotel_phone = hotel_phone;
        this.hotel_star = hotel_star;
        this.hotel_carkpark = hotel_carkpark;
        this.hotel_wifi = hotel_wifi;
        this.hotel_pool = hotel_pool;
        this.hotel_fitness = hotel_fitness;
        this.hotel_concierge = hotel_concierge;
        this.hotel_spa = hotel_spa;
        this.hotel_roomservice = hotel_roomservice;
        HotelManager hotelManager = new HotelManager();
        Pension pension = new Pension();
    }

    //Getter and Setter
    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_adress() {
        return hotel_adress;
    }

    public void setHotel_adress(String hotel_adress) {
        this.hotel_adress = hotel_adress;
    }

    public String getHotel_mail() {
        return hotel_mail;
    }

    public void setHotel_mail(String hotel_mail) {
        this.hotel_mail = hotel_mail;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_star() {
        return hotel_star;
    }

    public void setHotel_star(String hotel_star) {
        this.hotel_star = hotel_star;
    }

    public boolean isHotel_carkpark() {
        return hotel_carkpark;
    }

    public void setHotel_carkpark(boolean hotel_carkpark) {
        this.hotel_carkpark = hotel_carkpark;
    }

    public boolean isHotel_wifi() {
        return hotel_wifi;
    }

    public void setHotel_wifi(boolean hotel_wifi) {
        this.hotel_wifi = hotel_wifi;
    }

    public boolean isHotel_pool() {
        return hotel_pool;
    }

    public void setHotel_pool(boolean hotel_pool) {
        this.hotel_pool = hotel_pool;
    }

    public boolean isHotel_fitness() {
        return hotel_fitness;
    }

    public void setHotel_fitness(boolean hotel_fitness) {
        this.hotel_fitness = hotel_fitness;
    }

    public boolean isHotel_concierge() {
        return hotel_concierge;
    }

    public void setHotel_concierge(boolean hotel_concierge) {
        this.hotel_concierge = hotel_concierge;
    }

    public boolean isHotel_spa() {
        return hotel_spa;
    }

    public void setHotel_spa(boolean hotel_spa) {
        this.hotel_spa = hotel_spa;
    }

    public boolean isHotel_roomservice() {
        return hotel_roomservice;
    }

    public void setHotel_roomservice(boolean hotel_roomservice) {
        this.hotel_roomservice = hotel_roomservice;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getHotel_id(), this.getHotel_name());
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", hotel_name='" + hotel_name + '\'' +
                ", hotel_adress='" + hotel_adress + '\'' +
                ", hotel_mail='" + hotel_mail + '\'' +
                ", hotel_phone='" + hotel_phone + '\'' +
                ", hotel_star='" + hotel_star + '\'' +
                ", hotel_carkpark=" + hotel_carkpark +
                ", hotel_wifi=" + hotel_wifi +
                ", hotel_pool=" + hotel_pool +
                ", hotel_fitness=" + hotel_fitness +
                ", hotel_concierge=" + hotel_concierge +
                ", hotel_spa=" + hotel_spa +
                ", hotel_roomservice=" + hotel_roomservice +
                '}';
    }
}
