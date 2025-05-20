    import DAO.ThongKeDAO;
import TableModel.ThongkeTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongKeLuong extends JFrame {
    private JTable employeeTable;
    private ThongkeTableModel employTableModel;

    public ThongKeLuong() throws SQLException {
        setTitle("PAYROLL");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create label
        JLabel label = new JLabel("PAYROLL", SwingConstants.CENTER);

        // Create search panel with search box and button
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JTextField searchField = new JTextField(20);
        JButton searchEIDButton = new JButton("SEARCH EID");
        JButton searchMonthButton = new JButton("SEARCH MONTH");
        JButton searchYearButton = new JButton("SEARCH YEAR");

        searchPanel.add(searchField);
        searchPanel.add(searchEIDButton);
        searchPanel.add(searchMonthButton);
        searchPanel.add(searchYearButton);

        // Fetch data and create table model
        employTableModel = new ThongkeTableModel(GetThongKe());
        employeeTable = new JTable(employTableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton backButton = new JButton("MENU");

        buttonPanel.add(backButton);




        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame quanLyNhanVienForm = new MenuForm();
                quanLyNhanVienForm.setVisible(true);
                setVisible(false);
            }
        });

        searchEIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                try {
                    List<model.ThongKeLuong> searchResults = SearchThongKe(searchText);
                    System.out.println("count: "+ searchResults.size());
                    refreshEmployeeTable(employTableModel,  searchResults);
                    employTableModel.fireTableDataChanged();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        searchMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                try {
                    List<model.ThongKeLuong> searchResults = SearchByEffectiveMonth(searchText);
                    System.out.println("Month search count: " + searchResults.size());
                    refreshEmployeeTable(employTableModel, searchResults);
                    employTableModel.fireTableDataChanged();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        searchYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                try {
                    List<model.ThongKeLuong> searchResults = SearchByEffectiveYear(searchText);
                    System.out.println("Year search count: " + searchResults.size());
                    refreshEmployeeTable(employTableModel, searchResults);
                    employTableModel.fireTableDataChanged();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Combine all panels into the main panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(label, BorderLayout.NORTH);
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
    }

    public List<model.ThongKeLuong> GetThongKe() throws SQLException {
        ThongKeDAO dao = new ThongKeDAO();
        return dao.GetListThongKe();
    }
    public List<model.ThongKeLuong> SearchThongKe(String searchCriteria) throws SQLException {
        ThongKeDAO dao = new ThongKeDAO();
        List<model.ThongKeLuong> list = dao.GetListThongKe();

        // Initialize the result list
        List<model.ThongKeLuong> result = new ArrayList<>();

        // Convert searchCriteria to lower case for case-insensitive search
        String searchCriteriaLower = searchCriteria.toLowerCase();

        // Iterate through the list and search by employeeID
        for (model.ThongKeLuong item : list) {
            String employeeID = String.valueOf(item.getEmployeeID()).toLowerCase();

            if (employeeID.contains(searchCriteriaLower)) {
                result.add(item);
            }
        }
        return result;
    }

    public List<model.ThongKeLuong> SearchByEffectiveMonth(String searchCriteria) throws SQLException {
        ThongKeDAO dao = new ThongKeDAO();
        List<model.ThongKeLuong> list = dao.GetListThongKe();

        List<model.ThongKeLuong> result = new ArrayList<>();
        String searchCriteriaLower = searchCriteria.toLowerCase();

        for (model.ThongKeLuong item : list) {
            String effectiveMonth = String.valueOf(item.getEffectiveMonth()).toLowerCase();

            if (effectiveMonth.contains(searchCriteriaLower)) {
                result.add(item);
            }
        }
        return result;
    }

    public List<model.ThongKeLuong> SearchByEffectiveYear(String searchCriteria) throws SQLException {
        ThongKeDAO dao = new ThongKeDAO();
        List<model.ThongKeLuong> list = dao.GetListThongKe();

        List<model.ThongKeLuong> result = new ArrayList<>();
        String searchCriteriaLower = searchCriteria.toLowerCase();

        for (model.ThongKeLuong item : list) {
            String effectiveYear = String.valueOf(item.getEffectiveYear()).toLowerCase();

            if (effectiveYear.contains(searchCriteriaLower)) {
                result.add(item);
            }
        }
        return result;
    }
     private void refreshEmployeeTable(ThongkeTableModel employeeTableModel , List<model.ThongKeLuong> thognke) {
        SwingUtilities.invokeLater(() -> {
            try {
                ThongKeDAO dao = new ThongKeDAO();
                List<model.ThongKeLuong> employees = dao.GetListThongKe();
                employeeTableModel.setThongke(thognke); // Cập nhật dữ liệu cho TableModel
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
