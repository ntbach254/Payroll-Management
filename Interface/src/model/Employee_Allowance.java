package model;

public class Employee_Allowance {

    private int eadId;
    private double hra;
    private double ta;
    private double ma;
    private double da;
    private int effectiveYear;
    private int effectiveMonth;
    private int employeeID;

    public Employee_Allowance(int eadId, double hra, double ta, double ma, double da, int effectiveYear, int effectiveMonth, int employeeID) {
        this.eadId = eadId;
        this.hra = hra;
        this.ta = ta;
        this.ma = ma;
        this.da = da;
        this.effectiveYear = effectiveYear;
        this.effectiveMonth = effectiveMonth;
        this.employeeID = employeeID;
    }

    public int getEadId() {
        return eadId;
    }

    public void setEadId(int eadId) {
        this.eadId = eadId;
    }

    public double getHra() {
        return hra;
    }

    public void setHra(double hra) {
        this.hra = hra;
    }

    public double getTa() {
        return ta;
    }

    public void setTa(double ta) {
        this.ta = ta;
    }

    public double getMa() {
        return ma;
    }

    public void setMa(double ma) {
        this.ma = ma;
    }

    public double getDa() {
        return da;
    }

    public void setDa(double da) {
        this.da = da;
    }

    public int getEffectiveYear() {
        return effectiveYear;
    }

    public void setEffectiveYear(int effectiveYear) {
        this.effectiveYear = effectiveYear;
    }

    public int getEffectiveMonth() {
        return effectiveMonth;
    }

    public void setEffectiveMonth(int effectiveMonth) {
        this.effectiveMonth = effectiveMonth;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
