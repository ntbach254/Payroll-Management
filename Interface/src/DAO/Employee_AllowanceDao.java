package DAO;

import model.Employee_Allowance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee_AllowanceDao {
     public boolean addEmployeeAllowance(Employee_Allowance allowance) throws SQLException {
        String query = "INSERT INTO Employee_Allowance (HRA, TA, MA, DA, EffectiveYear, EffectiveMonth, EmployeeID) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnections.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, allowance.getHra());
            pstmt.setDouble(2, allowance.getTa());
            pstmt.setDouble(3, allowance.getMa());
            pstmt.setDouble(4, allowance.getDa());
            pstmt.setInt(5, allowance.getEffectiveYear());
            pstmt.setInt(6, allowance.getEffectiveMonth());
            pstmt.setInt(7, allowance.getEmployeeID());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}
