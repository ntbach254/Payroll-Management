package DAO;

import model.ExtraLeavess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExtraLeavesDAO {

    public void addExtraLeaves(ExtraLeavess extraLeaves) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnections.GetConnection();
            String query = "INSERT INTO ExtraLeaves (EL_amount, EffectiveYear, EffectiveMonth, FromDate, ToDate, EmployeeID) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate;
            java.util.Date utilDate2;
            Date sqlDatefrom = null;
            Date sqlDateto = null;
            try {
                utilDate = sdf.parse(extraLeaves.getFromDate()); // Phân tích chuỗi thành đối tượng java.util.Date
                utilDate2 = sdf.parse(extraLeaves.getToDate()); // Phân tích chuỗi thành đối tượng java.util.Date
                long timeInMillis = utilDate.getTime(); // Lấy thời gian trong millisecond
                long timeto = utilDate2.getTime();
                sqlDatefrom = new Date(timeInMillis);
                sqlDateto = new Date(timeto); // Tạo đối tượng java.sql.Date từ millisecond
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, extraLeaves.getAmount());
            pstmt.setInt(2, extraLeaves.getYear());
            pstmt.setInt(3, extraLeaves.getMonth());
            pstmt.setDate(4, sqlDatefrom);
            pstmt.setDate(5, sqlDateto);
            pstmt.setInt(6, extraLeaves.getEmployeeId());
            pstmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
