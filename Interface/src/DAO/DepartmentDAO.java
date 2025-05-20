package DAO;

import model.Department;
import model.Employee;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    public List<Department> GetListdepartment() throws SQLException {
        List<Department> employees = new ArrayList<>();
        String sql = "select * from Department";

        try (Connection conn = DatabaseConnections.GetConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("DepartmentID");
                String departmentname = rs.getString("DepartmentName");
                employees.add(new Department(id, departmentname));
            }
        }
        return employees;
    }

    public boolean AddnewDepartment(Department employee) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        System.out.println(employee.toString());
        try {
            conn = DatabaseConnections.GetConnection();
            String query = "INSERT INTO Department ( DepartmentName) VALUES (?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, employee.getDepartmentname());
            pstmt.executeUpdate();
            return  true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
