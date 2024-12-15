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

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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

    // Add a new Operational record
    private static void addOperational() {
        System.out.println("\n===== Add Operational =====");

        System.out.print("Enter Staff ID: ");
        int staffId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Shift Type: ");
        String shiftType = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Responsibility Level: ");
        String responsibilityLevel = scanner.nextLine();

        System.out.print("Enter Task Count: ");
        int taskCount = scanner.nextInt();

        System.out.print("Enter Performance Rating: ");
        BigDecimal performanceRating = scanner.nextBigDecimal();

        Operational operational = new Operational();
        operational.setId(staffId);
        operational.setShiftType(shiftType);
        operational.setLocation(location);
        operational.setResponsibilityLevel(responsibilityLevel);
        operational.setTaskCount(taskCount);
        operational.setPerformanceRating(performanceRating);

        if (operationalDAL.insertOperational(operational)) {
            System.out.println("Operational added successfully.");
        } else {
            System.out.println("Failed to add operational.");
        }
    }

    // View an Operational record by ID
    private static void viewOperationalById() {
        System.out.println("\n===== View Operational by ID =====");
        System.out.print("Enter Operational ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Operational operational = OperationalDAL.getOperationalById(id);
        if (operational != null) {
            System.out.println("Operational Details: " + operational);
        } else {
            System.out.println("No operational found with ID " + id);
        }
    }

    // View all Operational records
    private static void viewAllOperationals() {
        System.out.println("\n===== View All Operationals =====");
        List<Operational> operationalList = OperationalDAL.getAllOperationals();
        if (operationalList.isEmpty()) {
            System.out.println("No operational records found.");
        } else {
            for (Operational operational : operationalList) {
                System.out.println(operational);
            }
        }
    }

    // Update an existing Operational record
    private static void updateOperational() {
        System.out.println("\n===== Update Operational =====");
        System.out.print("Enter Operational ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Operational operational = OperationalDAL.getOperationalById(id);
        if (operational == null) {
            System.out.println("No operational found with ID " + id);
            return;
        }

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
        int taskCount = scanner.nextInt();
        operational.setTaskCount(taskCount);

        System.out.print("Enter Performance Rating (" + operational.getPerformanceRating() + "): ");
        BigDecimal performanceRating = scanner.nextBigDecimal();
        operational.setPerformanceRating(performanceRating);

        if (operationalDAL.updateOperational(operational)) {
            System.out.println("Operational updated successfully.");
        } else {
            System.out.println("Failed to update operational.");
        }
    }

    // Delete an Operational record by ID
    private static void deleteOperational() {
        System.out.println("\n===== Delete Operational =====");
        System.out.print("Enter Operational ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (OperationalDAL.deleteOperational(id)) {
            System.out.println("Operational deleted successfully.");
        } else {
            System.out.println("Failed to delete operational.");
        }
    }
}
