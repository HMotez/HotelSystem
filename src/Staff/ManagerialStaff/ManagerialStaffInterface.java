package Staff.ManagerialStaff;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ManagerialStaffInterface {

    private static final ManagerialStaffDAL managerialStaffDAL = new ManagerialStaffDAL();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Managerial Staff Management System =====");
            System.out.println("1. Add Managerial Staff");
            System.out.println("2. View Managerial Staff by ID");
            System.out.println("3. View All Managerial Staff");
            System.out.println("4. Update Managerial Staff");
            System.out.println("5. Delete Managerial Staff");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addManagerialStaff();
                case 2 -> viewManagerialStaffById();
                case 3 -> viewAllManagerialStaff();
                case 4 -> updateManagerialStaff();
                case 5 -> deleteManagerialStaff();
                case 6 -> {
                    System.out.println("Exiting Managerial Staff Management System.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addManagerialStaff() {
        System.out.println("\n===== Add Managerial Staff =====");

        System.out.print("Staff ID: ");
        int staffId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Hire Date (YYYY-MM-DD): ");
        String hireDateString = scanner.nextLine();
        Date hireDate = parseDate(hireDateString);

        System.out.print("Salary: ");
        BigDecimal salary = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline

        System.out.print("Status: ");
        String status = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        System.out.print("Job Title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Working Hours: ");
        String workingHours = scanner.nextLine();

        System.out.print("Office Location: ");
        String officeLocation = scanner.nextLine();

        System.out.print("Team Size: ");
        int teamSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Reports To (Manager's Name): ");
        String reportsTo = scanner.nextLine();

        ManagerialStaff managerialStaff = new ManagerialStaff();
        managerialStaff.setId(staffId);
        managerialStaff.setFirstName(firstName);
        managerialStaff.setLastName(lastName);
        managerialStaff.setEmail(email);
        managerialStaff.setPhoneNumber(phoneNumber);
        managerialStaff.setAddress(address);
        managerialStaff.setHireDate(hireDate);
        managerialStaff.setSalary(salary);
        managerialStaff.setStatus(status);
        managerialStaff.setDepartment(department);
        managerialStaff.setJobTitle(jobTitle);
        managerialStaff.setWorkingHours(workingHours);
        managerialStaff.setOfficeLocation(officeLocation);
        managerialStaff.setTeamSize(teamSize);
        managerialStaff.setReportsTo(reportsTo);

        if (managerialStaffDAL.insertManagerialStaff(managerialStaff)) {
            System.out.println("Managerial Staff added successfully.");
        } else {
            System.out.println("Failed to add managerial staff.");
        }
    }

    private static void viewManagerialStaffById() {
        System.out.println("\n===== View Managerial Staff by ID =====");
        System.out.print("Enter Managerial Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ManagerialStaff managerialStaff = managerialStaffDAL.getManagerialStaffById(id);
        if (managerialStaff != null) {
            System.out.println(managerialStaff);
        } else {
            System.out.println("No managerial staff found with ID " + id);
        }
    }

    private static void viewAllManagerialStaff() {
        System.out.println("\n===== View All Managerial Staff =====");
        List<ManagerialStaff> managerialStaffList = managerialStaffDAL.getAllManagerialStaffs();
        if (managerialStaffList.isEmpty()) {
            System.out.println("No managerial staff records found.");
        } else {
            for (ManagerialStaff managerialStaff : managerialStaffList) {
                System.out.println(managerialStaff);
            }
        }
    }

    private static void updateManagerialStaff() {
        System.out.println("\n===== Update Managerial Staff =====");
        System.out.print("Enter Managerial Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        ManagerialStaff managerialStaff = managerialStaffDAL.getManagerialStaffById(id);
        if (managerialStaff == null) {
            System.out.println("No managerial staff found with ID " + id);
            return;
        }
    
        // Update first name
        System.out.print("First Name (" + managerialStaff.getFirstName() + "): ");
        String firstName = scanner.nextLine();
        if (!firstName.isBlank()) managerialStaff.setFirstName(firstName);
    
        // Update last name
        System.out.print("Last Name (" + managerialStaff.getLastName() + "): ");
        String lastName = scanner.nextLine();
        if (!lastName.isBlank()) managerialStaff.setLastName(lastName);
    
        // Update email
        System.out.print("Email (" + managerialStaff.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) managerialStaff.setEmail(email);
    
        // Update phone number
        System.out.print("Phone Number (" + managerialStaff.getPhoneNumber() + "): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isBlank()) managerialStaff.setPhoneNumber(phoneNumber);
    
        // Update address
        System.out.print("Address (" + managerialStaff.getAddress() + "): ");
        String address = scanner.nextLine();
        if (!address.isBlank()) managerialStaff.setAddress(address);
    
        // Update hire date
        System.out.print("Hire Date (" + managerialStaff.getHireDate() + "): ");
        String hireDateString = scanner.nextLine();
        Date hireDate = parseDate(hireDateString);
        if (hireDate != null) managerialStaff.setHireDate(hireDate);
    
        // Update salary
        System.out.print("Salary (" + managerialStaff.getSalary() + "): ");
        BigDecimal salary = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline
        managerialStaff.setSalary(salary);
    
        // Update status
        System.out.print("Status (" + managerialStaff.getStatus() + "): ");
        String status = scanner.nextLine();
        if (!status.isBlank()) managerialStaff.setStatus(status);
    
        // Update department
        System.out.print("Department (" + managerialStaff.getDepartment() + "): ");
        String department = scanner.nextLine();
        if (!department.isBlank()) managerialStaff.setDepartment(department);
    
        // Update job title
        System.out.print("Job Title (" + managerialStaff.getJobTitle() + "): ");
        String jobTitle = scanner.nextLine();
        if (!jobTitle.isBlank()) managerialStaff.setJobTitle(jobTitle);
    
        // Update working hours
        System.out.print("Working Hours (" + managerialStaff.getWorkingHours() + "): ");
        String workingHours = scanner.nextLine();
        if (!workingHours.isBlank()) managerialStaff.setWorkingHours(workingHours);
    
        // Update office location
        System.out.print("Office Location (" + managerialStaff.getOfficeLocation() + "): ");
        String officeLocation = scanner.nextLine();
        if (!officeLocation.isBlank()) managerialStaff.setOfficeLocation(officeLocation);
    
        // Update team size
        System.out.print("Team Size (" + managerialStaff.getTeamSize() + "): ");
        int teamSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        managerialStaff.setTeamSize(teamSize);
    
        // Update reports to (manager's name)
        System.out.print("Reports To (" + managerialStaff.getReportsTo() + "): ");
        String reportsTo = scanner.nextLine();
        if (!reportsTo.isBlank()) managerialStaff.setReportsTo(reportsTo);
    
        // Update the managerial staff record
        if (managerialStaffDAL.updateManagerialStaff(managerialStaff)) {
            System.out.println("Managerial Staff updated successfully.");
        } else {
            System.out.println("Failed to update managerial staff.");
        }
    }
    

    private static void deleteManagerialStaff() {
        System.out.println("\n===== Delete Managerial Staff =====");
        System.out.print("Enter Managerial Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (managerialStaffDAL.deleteManagerialStaff(id)) {
            System.out.println("Managerial Staff deleted successfully.");
        } else {
            System.out.println("Failed to delete managerial staff.");
        }
    }

    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }
}
