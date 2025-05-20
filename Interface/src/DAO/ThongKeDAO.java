package DAO;

import model.ThongKeLuong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.

public class ThongKeDAO {


    public List<ThongKeLuong> GetListThongKe() throws SQLException {
        List<ThongKeLuong> employees = new ArrayList<>();
                    String sql ="SELECT e.EmployeeID, e.FName, e.LName, (e.BaseSalary + COALESCE(AVG(a.HRA + a.TA + a.MA + a.DA), 0) - COALESCE(SUM(l.EL_amount), 0)) AS NetSalary, a.EffectiveYear, a.EffectiveMonth FROM Employee e LEFT JOIN Employee_Allowance a ON e.EmployeeID = a.EmployeeID LEFT JOIN ExtraLeaves l ON e.EmployeeID = l.EmployeeID AND a.EffectiveYear = l.EffectiveYear AND a.EffectiveMonth = l.EffectiveMonth GROUP BY e.EmployeeID, e.FName, e.LName, e.BaseSalary, a.EffectiveYear, a.EffectiveMonth ORDER BY e.EmployeeID, a.EffectiveYear, a.EffectiveMonth;";

        try (Connection conn = DatabaseConnections.GetConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("EmployeeID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                double NetSalary = rs.getDouble("NetSalary");
                int EffectiveYear = rs.getInt("EffectiveYear");
                int EffectiveMonth = rs.getInt("EffectiveMonth");
                employees.add(new ThongKeLuong(id, LName , FName , NetSalary , EffectiveMonth , EffectiveYear));
            }
        }
        System.out.println("size: "+ employees.size());
        return employees;
    }
}
