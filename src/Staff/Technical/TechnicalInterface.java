package Staff.Technical;

import java.sql.Date;
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
            System.out.println("3. View All Technical");
            System.out.println("4. Update Technical");
            System.out.println("5. Delete Technical");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addTechnical();
                case 2 -> viewTechnicalById();
                case 3 -> viewAllTechnical();
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
        int staffId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Technical Skills: ");
        String technicalSkills = scanner.nextLine();

        System.out.print("Certifications: ");
        String certifications = scanner.nextLine();

        System.out.print("Work Location: ");
        String workLocation = scanner.nextLine();

        System.out.print("Last Training Date (yyyy-mm-dd): ");
        String lastTrainingDateStr = scanner.nextLine();
        java.sql.Date lastTrainingDate = java.sql.Date.valueOf(lastTrainingDateStr);

        Technical technical = new Technical();
        technical.setId(staffId);  // Assuming staffId is the primary key and maps with Staff table
        technical.setTechnicalSkills(technicalSkills);
        technical.setCertifications(certifications);
        technical.setWorkLocation(workLocation);
        technical.setLastTrainingDate(lastTrainingDate);

        if (TechnicalDAL.insertTechnical(technical)) {
            System.out.println("Technical added successfully.");
        } else {
            System.out.println("Failed to add technical.");
        }
    }

    private static void viewTechnicalById() {
        System.out.println("\n===== View Technical by ID =====");
        System.out.print("Enter Technical ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        Technical technical = TechnicalDAL.getTechnicalById(id);
        if (technical != null) {
            System.out.println(technical);
        } else {
            System.out.println("No technical found with ID " + id);
        }
    }
    
    

    private static void viewAllTechnical() {
        System.out.println("\n===== View All Technical =====");
        List<Technical> technicalList = TechnicalDAL.getAllTechnicals();
        if (technicalList.isEmpty()) {
            System.out.println("No technical records found.");
        } else {
            for (Technical technical : technicalList) {
                System.out.println(technical);
            }
        }
    }
    

    private static void updateTechnical() {
        System.out.println("\n===== Update Technical =====");
        System.out.print("Enter Technical ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

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
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (technicalDAL.deleteTechnical(id)) {
            System.out.println("Technical deleted successfully.");
        } else {
            System.out.println("Failed to delete technical.");
        }
    }
}
