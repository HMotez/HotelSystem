import Staff.StaffInterface;
import Staff.Technical.*;
import Staff.Operational.OperationalInterface;
import Staff.ManagerialStaff.ManagerialStaffInterface;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Staff Management System");
            System.out.println("2. Technical Management System");
            System.out.println("3. Operational Management System");
            System.out.println("4. Managerial Staff Management System");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> StaffInterface.main(args);
                case 2 -> TechnicalInterface.main(args);
                case 3 -> OperationalInterface.main(args);  // Add Operational Interface
                case 4 -> ManagerialStaffInterface.main(args);  // Add Managerial Staff Interface
                case 5 -> {
                    System.out.println("Exiting Main Menu.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
