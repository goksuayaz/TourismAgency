package dao;

import core.Database;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {

    private final Connection connection;

    public ReservationDao(){
        this.connection = Database.getInstance();
    }

    public ArrayList<Reservation> getReservationByHotelId(int hotelId){
        ArrayList<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservation WHERE room_id = ?";

        try(PreparedStatement pr = connection.prepareStatement(query)){
            pr.setInt(1,hotelId);
            ResultSet rs = pr.executeQuery();

            while(rs.next()){
                Reservation reservation = match(rs);
                reservations.add(reservation);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reservations;
    }

    public Reservation getById(int id){
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE id = ?";

        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();

            if(rs.next()){
                obj = this.match(rs);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public Reservation match(ResultSet rs) throws SQLException{
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("id"));
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setCheck_in_date(LocalDate.parse(rs.getString("check_in_date")));
        obj.setTotal_price(rs.getDouble("total_price"));
        obj.setGuest_count(rs.getInt("guest_count"));
        obj.setGuest_name(rs.getString("guest_name"));
        obj.setGuest_citizen_id(rs.getString("guest_citizen_id"));
        obj.setGuest_mail(rs.getString("guest_mail"));
        obj.setGuest_phone(rs.getString("guest_phone"));
        obj.setCheck_out_date(LocalDate.parse(rs.getString("check_out_date")));
        return obj;
    }

    public ArrayList<Reservation> findAll(){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation";

        try{
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while(rs.next()){

                reservationList.add(this.match(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reservationList;

    }

    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation"+
                "("+
                "room_id,"+
                "check_in_date," +
                "total_price,"+
                "guest_count,"+
                "guest_name,"+
                "guest_citizen_id,"+
                "guest_mail,"+
                "guest_phone,"+
                "check_out_date"+
                ")"+
                "VALUES (?,?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement pr = connection.prepareStatement(query);

            pr.setInt(1,reservation.getRoom_id());
            pr.setDate(2, Date.valueOf(reservation.getCheck_in_date()));
            pr.setDouble(3,reservation.getTotal_price());
            pr.setInt(4,reservation.getGuest_count());
            pr.setString(5,reservation.getGuest_name());
            pr.setString(6,reservation.getGuest_citizen_id());
            pr.setString(7,reservation.getGuest_mail());
            pr.setString(8,reservation.getGuest_phone());
            pr.setDate(9,Date.valueOf(reservation.getCheck_out_date()));

            return  pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hotel_id){
        try{
            String query = "DELETE FROM public.reservation WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,hotel_id);
            return pr.executeUpdate() != -1;

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return true;

    }

    public ArrayList<Reservation> getByListReservationId(int id){
        return this.selectByQuery("SELECT * FROM public.reservation WHERE id="+id);
    }

    public ArrayList<Reservation> selectByQuery(String query){
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try{
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while(rs.next()){
                reservationList.add(this.match(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reservationList;
    }

    public boolean update(Reservation reservation){
        try{
            String query = "UPDATE public.reservation SET " +
                    "room_id = ?,"+
                    "check_in_date = ?," +
                    "total_price = ?,"+
                    "guest_count = ?,"+
                    "guest_name = ?,"+
                    "guest_citizen_id = ?,"+
                    "guest_mail = ?,"+
                    "guest_phone = ?,"+
                    "check_out_date = ?"+
                    "WHERE id = ?";

            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,reservation.getRoom_id());
            pr.setDate(2,Date.valueOf(reservation.getCheck_in_date()));
            pr.setDouble(3,reservation.getTotal_price());
            pr.setInt(4,reservation.getGuest_count());
            pr.setString(5,reservation.getGuest_name());
            pr.setString(6,reservation.getGuest_citizen_id());
            pr.setString(7,reservation.getGuest_mail());
            pr.setString(8,reservation.getGuest_phone());
            pr.setDate(9,Date.valueOf(reservation.getCheck_out_date()));
            pr.setInt(10,reservation.getId());

            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

}








