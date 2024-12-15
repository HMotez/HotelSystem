package Staff;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StaffInterface {

    private static final StaffDAL staffDAL = new StaffDAL();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Staff Management System =====");
            System.out.println("1. Add Staff");
            System.out.println("2. View Staff by ID");
            System.out.println("3. View All Staff");
            System.out.println("4. Update Staff");
            System.out.println("5. Delete Staff");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStaff();
                case 2 -> viewStaffById();
                case 3 -> viewAllStaff();
                case 4 -> updateStaff();
                case 5 -> deleteStaff();
                case 6 -> {
                    System.out.println("Exiting Staff Management System.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStaff() {
        System.out.println("\n===== Add Staff =====");
    
        // Prompt for the 'id'
        System.out.print("Enter Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
    
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
    
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
    
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
    
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
    
        System.out.print("Enter Hire Date (yyyy-mm-dd): ");
        Date hireDate = java.sql.Date.valueOf(scanner.nextLine());
    
        System.out.print("Enter Salary: ");
        BigDecimal salary = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline
    
        System.out.print("Enter Status: ");
        String status = scanner.nextLine();
    
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
    
        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();
    
        System.out.print("Enter Working Hours: ");
        String workingHours = scanner.nextLine();
    
        // Create a Staff object and set the details
        Staff staff = new Staff();
        staff.setId(id);  // Set the 'id' from user input
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setEmail(email);
        staff.setPhoneNumber(phoneNumber);
        staff.setAddress(address);
        staff.setHireDate(hireDate);
        staff.setSalary(salary);
        staff.setStatus(status);
        staff.setDepartment(department);
        staff.setJobTitle(jobTitle);
        staff.setWorkingHours(workingHours);
    
        // Add the staff to the database
        if (staffDAL.addStaff(staff)) {
            System.out.println("Staff added successfully.");
        } else {
            System.out.println("Failed to add staff.");
        }
    }
    

    private static void viewStaffById() {
        System.out.println("\n===== View Staff by ID =====");
        System.out.print("Enter Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Staff staff = staffDAL.getStaffById(id);
        if (staff != null) {
            System.out.println(staff);
        } else {
            System.out.println("No staff found with ID " + id);
        }
    }

    private static void viewAllStaff() {
        System.out.println("\n===== View All Staff =====");
        List<Staff> staffList = staffDAL.getAllStaff();
        if (staffList.isEmpty()) {
            System.out.println("No staff records found.");
        } else {
            for (Staff staff : staffList) {
                System.out.println(staff);
            }
        }
    }

    private static void updateStaff() {
        System.out.println("\n===== Update Staff =====");
        System.out.print("Enter Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Staff staff = staffDAL.getStaffById(id);
        if (staff == null) {
            System.out.println("No staff found with ID " + id);
            return;
        }

        System.out.print("First Name (" + staff.getFirstName() + "): ");
        String firstName = scanner.nextLine();
        if (!firstName.isBlank()) staff.setFirstName(firstName);

        System.out.print("Last Name (" + staff.getLastName() + "): ");
        String lastName = scanner.nextLine();
        if (!lastName.isBlank()) staff.setLastName(lastName);

        System.out.print("Email (" + staff.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) staff.setEmail(email);

        System.out.print("Phone Number (" + staff.getPhoneNumber() + "): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isBlank()) staff.setPhoneNumber(phoneNumber);

        System.out.print("Address (" + staff.getAddress() + "): ");
        String address = scanner.nextLine();
        if (!address.isBlank()) staff.setAddress(address);

        System.out.print("Hire Date (yyyy-mm-dd) (" + staff.getHireDate() + "): ");
        String hireDateInput = scanner.nextLine();
        if (!hireDateInput.isBlank()) staff.setHireDate(java.sql.Date.valueOf(hireDateInput));

        System.out.print("Salary (" + staff.getSalary() + "): ");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isBlank()) staff.setSalary(new BigDecimal(salaryInput));

        System.out.print("Status (" + staff.getStatus() + "): ");
        String status = scanner.nextLine();
        if (!status.isBlank()) staff.setStatus(status);

        System.out.print("Department (" + staff.getDepartment() + "): ");
        String department = scanner.nextLine();
        if (!department.isBlank()) staff.setDepartment(department);

        System.out.print("Job Title (" + staff.getJobTitle() + "): ");
        String jobTitle = scanner.nextLine();
        if (!jobTitle.isBlank()) staff.setJobTitle(jobTitle);

        System.out.print("Working Hours (" + staff.getWorkingHours() + "): ");
        String workingHours = scanner.nextLine();
        if (!workingHours.isBlank()) staff.setWorkingHours(workingHours);

        if (staffDAL.updateStaff(staff)) {
            System.out.println("Staff updated successfully.");
        } else {
            System.out.println("Failed to update staff.");
        }
    }

    private static void deleteStaff() {
        System.out.println("\n===== Delete Staff =====");
        System.out.print("Enter Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (staffDAL.deleteStaff(id)) {
            System.out.println("Staff deleted successfully.");
        } else {
            System.out.println("Failed to delete staff.");
        }
    }
}
