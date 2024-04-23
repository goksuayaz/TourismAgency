package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateRoomView extends Layout {
    private JLabel lbl_title_add;
    private JComboBox cmb_room_add_hotel;
    private JComboBox cmb_season_add;
    private JComboBox cmb_room_type_add;
    private JTextField tf_adult_price;
    private JRadioButton rbut_projection;
    private JButton btn_save_add_room_menu;
    private JPanel wrapper;
    private JComboBox cmb_pension_add;
    private JTextField tf_child_price;
    private JTextField tf_bed_capacity;
    private JTextField tf_square_meter;
    private JRadioButton rbut_television;
    private JRadioButton rbut_minibar;
    private JRadioButton rbut_game_console;
    private JRadioButton rbut_cashbox;
    private JTextField tf_stock;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private ComboItem comboItem;
    private EmployeeView employeeView = new EmployeeView();
    private Hotel hotel;
    private Room room;
    private Season season;
    private RoomManager roomManager;


    public UpdateRoomView() {
        this.add(wrapper);
        this.guiInitilaze(725, 425);
        this.comboItem = new ComboItem();
        this.hotel = new Hotel();
        this.room = new Room();
        this.season = new Season();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        for (Hotel hotel : hotelManager.findAll()) {
            this.cmb_room_add_hotel.addItem(hotel.getComboItem());
        }

        btn_save_add_room_menu.addActionListener(e -> {


            JTextField[] selectedRoomList = new JTextField[]{tf_adult_price, tf_child_price, tf_bed_capacity, tf_bed_capacity, tf_square_meter};

            if (Helper.isFieldListEmpty(selectedRoomList)) {
                Helper.showMsg("fill");
            } else {

            }
            boolean result;

            int roomId = room.getId();

            ComboItem selectedHotel = (ComboItem) cmb_room_add_hotel.getSelectedItem();
            ComboItem selectedPension = (ComboItem) cmb_pension_add.getSelectedItem();
            ComboItem selectedSeason = (ComboItem) cmb_season_add.getSelectedItem();
            this.room.setSeason_id(selectedSeason.getKey());
            this.room.setPension_id(selectedPension.getKey());
            this.room.setHotel_id(selectedHotel.getKey());
            this.room.setType((String) cmb_room_type_add.getSelectedItem());
            this.room.setStock(Integer.parseInt(tf_stock.getText()));
            this.room.setAdult_price(Double.parseDouble(tf_adult_price.getText()));
            this.room.setChild_price(Double.parseDouble(tf_child_price.getText()));
            this.room.setBed_capacity(Integer.parseInt(tf_bed_capacity.getText()));
            this.room.setSquare_meter(Integer.parseInt(tf_square_meter.getText()));
            this.room.setTelevision(rbut_television.isSelected());
            this.room.setMinibar(rbut_minibar.isSelected());
            this.room.setGame_console(rbut_game_console.isSelected());
            this.room.setCash_box(rbut_cashbox.isSelected());
            this.room.setProjection(rbut_projection.isSelected());

            if (room.getId() != 0) {
                result = roomManager.update(room);
            } else {
                result = roomManager.save(room);
            }
            if (result) {
                Helper.showMsg("done");
            } else {
                Helper.showMsg("error");
            }
        });

        cmb_room_add_hotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ComboItem selectedHotelItem = (ComboItem) cmb_room_add_hotel.getSelectedItem();
                int selectedHotelId = selectedHotelItem.getKey();
                ArrayList<Pension> pensions = pensionManager.getPensionByHotelId(((ComboItem) cmb_room_add_hotel.getSelectedItem()).getKey());
                System.out.println("Pension: " + pensions);
                cmb_pension_add.removeAllItems();

                for (Pension pension : pensions) {
                    cmb_pension_add.addItem(pension.getComboItem());
                }

                ArrayList<Season> seasons = seasonManager.getSeasonByHotelId(((ComboItem) cmb_room_add_hotel.getSelectedItem()).getKey());
                System.out.println("Seasons: " + seasons);
                cmb_season_add.removeAllItems();
                for (Season season : seasons) {
                    cmb_season_add.addItem(season.getComboItem());

                }

            }
        });



    }

}
