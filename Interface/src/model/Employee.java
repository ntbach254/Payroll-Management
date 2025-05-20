package model;

public class Employee {
    private int EmployeeID;
    private String LName;
    private String FName;
    private String MName;

    private int Service;
    private double BaseSalary;
    private String Work;

    private int DepartmentID;


    public Employee() {
    }

    public Employee(int employeeID, String LName, String FName, String MName, int service, double baseSalary, String work, int departmentID) {
        EmployeeID = employeeID;
        this.LName = LName;
        this.FName = FName;
        this.MName = MName;
        Service = service;
        BaseSalary = baseSalary;
        Work = work;
        DepartmentID = departmentID;
    }

    public Employee(String LName, String FName, String MName, int service, double baseSalary, String work, int departmentID) {
        this.LName = LName;
        this.FName = FName;
        this.MName = MName;
        Service = service;
        BaseSalary = baseSalary;
        Work = work;
        DepartmentID = departmentID;
    }


    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getMName() {
        return MName;
    }

    public void setMName(String MName) {
        this.MName = MName;
    }

    public int getService() {
        return Service;
    }

    public void setService(int service) {
        Service = service;
    }

    public double getBaseSalary() {
        return BaseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        BaseSalary = baseSalary;
    }

    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        Work = work;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int departmentID) {
        DepartmentID = departmentID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeID=" + EmployeeID +
                ", LName='" + LName + '\'' +
                ", FName='" + FName + '\'' +
                ", MName='" + MName + '\'' +
                ", Service=" + Service +
                ", BaseSalary=" + BaseSalary +
                ", Work='" + Work + '\'' +
                ", DepartmentID=" + DepartmentID +
                '}';
    }
}
