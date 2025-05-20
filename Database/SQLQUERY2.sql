USE PAYROLL_MANAGEMENT;

CREATE TABLE Department (
    DepartmentID INT PRIMARY KEY identity(1,1),
    DepartmentName NVARCHAR(50) NOT NULL
);

CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY identity(1,1),
    LName NVARCHAR(50),
    FName NVARCHAR(50),
    MName NVARCHAR(50),
    Service INT,
    BaseSalary MONEY,
    Work TEXT,
    DepartmentID INT,
	FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID)
	
);

CREATE TABLE Payslip (
   PayslipID INT PRIMARY KEY identity(1,1),
   NetPay MONEY,
   Year INT ,
   Month INT ,
   EmployeeID INT,
   FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
   
);

CREATE TABLE Employee_Allowance (
     EAD_ID INT PRIMARY KEY identity(1,1), 
     HRA MONEY NOT NULL, 
     TA MONEY NOT NULL, 
     MA MONEY NOT NULL, 
     DA MONEY NOT NULL, 
     EffectiveYear INT NOT NULL, 
     EffectiveMonth INT NOT null,  
     EmployeeID INT,
	 FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
	 
);

CREATE TABLE ExtraLeaves (
      ELD_ID INT PRIMARY KEY identity(1,1),  
      EL_amount MONEY,  
      EffectiveYear INT NOT NULL,  
      EffectiveMonth INT NOT NULL,   
      FromDate DATE NOT NULL,   
      ToDate DATE NOT NULL,
      EmployeeID INT,
	  FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
	     
);




-- Insert into Department
INSERT INTO Department ( DepartmentName)
VALUES ('HR'), 
	   ('Sales'), 
	   ('Marketing'), 
	   ('IT'), 
	   ('Finance'), 
	   ('Operations'), 
	   ('R&D'), 
	   ('Customer Service'), 
	   ('Logistics'), 
	   ('Quality Assurance'), 
	   ('Legal');

-- Insert into Employee
INSERT INTO Employee ( LName, FName, MName, Service, BaseSalary, Work, DepartmentID)	
VALUES ( 'Nguyen', 'Anh', 'Tuan', 5, 1000, 'Manager', 1), 
       ('Tran', 'Bao', 'Long', 3, 800, 'Assistant Manager', 1),
       ('Le', 'Thi', 'Lan', 2, 700, 'Senior Associate', 1),
       ( 'Pham', 'Van', 'Hung', 4, 900, 'Associate', 2),
       ( 'Hoang', 'Thi', 'Mai', 1, 600, 'Junior Associate', 2),
       ('Vu', 'Van', 'Nam', 5, 1000, 'Manager', 2),
       ( 'Do', 'Thi', 'Hoa', 3, 800, 'Assistant Manager', 3),
       ( 'Bui', 'Van', 'Binh', 2, 700, 'Senior Associate', 3),
       ( 'Dang', 'Thi', 'Huong', 4, 900, 'Associate', 3),
       ( 'Nguyen', 'Van', 'An', 1, 600, 'Junior Associate', 4);

-- Insert into Employee_Allowance
INSERT INTO Employee_Allowance (HRA, TA, MA, DA, EffectiveYear, EffectiveMonth, EmployeeID)
VALUES ( 100, 50, 30, 20, 2024, 1, 1), 
       ( 80, 40, 24, 16, 2024, 1, 2), 
       ( 70, 35, 21, 14, 2024, 1, 3), 
       ( 90, 45, 27, 18, 2024, 1, 4), 
       ( 60, 30, 18, 12, 2024, 1, 5), 
       ( 100, 50, 30, 20, 2024, 1, 6), 
       ( 80, 40, 24, 16, 2024, 1, 7), 
       ( 70, 35, 21, 14, 2024, 1, 8), 
       ( 90, 45, 27, 18, 2024, 1, 9), 
       ( 60, 30, 18, 12, 2024, 1, 10);


-- Insert into ExtraLeaves
INSERT INTO ExtraLeaves ( EL_amount, EffectiveYear, EffectiveMonth, FromDate, ToDate, EmployeeId)
VALUES ( 10, 2024, 1, '2024-01-01', '2024-01-02', 1), 
       ( 20, 2024, 1, '2024-01-02', '2024-01-04', 2), 
       ( 30, 2024, 1, '2024-01-05', '2024-01-06', 3), 
       ( 40, 2024, 1, '2024-01-07', '2024-01-08', 4), 
       ( 50, 2024, 1, '2024-01-09', '2024-01-10', 5),
       ( 60, 2024, 1, '2024-01-10', '2024-01-12', 6), 
       ( 70, 2024, 1, '2024-01-13', '2024-01-14', 7), 
       ( 80, 2024, 1, '2024-01-15', '2024-01-16', 8), 
       ( 90, 2024, 1, '2024-01-17', '2024-01-18', 9), 
       ( 10, 2024, 1, '2024-01-19', '2024-01-20', 10),
	   (10, 2024, 1, '2024-01-14', '2024-01-16',1),
	   (10, 2024, 1, '2024-01-10', '2024-01-12',2);



