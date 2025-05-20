import DAO.EmployeeDAO;
import TableModel.EmployTableModel;
import model.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class QuanLyNhanVien extends JFrame {
    private JTable employeeTable;
    private Employee _employee;
    public QuanLyNhanVien(){
//        setLayout(new BorderLayout());
        setTitle("EMPLOYEE AND SALARY CALCULATOR");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel label = new JLabel("EMPLOYEE MANAGEMENT", SwingConstants.CENTER);

        EmployTableModel EmployTableModel = new EmployTableModel(getEmployeeList());
        employeeTable = new JTable(EmployTableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);
        refreshEmployeeTable(EmployTableModel);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton backButton = new JButton("MENU");
        JButton addButton = new JButton("ADD EMPLOYEE");

        JButton deleteButton = new JButton("ADD ALLOWANCE");
        JButton dThemNgayNghiButton = new JButton("ADD LEAVES");



        // Thêm các button vào panel
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);

        buttonPanel.add(deleteButton);
        buttonPanel.add(dThemNgayNghiButton);

        // Đặt cả label và bảng vào panel chứa
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(label, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Đặt panel chứa button ở dưới cùng
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Thêm panel chứa tất cả vào frame
        add(mainPanel);

        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = employeeTable.getSelectedRow();
                if (row >= 0) {
                    int employeeID = (int) employeeTable.getValueAt(row, 0); // assuming EmployeeID is in the first column
                   EmployeeDAO dao = new EmployeeDAO();
                    try {
                       Employee employee =  dao.getEmployeeById(employeeID);
                        _employee = employee;

                        System.out.println(employee.toString());
//                        JFrame quanLyNhanVienForm = new EmployeeForm(employee);
//                        quanLyNhanVienForm.setVisible(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });





        // Add action listener for the button
        addButton.addActionListener(e -> {
            // Logic to add a new employee (not implemented here)
            // After adding the new employee, refresh the table
            JFrame quanLyNhanVienForm = new EmployeeForm(null);
            quanLyNhanVienForm.setVisible(true);
            setVisible(false);
            refreshEmployeeTable(EmployTableModel);
        });



         deleteButton.addActionListener(e -> {
            // Logic to add a new employee (not implemented here)
             if(_employee== null) return;
            // After adding the new employee, refresh the table
            JFrame quanLyNhanVienForm = new SalaryMaganerment(_employee);
            quanLyNhanVienForm.setVisible(true);
            setVisible(false);
        });
        backButton.addActionListener(e -> {
            // Logic to add a new employee (not implemented here)
            // After adding the new employee, refresh the table
            JFrame quanLyNhanVienForm = new MenuForm();
            quanLyNhanVienForm.setVisible(true);
            setVisible(false);
            refreshEmployeeTable(EmployTableModel);
        });

        dThemNgayNghiButton.addActionListener(e -> {
            // Logic to add a new employee (not implemented here)
            // After adding the new employee, refresh the table
            JFrame quanLyNhanVienForm = new ExtraLeaves(_employee);
            quanLyNhanVienForm.setVisible(true);
            setVisible(false);
            refreshEmployeeTable(EmployTableModel);
        });
    }


    private List<Employee> getEmployeeList() {
        EmployeeDAO dao = new EmployeeDAO();
        try {
            return dao.GetListEmployee();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void refreshEmployeeTable(EmployTableModel employeeTableModel) {
        SwingUtilities.invokeLater(() -> {
            try {
                EmployeeDAO dao = new EmployeeDAO();
                List<Employee> employees = dao.GetListEmployee();
                employeeTableModel.setEmployees(employees); // Cập nhật dữ liệu cho TableModel
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR WHILE TRYING TO UPLOAD EMPLOYEE LIST", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


}
