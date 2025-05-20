package DAO;

import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDAO {

    public List<Employee> GetListEmployee() throws SQLException{
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from Employee";

        try (Connection conn = DatabaseConnections.GetConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("EmployeeID");
                String LName = rs.getString("LName");
                String FName = rs.getString("FName");
                String MName = rs.getString("MName");
                int Service = rs.getInt("Service");
                double BaseSalary = rs.getDouble("BaseSalary");
                String Work = rs.getString("Work");
                int DepartmentID = rs.getInt("DepartmentID");
                employees.add(new Employee(id, LName , FName , MName , Service , BaseSalary , Work , DepartmentID));
            }
        }
        System.out.println("size: "+ employees.size());
        return employees;
    }
    public boolean AddnewEmployee(Employee employee) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        System.out.println(employee.toString());
        try {
            conn = DatabaseConnections.GetConnection();
            String query = "INSERT INTO Employee(LName, FName, MName, Service, BaseSalary, Work, DepartmentID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, employee.getLName());
            pstmt.setString(2, employee.getFName());
            pstmt.setString(3, employee.getMName());
            pstmt.setInt(4, employee.getService());
            pstmt.setDouble(5, employee.getBaseSalary());
            pstmt.setString(6, employee.getWork());
            pstmt.setInt(7, employee.getDepartmentID());
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

public boolean updateEmployee(Employee employee) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnections.GetConnection();
            String query = "UPDATE Employee SET LName = ?, FName = ?, MName = ?, Service = ?, BaseSalary = ?, Work = ?, DepartmentID = ? WHERE EmployeeID = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, employee.getLName());
            pstmt.setString(2, employee.getFName());
            pstmt.setString(3, employee.getMName());
            pstmt.setInt(4, employee.getService());
            pstmt.setDouble(5, employee.getBaseSalary());
            pstmt.setString(6, employee.getWork());
            pstmt.setInt(7, employee.getDepartmentID());
            pstmt.setInt(8, employee.getEmployeeID());
            return pstmt.executeUpdate() > 0;
        }catch (Exception exception){
            exception.printStackTrace();
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

    public Employee getEmployeeById(int employeeID) throws SQLException {
        String query = "SELECT * FROM Employee WHERE EmployeeID = ?";
        try (Connection conn = DatabaseConnections.GetConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(rs.getInt("EmployeeID"));
                    employee.setLName(rs.getString("LName"));
                    employee.setFName(rs.getString("FName"));
                    employee.setMName(rs.getString("MName"));
                    employee.setService(rs.getInt("Service"));
                    employee.setBaseSalary(rs.getDouble("BaseSalary"));
                    employee.setWork(rs.getString("Work"));
                    employee.setDepartmentID(rs.getInt("DepartmentID"));
                    return employee;
                } else {
                    return null; // Nếu không tìm thấy nhân viên
                }
            }
        }
    }

}
