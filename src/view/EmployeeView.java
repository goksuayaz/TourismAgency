package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_exit;
    private JScrollPane w_bot;
    private JPanel w_top;
    private JTabbedPane tab_rezervation;
    private JPanel tab_Hotel;
    private JPanel tab_room;
    private JPanel tab_reservation;
    private JTable tbl_hotel;
    private JTable tbl_room;

    public JTable getTbl_room() {
        return tbl_room;
    }

    public void setTbl_room(JTable tbl_room) {
        this.tbl_room = tbl_room;
    }

    private JTable tbl_reservation;
    private JScrollPane w_left;
    private JScrollPane w_right;
    private JButton btn_hotel_add;
    private JPanel pnl_room;
    private JLabel lbl_room_name;
    private JButton btn_search_room;
    private JButton btn_room_reset;
    private JButton btn_room_add;
    private JLabel lbl_city;
    private JLabel lbl_date_entry;
    private JLabel lbl_date_release;
    private JLabel lbl_number_adult;
    private JLabel lbl_number_child;
    private JTextField tf_name_room;
    private JTextField tf_city_room;
    private JTextField tf_numb_adult_room;
    private JTextField tf_numb_children_room;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JScrollPane scrl_room;
    private JFormattedTextField tf_date_entry_room;
    private JFormattedTextField tf_date_release_room;
    private JButton btn_pension_add;
    private Hotel hotel;
    private JPopupMenu hotel_menu;
    private HotelManager hotelManager = new HotelManager();
    private Pension pension = new Pension();
    private PensionManager pensionManager = new PensionManager();
    private SeasonManager seasonManager = new SeasonManager();
    private RoomManager roomManager = new RoomManager();
    private UserManager userManager = new UserManager();
    private JPopupMenu pension_menu;
    private JPopupMenu season_menu;
    private JPopupMenu room_menu;
    private JPopupMenu reservation_menu;
    private ReservationManager reservationManager;
    private Room room;

    private Object[] col_room;


    DefaultTableModel tmdl_hotel = new DefaultTableModel();
    DefaultTableModel tmdl_pension = new DefaultTableModel();
    DefaultTableModel tmdl_season = new DefaultTableModel();
    DefaultTableModel tmdl_room = new DefaultTableModel();
    DefaultTableModel tmdl_res = new DefaultTableModel();

    public EmployeeView() {

    }


    public EmployeeView(User user) {
        this.room = new Room();
        this.col_room = col_room;
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.add(wrapper);
        this.guiInitilaze(1400, 850);
        this.pension_menu = new JPopupMenu();
        this.season_menu = new JPopupMenu();
        this.room_menu = new JPopupMenu();
        this.reservation_menu = new JPopupMenu();
        this.lbl_welcome.setText("Welcome : " + user.getUsername());
        this.reservationManager = new ReservationManager();

        loadHotelTable();
        loadPensionTable();
        loadRoomTable(null);
        loadSeasonTable();
        LoadRoomTableComponent();
        loadReservationTable(null);
        LoadReservationTableComponent();

        tableRowSelect(tbl_hotel);
        tableRowSelect(tbl_pension);
        tableRowSelect(tbl_room);
        loadHotelTableComponent();
        LoadPensionTableComponent();
        loadSeasonTableComponent();


        btn_room_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRoomTable(null);
            }
        });


        btn_search_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] roomJTextField = new JTextField[]{tf_numb_adult_room, tf_numb_children_room};
                if (Helper.isFieldListEmpty(roomJTextField)) {
                    Helper.showMsg("Please enter the number of children and adults");
                }else {
                    int selectedAdult = Integer.parseInt(tf_numb_adult_room.getText());
                    int selectedChild = Integer.parseInt(tf_numb_children_room.getText());

                    if (selectedAdult < 0 || selectedChild < 0) {
                        Helper.showMsg("Please enter the number of children and adults greater than 0");
                    }
                    ArrayList<Room> roomList = roomManager.searchForTable(
                            tf_name_room.getText(),
                            tf_city_room.getText(),
                            tf_date_entry_room.getText(),
                            tf_date_release_room.getText(),
                            tf_numb_adult_room.getText(),
                            tf_numb_children_room.getText()
                    );

                    ArrayList<Object[]> searchResult = roomManager.getForTable(col_room.length, roomList);
                    loadRoomTable(searchResult);

                }
            }

        });

        btn_room_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRoomView addRoomView = new AddRoomView();
                addRoomView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }
                });
            }
        });

        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView loginView = new LoginView();
                loadHotelTable();
                dispose();
            }
        });

        btn_hotel_add.addActionListener(e -> {
            HotelAddView hotelAddGUI = new HotelAddView();
            hotelAddGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });

            tableRowSelect(tbl_hotel);
        });
    }

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Name", "Adress", "Mail", "Telefon", "Star", "Car Park", "Wifi", "Pool", "Fitness", "Concierge", "Spa", "Room Services"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        createTable(this.tmdl_hotel, tbl_hotel, col_hotel, hotelList);
    }

    public void loadReservationTable(Reservation reservation) {
        Object[] col_res = {"ID", "Room ID", "Entry Date", "Finish Date","Total Amount", "Guest Number", "Guest Name ", "Guest ID Number", "Mail", "Phone"};
        ArrayList<Object[]> resList = this.reservationManager.getForTable(col_res.length, this.reservationManager.findAll());
        createTable(this.tmdl_res, tbl_reservation, col_res, resList);
    }


    public void loadPensionTable() {
        Object[] col_pension = {"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        createTable(this.tmdl_pension, tbl_pension, col_pension, pensionList);
    }

    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Hotel ID", "Start Date", "Finish Date"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        createTable(this.tmdl_season, tbl_season, col_season, seasonList);

    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room = new Object[]{"ID", "Hotel ID", "Pension ID", "Season ID", "Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "Television", "Minibar", "Game Console", "Cash BOX", "Projection"};
        if (roomList == null) {
            roomList = roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, tbl_room, col_room, roomList);
    }

    @Override
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseReleased(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }

    public void LoadRoomTableComponent() {

        tableRowSelect(tbl_room);

        room_menu.add("ADD Reservation").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(tbl_room, 0);
            JTextField[] roomJTextField = new JTextField[]{tf_date_entry_room, tf_date_release_room, tf_numb_adult_room, tf_numb_children_room};
            if (Helper.isFieldListEmpty(roomJTextField)) {
                Helper.showMsg("fill");
            } else {
                int adult_numb = Integer.parseInt(this.tf_numb_adult_room.getText());
                int child_numb = Integer.parseInt(this.tf_numb_children_room.getText());
                AddReservationView reservationView = new AddReservationView(this.roomManager.getById(selectId), this.tf_date_entry_room.getText(), this.tf_date_release_room.getText(), adult_numb, child_numb, null);
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                        loadReservationTable(null);
                    }
                });
            }

        });

        room_menu.add("Update").addActionListener(e -> {
            UpdateRoomView updateRoomView = new UpdateRoomView();
            updateRoomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });

        room_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_room, 0);
                if (this.roomManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadRoomTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_room.setComponentPopupMenu(room_menu);
    }

    public void LoadReservationTableComponent() {

        tableRowSelect(tbl_reservation);
        this.reservation_menu = new JPopupMenu();

        reservation_menu.add("UPDATE Reservation").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(tbl_reservation, 0);
            Reservation selectReservation = this.reservationManager.getById(selectId);
            int selectRoomId = selectReservation.getRoom_id();
            Room selectRoom = this.roomManager.getById(selectRoomId);
            UpdateReservationView reservationView = new UpdateReservationView(selectRoom,selectReservation.check_in_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),selectReservation.check_out_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),selectReservation.getAdult_count(), selectReservation.getChild_count(), selectReservation);
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);

                }
            });
        });

        reservation_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectResId = this.getTableSelectedRow(tbl_reservation, 0);
                int selectRoomId = this.reservationManager.getById(selectResId).getRoom_id();
                Room selectedRoom = this.roomManager.getById(selectRoomId);
                selectedRoom.setStock(selectedRoom.getStock()+1);
                this.roomManager.updateStock(selectedRoom);
                if (this.reservationManager.delete(selectResId)) {
                    Helper.showMsg("done");
                    loadRoomTable(null);
                    loadReservationTable(null);


                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_reservation.setComponentPopupMenu(reservation_menu);
    }

    public void LoadPensionTableComponent() {


        pension_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_pension, 0);
                if (this.pensionManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadPensionTable();


                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_pension.setComponentPopupMenu(pension_menu);
    }

    public void loadSeasonTableComponent() {


        tableRowSelect(tbl_season);

        season_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_season, 0);
                if (this.seasonManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadSeasonTable();

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_season.setComponentPopupMenu(season_menu);
    }


    public void loadHotelTableComponent() {
        tableRowSelect(tbl_hotel);
        tableRowSelect(tbl_pension);
        tableRowSelect(tbl_room);
        tableRowSelect(tbl_season);


        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Hotel ADD").addActionListener(e -> {
            HotelAddView hotelAddView = new HotelAddView();
            hotelAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });

        this.hotel_menu.add("Season ADD").addActionListener(e -> {
            SeasonView seasonView = new SeasonView();
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadSeasonTable();
                }
            });
        });

        this.hotel_menu.add("Pension Add").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(tbl_hotel, 0);
            PensionView pensionView = new PensionView(selectId);
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadPensionTable();
                }
            });
        });


        this.hotel_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadHotelTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_menu);
    }

    private void createUIComponents() throws ParseException {
        this.tf_date_entry_room = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.tf_date_entry_room.setText("01/01/2024");
        this.tf_date_release_room = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.tf_date_release_room.setText("01/05/2024");
    }

}