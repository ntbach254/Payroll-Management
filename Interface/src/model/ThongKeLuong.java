package model;

public class ThongKeLuong {

    private int EmployeeID;
    private String FName;
    private String  LName;
    private double TotalMonney;
    private int EffectiveMonth;
    private  int EffectiveYear;


    public ThongKeLuong(int employeeID, String FName, String LName, double totalMonney, int effectiveMonth, int effectiveYear) {
        EmployeeID = employeeID;
        this.FName = FName;
        this.LName = LName;
        TotalMonney = totalMonney;
        EffectiveMonth = effectiveMonth;
        EffectiveYear = effectiveYear;
    }

    @Override
    public String toString() {
        return "ThongKeLuong{" +
                "EmployeeID=" + EmployeeID +
                ", FName='" + FName + '\'' +
                ", LName='" + LName + '\'' +
                ", TotalMonney=" + TotalMonney +
                ", EffectiveMonth=" + EffectiveMonth +
                ", EffectiveYear=" + EffectiveYear +
                '}';
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public double getTotalMonney() {
        return TotalMonney;
    }

    public void setTotalMonney(double totalMonney) {
        TotalMonney = totalMonney;
    }

    public int getEffectiveMonth() {
        return EffectiveMonth;
    }

    public void setEffectiveMonth(int effectiveMonth) {
        EffectiveMonth = effectiveMonth;
    }

    public int getEffectiveYear() {
        return EffectiveYear;
    }

    public void setEffectiveYear(int effectiveYear) {
        EffectiveYear = effectiveYear;
    }
}
