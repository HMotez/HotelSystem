package Staff.Technical;

import java.util.List;
import java.util.Scanner;

public class TechnicalInterface {

    private static final TechnicalDAL technicalDAL = new TechnicalDAL();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Technical Management System =====");
            System.out.println("1. Add Technical");
            System.out.println("2. View Technical by ID");
            System.out.println("3. View All Technicals");
            System.out.println("4. Update Technical");
            System.out.println("5. Delete Technical");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> addTechnical();
                case 2 -> viewTechnicalById();
                case 3 -> viewAllTechnicals();
                case 4 -> updateTechnical();
                case 5 -> deleteTechnical();
                case 6 -> {
                    System.out.println("Exiting Technical Management System.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTechnical() {
        System.out.println("\n===== Add Technical =====");
    
        System.out.print("Staff ID: ");
        int staffId = Integer.parseInt(scanner.nextLine());
    
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
    
        System.out.print("Hire Date (yyyy-mm-dd): ");
        java.sql.Date hireDate = java.sql.Date.valueOf(scanner.nextLine());
    
        System.out.print("Salary: ");
        java.math.BigDecimal salary = new java.math.BigDecimal(scanner.nextLine());
    
        System.out.print("Status: ");
        String status = scanner.nextLine();
    
        System.out.print("Department: ");
        String department = scanner.nextLine();
    
        System.out.print("Job Title: ");
        String jobTitle = scanner.nextLine();
    
        System.out.print("Working Hours: ");
        String workingHours = scanner.nextLine();
    
        System.out.print("Technical Skills: ");
        String technicalSkills = scanner.nextLine();
    
        System.out.print("Certifications: ");
        String certifications = scanner.nextLine();
    
        System.out.print("Work Location: ");
        String workLocation = scanner.nextLine();
    
        System.out.print("Last Training Date (yyyy-mm-dd): ");
        java.sql.Date lastTrainingDate = java.sql.Date.valueOf(scanner.nextLine());
    
        // Create and set Technical object
        Technical technical = new Technical();
        technical.setId(staffId);
        technical.setFirstName(firstName);
        technical.setLastName(lastName);
        technical.setEmail(email);
        technical.setPhoneNumber(phoneNumber);
        technical.setAddress(address);
        technical.setHireDate(hireDate);
        technical.setSalary(salary);
        technical.setStatus(status);
        technical.setDepartment(department);
        technical.setJobTitle(jobTitle);
        technical.setWorkingHours(workingHours);
        technical.setTechnicalSkills(technicalSkills);
        technical.setCertifications(certifications);
        technical.setWorkLocation(workLocation);
        technical.setLastTrainingDate(lastTrainingDate);
    
        if (technicalDAL.insertTechnical(technical)) {
            System.out.println("Technical added successfully.");
        } else {
            System.out.println("Failed to add technical.");
        }
    }
    

    private static void viewTechnicalById() {
        System.out.println("\n===== View Technical by ID =====");
        System.out.print("Enter Technical ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Technical technical = technicalDAL.getTechnicalById(id);
        if (technical != null) {
            System.out.println(technical);
        } else {
            System.out.println("No technical found with ID " + id);
        }
    }

    private static void viewAllTechnicals() {
        System.out.println("\n===== View All Technicals =====");
        List<Technical> technicalList = technicalDAL.getAllTechnicals();
        if (technicalList.isEmpty()) {
            System.out.println("No technical records found.");
        } else {
            technicalList.forEach(System.out::println);
        }
    }

    private static void updateTechnical() {
        System.out.println("\n===== Update Technical =====");
        System.out.print("Enter Technical ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Technical technical = technicalDAL.getTechnicalById(id);
        if (technical == null) {
            System.out.println("No technical found with ID " + id);
            return;
        }

        System.out.print("Technical Skills (" + technical.getTechnicalSkills() + "): ");
        String technicalSkills = scanner.nextLine();
        if (!technicalSkills.isBlank()) technical.setTechnicalSkills(technicalSkills);

        System.out.print("Certifications (" + technical.getCertifications() + "): ");
        String certifications = scanner.nextLine();
        if (!certifications.isBlank()) technical.setCertifications(certifications);

        System.out.print("Work Location (" + technical.getWorkLocation() + "): ");
        String workLocation = scanner.nextLine();
        if (!workLocation.isBlank()) technical.setWorkLocation(workLocation);

        System.out.print("Last Training Date (yyyy-mm-dd) (" + technical.getLastTrainingDate() + "): ");
        String lastTrainingDateStr = scanner.nextLine();
        if (!lastTrainingDateStr.isBlank()) technical.setLastTrainingDate(java.sql.Date.valueOf(lastTrainingDateStr));

        if (technicalDAL.updateTechnical(technical)) {
            System.out.println("Technical updated successfully.");
        } else {
            System.out.println("Failed to update technical.");
        }
    }

    private static void deleteTechnical() {
        System.out.println("\n===== Delete Technical =====");
        System.out.print("Enter Technical ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (technicalDAL.deleteTechnical(id)) {
            System.out.println("Technical deleted successfully.");
        } else {
            System.out.println("Failed to delete technical.");
        }
    }
}
