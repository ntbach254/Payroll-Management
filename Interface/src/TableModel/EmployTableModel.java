package TableModel;

import model.Employee;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class EmployTableModel extends AbstractTableModel {


    private final String[] columnNames = {"ID","FIRST NAME", "LAST NAME","MIDDLE NAME", "BASE SALARY", "JOB", "SERVICE" , "DEPARTMENT ID"};
    private List<Employee> _employees;
    public EmployTableModel(List<Employee> employees){
        _employees = employees;
    }
    @Override
    public int getRowCount() {
        return _employees.size();
//        return 0;
    }

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = _employees.get(rowIndex);
        switch (columnIndex){
            case 0:
                return employee.getEmployeeID();
            case 1:
                return employee.getFName();
            case 2:
                return employee.getLName();
            case 3:
                return employee.getMName();
            case 4:
                return employee.getBaseSalary();
            case 5:
                return employee.getWork();
            case 6:
                return employee.getDepartmentID();
            case 7:
                return employee.getDepartmentID();
            case 8:
               JButton editButton = new JButton("thêm TC");
                editButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });
                return editButton;
            default: break;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    public void setEmployees(List<Employee> employees) {
        this._employees = employees;
        fireTableDataChanged();
    }
}
