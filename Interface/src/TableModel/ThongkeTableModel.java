package TableModel;

import model.ThongKeLuong;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ThongkeTableModel extends AbstractTableModel {


    private final String[] columnNames = {"EID", "FIRSTNAME", "LASTNAME", "NET PAY", "MONTH" , "YEAR"};
    private List<ThongKeLuong> _employees;
    public ThongkeTableModel(List<ThongKeLuong> employees){
        _employees = employees;
    }
    @Override
    public int getRowCount() {
        return _employees.size();
    }

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        ThongKeLuong employee = _employees.get(rowIndex);
        switch (columnIndex){
            case 0:
                return employee.getEmployeeID();
            case 1:
                return employee.getFName();
            case 2:
                return employee.getLName();
            case 3:
                return employee.getTotalMonney();
            case 4:
                return employee.getEffectiveMonth();
            case 5:
                return employee.getEffectiveYear();
            default: break;
        }
        return null;
    }

    public void setThongke(List<ThongKeLuong> employees) {
        this._employees = employees;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
