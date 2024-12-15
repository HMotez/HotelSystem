package Staff.ManagerialStaff;

import java.math.BigDecimal;
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

        System.out.print("Office Location: ");
        String officeLocation = scanner.nextLine();

        System.out.print("Team Size: ");
        int teamSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Reports To (Manager's Name): ");
        String reportsTo = scanner.nextLine();

        ManagerialStaff managerialStaff = new ManagerialStaff();
        managerialStaff.setId(staffId);
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
        List<ManagerialStaff> managerialStaffList = managerialStaffDAL.getAllManagerialStaff();
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

        System.out.print("Office Location (" + managerialStaff.getOfficeLocation() + "): ");
        String officeLocation = scanner.nextLine();
        if (!officeLocation.isBlank()) managerialStaff.setOfficeLocation(officeLocation);

        System.out.print("Team Size (" + managerialStaff.getTeamSize() + "): ");
        int teamSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        managerialStaff.setTeamSize(teamSize);

        System.out.print("Reports To (" + managerialStaff.getReportsTo() + "): ");
        String reportsTo = scanner.nextLine();
        if (!reportsTo.isBlank()) managerialStaff.setReportsTo(reportsTo);

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
}
