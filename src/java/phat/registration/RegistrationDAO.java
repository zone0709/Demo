/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phat.utils.DBUtils;

/**
 *
 * @author zone
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // Viết câu lệnh truy cập sql
                String sql = "Select * From tbl_User Where userId = ? And password = ?";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1, username);
                stm.setString(2, password);
                // thực thi câu lệnh
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            } // end of con
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public String CheckRole(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String role = "1";
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // Viết câu lệnh truy cập sql
                String sql = "Select role From tbl_User Where userId = ? And password = ?";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1, username);
                stm.setString(2, password);
                // thực thi câu lệnh
                rs = stm.executeQuery();

                if (rs.next()) {
                    role = rs.getString("role");
                    return role;
                }
            } // end of con
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return role;
    }
    private List<RegistrationDTO> listResult;

    public List<RegistrationDTO> getListResult() {
        return listResult;
    }

    public void searchLastname(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // Viết câu lệnh truy cập sql
                String sql = "Select * From tbl_Mobile Where mobileId Like ? or mobileName Like ? ";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, "%" + searchValue + "%");

                // thực thi câu lệnh
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    String price = rs.getString("price");
                    String mobileName = rs.getString("mobileName");
                    String yearOfProduction = rs.getString("yearOfProduction");
                    String quantity = rs.getString("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    RegistrationDTO dto = new RegistrationDTO(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);
                    if (this.listResult == null) {
                        listResult = new ArrayList<RegistrationDTO>();
                    }
                    listResult.add(dto);
                }
            } // end of con
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchPrice(String Min, String Max)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // Viết câu lệnh truy cập sql
                String sql = "Select * From tbl_Mobile Where price >= ? AND price <= ? ";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1, Min);
                stm.setString(2, Max);

                // thực thi câu lệnh
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    String price = rs.getString("price");
                    String mobileName = rs.getString("mobileName");
                    String yearOfProduction = rs.getString("yearOfProduction");
                    String quantity = rs.getString("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    RegistrationDTO dto = new RegistrationDTO(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);
                    if (this.listResult == null) {
                        listResult = new ArrayList<RegistrationDTO>();
                    }
                    listResult.add(dto);
                }
            } // end of con
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deletePK(String mobileId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // Viết câu lệnh truy cập sql
                String sql = "Delete From tbl_Mobile Where mobileId = ? ";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1, mobileId);

                // thực thi câu lệnh
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } // end of con
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updatePassRole(String mobileId, String price, String description, String quantity, boolean role)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // Viết câu lệnh truy cập sql
                String sql = "Update  tbl_Mobile Set price=? ,description=? ,quantity=?,notSale=? Where mobileId = ? ";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1, price);
                stm.setString(2, description);
                stm.setString(3, quantity);
                stm.setBoolean(4, role);
                stm.setString(5, mobileId);
                // thực thi câu lệnh
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } // end of con
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean InsertMobie(String MobileId, String Description, String Price, String MobileName,
            String yearOfProduction, String quantity, String notSale) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                // Viết câu lệnh truy cập sql
                String sql = "Insert Into tbl_Mobile(mobileId,description,price,mobileName,yearOfProduction,quantity,notSale) "
                        + " Values(?,?,?,?,?,?,?)";
                // Tao statement
                stm = con.prepareStatement(sql);
                // truyền tham số vào câu statement
                stm.setString(1, MobileId);
                stm.setString(2, Description);
                stm.setString(3, Price);
                stm.setString(4, MobileName);
                stm.setString(5, yearOfProduction);
                stm.setString(6, quantity);
                stm.setString(7, notSale);
                // thực thi câu lệnh
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } // end of con
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
