package Staff.CustomerService;

import Utils.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceDAL {

    // Method to add a new CustomerService record
    public boolean addCustomerService(CustomerService customerService) {
        String sql = "INSERT INTO CustomerService (id, customerSatisfactionScore, languagesSpoken, shift, feedbackReceived) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, customerService.getId());
            preparedStatement.setBigDecimal(2, customerService.getCustomerSatisfactionScore());
            preparedStatement.setString(3, customerService.getLanguagesSpoken());
            preparedStatement.setString(4, customerService.getShift());
            preparedStatement.setString(5, customerService.getFeedbackReceived());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a CustomerService record by ID
    public CustomerService getCustomerServiceById(int id) {
        String sql = "SELECT * FROM CustomerService WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToCustomerService(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get all CustomerService records
    public List<CustomerService> getAllCustomerServices() {
        String sql = "SELECT * FROM CustomerService";
        List<CustomerService> customerServiceList = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                customerServiceList.add(mapToCustomerService(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerServiceList;
    }

    // Method to update a CustomerService record
    public boolean updateCustomerService(CustomerService customerService) {
        String sql = "UPDATE CustomerService SET customerSatisfactionScore = ?, languagesSpoken = ?, shift = ?, feedbackReceived = ? WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setBigDecimal(1, customerService.getCustomerSatisfactionScore());
            preparedStatement.setString(2, customerService.getLanguagesSpoken());
            preparedStatement.setString(3, customerService.getShift());
            preparedStatement.setString(4, customerService.getFeedbackReceived());
            preparedStatement.setInt(5, customerService.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a CustomerService record by ID
    public boolean deleteCustomerService(int id) {
        String sql = "DELETE FROM CustomerService WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to map ResultSet to CustomerService object
    private CustomerService mapToCustomerService(ResultSet resultSet) throws SQLException {
        CustomerService customerService = new CustomerService();
        customerService.setId(resultSet.getInt("id"));
        customerService.setCustomerSatisfactionScore(resultSet.getBigDecimal("customerSatisfactionScore"));
        customerService.setLanguagesSpoken(resultSet.getString("languagesSpoken"));
        customerService.setShift(resultSet.getString("shift"));
        customerService.setFeedbackReceived(resultSet.getString("feedbackReceived"));
        return customerService;
    }
}
