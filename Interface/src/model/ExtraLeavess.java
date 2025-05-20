package model;

public class ExtraLeavess {
    private int eldId;
    private double amount;
    private int year;
    private int month;
    private String fromDate;
    private String toDate;
    private int employeeId;

    public ExtraLeavess(double amount, int year, int month, String fromDate, String toDate, int employeeId) {
        this.amount = amount;
        this.year = year;
        this.month = month;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.employeeId = employeeId;
    }



    public int getEldId() {
        return eldId;
    }

    public void setEldId(int eldId) {
        this.eldId = eldId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
