USE PAYROLL_MANAGEMENT


--show all table
select * from ExtraLeaves
select * from Payslip 
select* from Employee
select * from Employee_Allowance
select * from Department


-- the logic/formula to calculate netpay
SELECT e.EmployeeID, e.FName, e.LName, (COALESCE(AVG(e.BaseSalary), 0) + COALESCE(AVG(a.HRA + a.TA + a.MA + a.DA), 0) - COALESCE(SUM(l.EL_amount), 0)) AS NetSalary, a.EffectiveYear, a.EffectiveMonth FROM Employee e LEFT JOIN Employee_Allowance a ON e.EmployeeID = a.EmployeeID LEFT JOIN ExtraLeaves l ON e.EmployeeID = l.EmployeeID AND a.EffectiveYear = l.EffectiveYear AND a.EffectiveMonth = l.EffectiveMonth GROUP BY e.EmployeeID, e.FName, e.LName, e.BaseSalary, a.EffectiveYear, a.EffectiveMonth ORDER BY e.EmployeeID, a.EffectiveYear, a.EffectiveMonth;



--way to insert data and stored value in 
delete payslip
INSERT INTO Payslip (EmployeeID, NetPay, Year, Month)
SELECT e.EmployeeID, 
       (COALESCE(AVG(e.BaseSalary), 0) + COALESCE(AVG(a.HRA + a.TA + a.MA + a.DA), 0) - COALESCE(SUM(l.EL_amount), 0)) AS NetSalary, 
       a.EffectiveYear, a.EffectiveMonth
FROM Employee e
LEFT JOIN Employee_Allowance a ON e.EmployeeID = a.EmployeeID
LEFT JOIN ExtraLeaves l ON e.EmployeeID = l.EmployeeID AND a.EffectiveYear = l.EffectiveYear AND a.EffectiveMonth = l.EffectiveMonth
GROUP BY e.EmployeeID, a.EffectiveYear, a.EffectiveMonth;


