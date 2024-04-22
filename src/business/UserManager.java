package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {

    private final UserDao userDao;

    public UserManager(){
        this.userDao = new UserDao();
    }

    public User findByLogin(String username, String password){
        return this.userDao.findByLogin(username,password);

    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> modelList) {
        ArrayList<Object[]> modelObjList = new ArrayList<>();
        for (User obj : modelList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();
            modelObjList.add(rowObject);
        }
        return modelObjList;

    }


    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }


    public boolean save(User user) {
        if (this.getById(user.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }


    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg(user.getId() + " ID kayıtlı model bulunamadı");
            return false;
        }
        return this.userDao.update(user);
    }


    public User getById(int id) {
        return this.userDao.getByID(id);
    }


    public boolean delete(int id) {  // DELETE İŞLEMİ
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı model bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }


    public ArrayList<User> searchForTable(String role) {
        String select = "SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();
        if (role != null) {
            whereList.add("user_role='" + role + "'");
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }
        System.out.println(whereList);
        return this.userDao.selectByQuery(query);
    }
}
