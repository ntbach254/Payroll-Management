import DAO.DepartmentDAO;
import TableModel.DepartmentTableModel;
import model.Department;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class DepartmentForm extends JFrame {
    private JTable departmentTable;
    private DefaultTableModel tableModel;
    private JTextField departmentNameField;


    private JButton backButton;

    public DepartmentForm() {
        setTitle("DEPARTMENT MANAGEMENT");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo bảng để hiển thị danh sách phòng ban
        DepartmentTableModel model = new DepartmentTableModel(getDepartmentList());
        departmentTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(departmentTable);

        // Tạo nút chỉnh sửa và nút thêm mới

        backButton = new JButton("MENU");


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action when the back button is clicked
                // For example, go back to the previous frame or form
                JFrame menuForm = new MenuForm(); // Assuming MenuForm is the previous form
                menuForm.setVisible(true);
                setVisible(false); // Hide the current frame
            }
        });

        // Sự kiện khi click vào nút thêm mới


        // Tạo các component để nhập thông tin phòng ban mới
        JLabel nameLabel = new JLabel("DEPARTMENT:");
        departmentNameField = new JTextField(20);
        JButton addNewButton = new JButton("ADD");

        // Sự kiện khi click vào nút thêm mới trong form nhập
        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentName = departmentNameField.getText();
                if (!departmentName.isEmpty()) {
                    // Thêm phòng ban mới vào cơ sở dữ liệu và cập nhật bảng
                    try {
                        addNewDepartment(departmentName);
                        departmentNameField.setText("");
                        refreshEmployeeTable(model);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(DepartmentForm.this, "PLEASE PROVIDE DEPARTMENT NAME", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Đặt các component vào layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(nameLabel);
        inputPanel.add(departmentNameField);
        inputPanel.add(addNewButton);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(backButton);
        // Đặt bảng và form nhập vào layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Đặt nút vào layout
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(buttonPanel);

        // Thêm mainPanel và controlPanel vào frame
        add(mainPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    // Hàm để mở form chỉnh sửa thông tin phòng ban
    private void openEditDepartmentForm(int departmentId, String departmentName) {
        // Tạo một instance mới của EditDepartmentForm để chỉnh sửa thông tin
        // và thiết lập thông tin của phòng ban cần chỉnh sửa
//        EditDepartmentForm editForm = new EditDepartmentForm(departmentId, departmentName);
//        editForm.setVisible(true);
    }

    // Hàm để mở form thêm mới phòng ban
    private void openAddDepartmentForm() {
        // Tạo một instance mới của AddDepartmentForm để thêm mới phòng ban
//        AddDepartmentForm addForm = new AddDepartmentForm();
//        addForm.setVisible(true);
    }

    // Hàm để cập nhật danh sách phòng ban trong bảng
    public void updateDepartmentTable(Object[][] data) {
        tableModel.setDataVector(data, new Object[]{"ID Phòng ban", "Tên Phòng ban"});
    }

    // Hàm để thêm phòng ban mới vào cơ sở dữ liệu
    private void addNewDepartment(String departmentName) throws SQLException {
        // Viết code để thêm phòng ban mới vào cơ sở dữ liệu ở đây
        // Sau khi thêm, cập nhật lại bảng hiển thị danh sách phòng ban
        DepartmentDAO dao = new DepartmentDAO();
        Department dp = new Department(1 ,departmentName);
        dao.AddnewDepartment(dp);
    }
    private void refreshEmployeeTable(DepartmentTableModel employeeTableModel) {
        SwingUtilities.invokeLater(() -> {
            try {
                DepartmentDAO dao = new DepartmentDAO();
                List<Department> employees = dao.GetListdepartment();
                employeeTableModel.setEmployees(employees); // Cập nhật dữ liệu cho TableModel
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private List<Department> getDepartmentList() {
        DepartmentDAO dao = new DepartmentDAO();
        try {
            return dao.GetListdepartment();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
