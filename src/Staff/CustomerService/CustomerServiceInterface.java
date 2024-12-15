package Staff.CustomerService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CustomerServiceInterface {

    private static final CustomerServiceDAL customerServiceDAL = new CustomerServiceDAL();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Customer Service Management System =====");
            System.out.println("1. Add Customer Service");
            System.out.println("2. View Customer Service by ID");
            System.out.println("3. View All Customer Services");
            System.out.println("4. Update Customer Service");
            System.out.println("5. Delete Customer Service");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCustomerService();
                case 2 -> viewCustomerServiceById();
                case 3 -> viewAllCustomerServices();
                case 4 -> updateCustomerService();
                case 5 -> deleteCustomerService();
                case 6 -> {
                    System.out.println("Exiting Customer Service Management System.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCustomerService() {
        System.out.println("\n===== Add Customer Service =====");

        System.out.print("Customer Service ID: ");
        int id = scanner.nextInt();
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

        System.out.print("Customer Satisfaction Score: ");
        BigDecimal satisfactionScore = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline

        System.out.print("Languages Spoken: ");
        String languagesSpoken = scanner.nextLine();

        System.out.print("Shift: ");
        String shift = scanner.nextLine();

        System.out.print("Feedback Received: ");
        String feedbackReceived = scanner.nextLine();

        CustomerService customerService = new CustomerService(id, firstName, lastName, email, phoneNumber, address, hireDate, salary, status, department, jobTitle, workingHours, satisfactionScore, languagesSpoken, shift, feedbackReceived);

        if (customerServiceDAL.insertCustomerService(customerService)) {
            System.out.println("Customer Service added successfully.");
        } else {
            System.out.println("Failed to add Customer Service.");
        }
    }

    private static void viewCustomerServiceById() {
        System.out.println("\n===== View Customer Service by ID =====");
        System.out.print("Enter Customer Service ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        CustomerService customerService = customerServiceDAL.getCustomerServiceById(id);
        if (customerService != null) {
            System.out.println(customerService);
        } else {
            System.out.println("No Customer Service found with ID " + id);
        }
    }

    private static void viewAllCustomerServices() {
        System.out.println("\n===== View All Customer Services =====");
        List<CustomerService> customerServiceList = customerServiceDAL.getAllCustomerServices();
        if (customerServiceList.isEmpty()) {
            System.out.println("No Customer Service records found.");
        } else {
            for (CustomerService customerService : customerServiceList) {
                System.out.println(customerService);
            }
        }
    }

    private static void updateCustomerService() {
        System.out.println("\n===== Update Customer Service =====");
        System.out.print("Enter Customer Service ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        CustomerService customerService = customerServiceDAL.getCustomerServiceById(id);
        if (customerService == null) {
            System.out.println("No Customer Service found with ID " + id);
            return;
        }
    
        // Update first name
        System.out.print("First Name (" + customerService.getFirstName() + "): ");
        String firstName = scanner.nextLine();
        if (!firstName.isBlank()) customerService.setFirstName(firstName);
    
        // Update last name
        System.out.print("Last Name (" + customerService.getLastName() + "): ");
        String lastName = scanner.nextLine();
        if (!lastName.isBlank()) customerService.setLastName(lastName);
    
        // Update email
        System.out.print("Email (" + customerService.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) customerService.setEmail(email);
    
        // Update phone number
        System.out.print("Phone Number (" + customerService.getPhoneNumber() + "): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isBlank()) customerService.setPhoneNumber(phoneNumber);
    
        // Update address
        System.out.print("Address (" + customerService.getAddress() + "): ");
        String address = scanner.nextLine();
        if (!address.isBlank()) customerService.setAddress(address);
    
        // Update hire date
        System.out.print("Hire Date (" + customerService.getHireDate() + "): ");
        String hireDateString = scanner.nextLine();
        if (!hireDateString.isBlank()) {
            Date hireDate = parseDate(hireDateString);
            customerService.setHireDate(hireDate);
        }
    
        // Update salary
        System.out.print("Salary (" + customerService.getSalary() + "): ");
        BigDecimal salary = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline
        if (salary.compareTo(BigDecimal.ZERO) > 0) customerService.setSalary(salary);
    
        // Update status
        System.out.print("Status (" + customerService.getStatus() + "): ");
        String status = scanner.nextLine();
        if (!status.isBlank()) customerService.setStatus(status);
    
        // Update department
        System.out.print("Department (" + customerService.getDepartment() + "): ");
        String department = scanner.nextLine();
        if (!department.isBlank()) customerService.setDepartment(department);
    
        // Update job title
        System.out.print("Job Title (" + customerService.getJobTitle() + "): ");
        String jobTitle = scanner.nextLine();
        if (!jobTitle.isBlank()) customerService.setJobTitle(jobTitle);
    
        // Update working hours
        System.out.print("Working Hours (" + customerService.getWorkingHours() + "): ");
        String workingHours = scanner.nextLine();
        if (!workingHours.isBlank()) customerService.setWorkingHours(workingHours);
    
        // Update customer satisfaction score
        System.out.print("Customer Satisfaction Score (" + customerService.getCustomerSatisfactionScore() + "): ");
        BigDecimal satisfactionScore = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline
        if (satisfactionScore.compareTo(BigDecimal.ZERO) >= 0) customerService.setCustomerSatisfactionScore(satisfactionScore);
    
        // Update languages spoken
        System.out.print("Languages Spoken (" + customerService.getLanguagesSpoken() + "): ");
        String languagesSpoken = scanner.nextLine();
        if (!languagesSpoken.isBlank()) customerService.setLanguagesSpoken(languagesSpoken);
    
        // Update shift
        System.out.print("Shift (" + customerService.getShift() + "): ");
        String shift = scanner.nextLine();
        if (!shift.isBlank()) customerService.setShift(shift);
    
        // Update feedback received
        System.out.print("Feedback Received (" + customerService.getFeedbackReceived() + "): ");
        String feedbackReceived = scanner.nextLine();
        if (!feedbackReceived.isBlank()) customerService.setFeedbackReceived(feedbackReceived);
    
        // Update CustomerService in the database
        if (customerServiceDAL.updateCustomerService(customerService)) {
            System.out.println("Customer Service updated successfully.");
        } else {
            System.out.println("Failed to update Customer Service.");
        }
    }
    

    private static void deleteCustomerService() {
        System.out.println("\n===== Delete Customer Service =====");
        System.out.print("Enter Customer Service ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (customerServiceDAL.deleteCustomerService(id)) {
            System.out.println("Customer Service deleted successfully.");
        } else {
            System.out.println("Failed to delete Customer Service.");
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
