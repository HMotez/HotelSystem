package Staff.Operational;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class OperationalInterface {

    private static final OperationalDAL operationalDAL = new OperationalDAL();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Operational Management System =====");
            System.out.println("1. Add Operational");
            System.out.println("2. View Operational by ID");
            System.out.println("3. View All Operationals");
            System.out.println("4. Update Operational");
            System.out.println("5. Delete Operational");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1 -> addOperational();
                case 2 -> viewOperationalById();
                case 3 -> viewAllOperationals();
                case 4 -> updateOperational();
                case 5 -> deleteOperational();
                case 6 -> {
                    System.out.println("Exiting Operational Management System.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addOperational() {
        try {
            System.out.println("\n===== Add Operational =====");
    
            System.out.print("Enter Staff ID: ");
            int staffId = Integer.parseInt(scanner.nextLine());
    
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
            java.util.Date hireDate = java.sql.Date.valueOf(scanner.nextLine()); // Using java.sql.Date for conversion
    
            System.out.print("Enter Salary: ");
            BigDecimal salary = new BigDecimal(scanner.nextLine());
    
            System.out.print("Enter Status: ");
            String status = scanner.nextLine();
    
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();
    
            System.out.print("Enter Job Title: ");
            String jobTitle = scanner.nextLine();
    
            System.out.print("Enter Working Hours: ");
            String workingHours = scanner.nextLine();
    
            // Operational-specific attributes
            System.out.print("Enter Shift Type: ");
            String shiftType = scanner.nextLine();
    
            System.out.print("Enter Location: ");
            String location = scanner.nextLine();
    
            System.out.print("Enter Responsibility Level: ");
            String responsibilityLevel = scanner.nextLine();
    
            System.out.print("Enter Task Count: ");
            int taskCount = Integer.parseInt(scanner.nextLine());
    
            System.out.print("Enter Performance Rating: ");
            BigDecimal performanceRating = new BigDecimal(scanner.nextLine());
    
            // Create an Operational object
            Operational operational = new Operational(
                staffId, firstName, lastName, email, phoneNumber, address, hireDate,
                salary, status, department, jobTitle, workingHours, shiftType, location,
                responsibilityLevel, taskCount, performanceRating
            );
    
            // Insert into the database
            if (operationalDAL.insertOperational(operational)) {
                System.out.println("Operational added successfully.");
            } else {
                System.out.println("Failed to add operational.");
            }
        } catch (Exception e) {
            System.out.println("Error adding operational. Please ensure all inputs are valid.");
            e.printStackTrace(); // Optional: Remove this in production
        }
    }
    

    private static void viewOperationalById() {
        try {
            System.out.println("\n===== View Operational by ID =====");
            System.out.print("Enter Operational ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            Operational operational = operationalDAL.getOperationalById(id);
            if (operational != null) {
                System.out.println("Operational Details: \n" + operational);
            } else {
                System.out.println("No operational found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving operational. Please ensure the input is valid.");
        }
    }

    private static void viewAllOperationals() {
        System.out.println("\n===== View All Operationals =====");
        List<Operational> operationalList = operationalDAL.getAllOperationals();
        if (operationalList == null || operationalList.isEmpty()) {
            System.out.println("No operational records found.");
        } else {
            for (Operational operational : operationalList) {
                System.out.println(operational);
            }
        }
    }

    private static void updateOperational() {
        try {
            System.out.println("\n===== Update Operational =====");
            System.out.print("Enter Operational ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());
    
            Operational operational = operationalDAL.getOperationalById(id);
            if (operational == null) {
                System.out.println("No operational found with ID " + id);
                return;
            }
    
            // Update fields inherited from Staff
            System.out.print("Enter First Name (" + operational.getFirstName() + "): ");
            String firstName = scanner.nextLine();
            if (!firstName.isBlank()) operational.setFirstName(firstName);
    
            System.out.print("Enter Last Name (" + operational.getLastName() + "): ");
            String lastName = scanner.nextLine();
            if (!lastName.isBlank()) operational.setLastName(lastName);
    
            System.out.print("Enter Email (" + operational.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) operational.setEmail(email);
    
            System.out.print("Enter Phone Number (" + operational.getPhoneNumber() + "): ");
            String phoneNumber = scanner.nextLine();
            if (!phoneNumber.isBlank()) operational.setPhoneNumber(phoneNumber);
    
            System.out.print("Enter Address (" + operational.getAddress() + "): ");
            String address = scanner.nextLine();
            if (!address.isBlank()) operational.setAddress(address);
    
            System.out.print("Enter Hire Date (yyyy-mm-dd, current: " + operational.getHireDate() + "): ");
            String hireDateInput = scanner.nextLine();
            if (!hireDateInput.isBlank()) {
                operational.setHireDate(java.sql.Date.valueOf(hireDateInput));
            }
    
            System.out.print("Enter Salary (" + operational.getSalary() + "): ");
            String salaryInput = scanner.nextLine();
            if (!salaryInput.isBlank()) operational.setSalary(new BigDecimal(salaryInput));
    
            System.out.print("Enter Status (" + operational.getStatus() + "): ");
            String status = scanner.nextLine();
            if (!status.isBlank()) operational.setStatus(status);
    
            System.out.print("Enter Department (" + operational.getDepartment() + "): ");
            String department = scanner.nextLine();
            if (!department.isBlank()) operational.setDepartment(department);
    
            System.out.print("Enter Job Title (" + operational.getJobTitle() + "): ");
            String jobTitle = scanner.nextLine();
            if (!jobTitle.isBlank()) operational.setJobTitle(jobTitle);
    
            System.out.print("Enter Working Hours (" + operational.getWorkingHours() + "): ");
            String workingHours = scanner.nextLine();
            if (!workingHours.isBlank()) operational.setWorkingHours(workingHours);
    
            // Update fields specific to Operational
            System.out.print("Enter Shift Type (" + operational.getShiftType() + "): ");
            String shiftType = scanner.nextLine();
            if (!shiftType.isBlank()) operational.setShiftType(shiftType);
    
            System.out.print("Enter Location (" + operational.getLocation() + "): ");
            String location = scanner.nextLine();
            if (!location.isBlank()) operational.setLocation(location);
    
            System.out.print("Enter Responsibility Level (" + operational.getResponsibilityLevel() + "): ");
            String responsibilityLevel = scanner.nextLine();
            if (!responsibilityLevel.isBlank()) operational.setResponsibilityLevel(responsibilityLevel);
    
            System.out.print("Enter Task Count (" + operational.getTaskCount() + "): ");
            String taskCountInput = scanner.nextLine();
            if (!taskCountInput.isBlank()) operational.setTaskCount(Integer.parseInt(taskCountInput));
    
            System.out.print("Enter Performance Rating (" + operational.getPerformanceRating() + "): ");
            String performanceRatingInput = scanner.nextLine();
            if (!performanceRatingInput.isBlank()) {
                operational.setPerformanceRating(new BigDecimal(performanceRatingInput));
            }
    
            // Update in database
            if (operationalDAL.updateOperational(operational)) {
                System.out.println("Operational updated successfully.");
            } else {
                System.out.println("Failed to update operational.");
            }
        } catch (Exception e) {
            System.out.println("Error updating operational. Please ensure all inputs are valid.");
            e.printStackTrace(); // Optional for debugging; remove in production
        }
    }
    

    private static void deleteOperational() {
        try {
            System.out.println("\n===== Delete Operational =====");
            System.out.print("Enter Operational ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (operationalDAL.deleteOperational(id)) {
                System.out.println("Operational deleted successfully.");
            } else {
                System.out.println("Failed to delete operational.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting operational. Please ensure the input is valid.");
        }
    }
}
