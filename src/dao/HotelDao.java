package dao;

import core.Database;
import entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {

    private final Connection connection;

    public HotelDao(){
        this.connection = Database.getInstance();
    }


    //Method that returns the hotel with a specific ID
    public Hotel getById(int id){
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ? ";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        }catch(SQLException e){
            e.printStackTrace();

        }
        return obj;
    }

    // Method that maps ResultSet to Hotel object
    public Hotel match(ResultSet rs) throws SQLException{
        Hotel obj = new Hotel();
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setHotel_name(rs.getString("hotel_name"));
        obj.setHotel_adress(rs.getString("hotel_address"));
        obj.setHotel_mail(rs.getString("hotel_mail"));
        obj.setHotel_phone(rs.getString("hotel_phone"));
        obj.setHotel_star(rs.getString("hotel_star"));
        obj.setHotel_carkpark(rs.getBoolean("hotel_carpark"));
        obj.setHotel_wifi(rs.getBoolean("hotel_wifi"));
        obj.setHotel_pool(rs.getBoolean("hotel_pool"));
        obj.setHotel_fitness(rs.getBoolean("hotel_fitness"));
        obj.setHotel_concierge(rs.getBoolean("hotel_concierge"));
        obj.setHotel_spa(rs.getBoolean("hotel_spa"));
        obj.setHotel_roomservice(rs.getBoolean("hotel_roomservice"));

        return obj;
    }

    //Method to get all hotels
    public ArrayList<Hotel> findAll(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel";

        try{
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while(rs.next()){

                hotelList.add(this.match(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return hotelList;
    }

    //Method that adds hotel
    public boolean save(Hotel hotel){
        String query = "INSERT INTO public.hotel " +
                "(" +
                "hotel_name," +
                "hotel_address," +
                "hotel_mail," +
                "hotel_phone," +
                "hotel_star,"+
                "hotel_carpark," +
                "hotel_wifi," +
                "hotel_pool," +
                "hotel_fitness," +
                "hotel_concierge," +
                "hotel_spa," +
                "hotel_roomservice " +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_mail());
            pr.setString(3, hotel.getHotel_phone());
            pr.setString(4, hotel.getHotel_adress());
            pr.setString(5, hotel.getHotel_star());
            pr.setBoolean(6, hotel.isHotel_carkpark());
            pr.setBoolean(7, hotel.isHotel_wifi());
            pr.setBoolean(8, hotel.isHotel_pool());
            pr.setBoolean(9, hotel.isHotel_fitness());
            pr.setBoolean(10, hotel.isHotel_concierge());
            pr.setBoolean(11, hotel.isHotel_spa());
            pr.setBoolean(12, hotel.isHotel_roomservice());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    // Method to delete hotel
    public boolean delete(int model_id){
        try{
            String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, model_id);
            return pr.executeUpdate() != -1;
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }


    //Method to update the hotel
    public boolean update(Hotel hotel){
        try {
            String query = "UPDATE public.hotel SET " +
                    "hotel_name," +
                    "hotel_mail," +
                    "hotel_phone," +
                    "hotel_address," +
                    "hotel_star,"+
                    "hotel_carpark," +
                    "hotel_wifi," +
                    "hotel_pool," +
                    "hotel_fitness," +
                    "hotel_concierge," +
                    "hotel_spa," +
                    "hotel_roomservice " +
                    "WHERE hotel = ?";

            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_mail());
            pr.setString(3, hotel.getHotel_phone());
            pr.setString(4, hotel.getHotel_adress());
            pr.setString(5, hotel.getHotel_star());
            pr.setBoolean(6, hotel.isHotel_carkpark());
            pr.setBoolean(7, hotel.isHotel_wifi());
            pr.setBoolean(8, hotel.isHotel_pool());
            pr.setBoolean(9, hotel.isHotel_fitness());
            pr.setBoolean(10, hotel.isHotel_concierge());
            pr.setBoolean(11, hotel.isHotel_spa());
            pr.setBoolean(12, hotel.isHotel_roomservice());

            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }
}
