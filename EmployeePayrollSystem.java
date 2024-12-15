import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee 
{
    private String name;
    private int id;

    public Employee(String name, int id) 
	{
        this.name = name;
        this.id = id;
    }

    public String getName() 
	{
        return name;
    }

    public int getId()
	{
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() 
	{
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee 
{
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary)
	{
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary()
	{
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee 
{
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) 
	{
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() 
	{
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem 
{
    private List<Employee> employeeList;

    public PayrollSystem() 
	{
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) 
	{
        employeeList.add(employee);
        System.out.println("Employee added successfully.");
    }

    public void removeEmployee(int id)
	{
        Employee employeeToRemove = null;
        for (Employee employee : employeeList)
		{
            if (employee.getId() == id) 
			{
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) 
		{
            employeeList.remove(employeeToRemove);
            System.out.println("Employee with ID " + id + " removed successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void displayEmployees() 
	{
        if (employeeList.isEmpty())
		{
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : employeeList)
			{
                System.out.println(employee);
            }
        }
    }

    public void fillEmployeeDetails() 
	{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee type (1 for Full-Time, 2 for Part-Time): ");
        int type = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.println("Enter employee ID: ");
        int id = scanner.nextInt();

        if (type == 1) 
		{
            System.out.println("Enter monthly salary: ");
            double monthlySalary = scanner.nextDouble();
            addEmployee(new FullTimeEmployee(name, id, monthlySalary));
        } else if (type == 2)
		{
            System.out.println("Enter hours worked: ");
            int hoursWorked = scanner.nextInt();

            System.out.println("Enter hourly rate: ");
            double hourlyRate = scanner.nextDouble();

            addEmployee(new PartTimeEmployee(name, id, hoursWorked, hourlyRate));
        } else
		{
            System.out.println("Invalid employee type.");
        }
    }
}

public class EmployeePayrollSystem
{
    public static void main(String[] args)
	{
        PayrollSystem payrollSystem = new PayrollSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running)
		{
            System.out.println("\n--- Payroll System Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Display Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice)
			{
                case 1:
                    payrollSystem.fillEmployeeDetails();
                    break;
                case 2:
                    System.out.print("Enter employee ID to remove: ");
                    int id = scanner.nextInt();
                    payrollSystem.removeEmployee(id);
                    break;
                case 3:
                    payrollSystem.displayEmployees();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting Payroll System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
