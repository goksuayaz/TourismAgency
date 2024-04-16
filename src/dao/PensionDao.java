package dao;

import core.Database;
import entity.Hotel;
import entity.Pension;
import entity.Season;
import entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {

    private final Connection connection;

    public PensionDao(){
        this.connection = Database.getInstance();
    }

    public ArrayList<Pension> getPensionByHotelId(int id){
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.pension WHERE id = ?";

        try(PreparedStatement pr = connection.prepareStatement(query)){
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            while(rs.next()){
                Pension pension = match(rs);
                pensions.add(pension);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return pensions;

    }

    public Pension getByID(int id){
        Pension obj = null;
        String query ="SELECT * FROM public.pension WHERE id = ? ";

        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            if(rs.next()){
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public Pension match(ResultSet rs) throws SQLException{
        Pension obj = new Pension();
        obj.setId(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_type(rs.getString("pension_type"));

        return obj;
    }

    public boolean update(Pension pension){
        try{
            String query = "UPDATE public.pension SET " +
                    "hotel_id = ?," +
                    "pension_type = ?" +
                    "WHERE user_id = ?";

            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, pension.getHotel_id());
            pr.setString(2, pension.getPension_type());
            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Pension> findAll(){
        ArrayList<Pension> pensionList = new ArrayList<>();
        String sql = "SELECT * FROM public.pension";

        try{
            ResultSet rs = this.connection.createStatement().executeQuery(sql);

            while(rs.next()){
                pensionList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pensionList;
    }

    public boolean save(Pension pension){
        String query = "INSERT INTO public.pension"+
                "("+
                "hotel_id,"+
                "pension_type"+
                ")"+
                "VALUES (?,?)";

        try{
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,pension.getHotel_id());
            pr.setString(2, pension.getPension_type());
            return pr.executeUpdate() != -1;

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hotel_id){
        try{
            String query = "DELETE FROM public.pension WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }


}
