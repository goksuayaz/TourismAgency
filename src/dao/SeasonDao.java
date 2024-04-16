package dao;

import core.Database;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {

    private final Connection connection;


    public SeasonDao(){
        this.connection = Database.getInstance();
    }

    public ArrayList<Season> getSeasonByHotelId(int hotelId){
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.season WHERE hotel_id = ?";

        try(PreparedStatement pr = connection.prepareStatement(query)){
            pr.setInt(1,hotelId);
            ResultSet rs = pr.executeQuery();

            while(rs.next()){
                Season season = match(rs);
                seasons.add(season);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return seasons;
    }


    public Season getByID(int id){
        Season obj = null;

        String query = "SELECT * FROM public.season WHERE id = ?";

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


    public Season match(ResultSet rs) throws SQLException{
        Season obj = new Season();
        obj.setId(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setStart_date(LocalDate.parse(rs.getString("start_date")));
        obj.setFinish_date(LocalDate.parse(rs.getString("finish_date")));

        return obj;
    }

    public ArrayList<Season> findAll(){
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.season";

        try{

            ResultSet rs = this.connection.createStatement().executeQuery(sql);

            while(rs.next()){
                seasonList.add(this.match(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return seasonList;
    }

    public boolean save(Season season){
        String query = "INSERT INTO public.season"+
                "("+
                "hotel_id,"+
                "start_date," +
                "finish_date"+
                ")"+
                "VALUES (?,?,?)";

        try{
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getFinish_date()));

            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hotel_id){
        try{
            String query = "SELECT FROM public.season WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, hotel_id);

            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }
}
