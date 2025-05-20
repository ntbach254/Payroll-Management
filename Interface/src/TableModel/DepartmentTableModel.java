package TableModel;

import model.Department;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DepartmentTableModel extends AbstractTableModel {

 private final String[] columnNames = {"ID", "DEPARTMENT NAME"};
    private List<Department> _employees;
    public DepartmentTableModel(List<Department> employees){
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
        Department employee = _employees.get(rowIndex);
        switch (columnIndex){
            case 0:
                return employee.getDepartmentid();
            case 1:
                return employee.getDepartmentname();
            default: break;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    public void setEmployees(List<Department> employees) {
        this._employees = employees;
        fireTableDataChanged();
    }
}
