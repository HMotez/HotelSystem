package Staff.ManagerialStaff;


import Utils.DbConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerialStaffDAL {

    // Insert a new ManagerialStaff record
    public boolean insertManagerialStaff(ManagerialStaff managerialStaff) {
        String query = "INSERT INTO managerialstaff (id, firstName, lastName, email, phoneNumber, address, hireDate, salary, status, department, jobTitle, workingHours, officeLocation, teamSize, reportsTo) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, managerialStaff.getId());
            preparedStatement.setString(2, managerialStaff.getFirstName());
            preparedStatement.setString(3, managerialStaff.getLastName());
            preparedStatement.setString(4, managerialStaff.getEmail());
            preparedStatement.setString(5, managerialStaff.getPhoneNumber());
            preparedStatement.setString(6, managerialStaff.getAddress());
            preparedStatement.setDate(7, new java.sql.Date(managerialStaff.getHireDate().getTime())); // Proper conversion
            preparedStatement.setBigDecimal(8, managerialStaff.getSalary());
            preparedStatement.setString(9, managerialStaff.getStatus());
            preparedStatement.setString(10, managerialStaff.getDepartment());
            preparedStatement.setString(11, managerialStaff.getJobTitle());
            preparedStatement.setString(12, managerialStaff.getWorkingHours());
            preparedStatement.setString(13, managerialStaff.getOfficeLocation());
            preparedStatement.setInt(14, managerialStaff.getTeamSize());
            preparedStatement.setString(15, managerialStaff.getReportsTo());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get a ManagerialStaff record by ID
    public ManagerialStaff getManagerialStaffById(int id) {
        String query = "SELECT * FROM managerialstaff WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToManagerialStaff(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all ManagerialStaff records
    public List<ManagerialStaff> getAllManagerialStaffs() {
        String query = "SELECT * FROM managerialstaff";
        List<ManagerialStaff> managerialStaffList = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                managerialStaffList.add(mapResultSetToManagerialStaff(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managerialStaffList;
    }

    // Update a ManagerialStaff record
    public boolean updateManagerialStaff(ManagerialStaff managerialStaff) {
        String query = "UPDATE managerialstaff SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, address = ?, hireDate = ?, salary = ?, status = ?, department = ?, jobTitle = ?, workingHours = ?, officeLocation = ?, teamSize = ?, reportsTo = ? WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, managerialStaff.getFirstName());
            preparedStatement.setString(2, managerialStaff.getLastName());
            preparedStatement.setString(3, managerialStaff.getEmail());
            preparedStatement.setString(4, managerialStaff.getPhoneNumber());
            preparedStatement.setString(5, managerialStaff.getAddress());
            preparedStatement.setDate(6, new java.sql.Date(managerialStaff.getHireDate().getTime())); // Proper conversion
            preparedStatement.setBigDecimal(7, managerialStaff.getSalary());
            preparedStatement.setString(8, managerialStaff.getStatus());
            preparedStatement.setString(9, managerialStaff.getDepartment());
            preparedStatement.setString(10, managerialStaff.getJobTitle());
            preparedStatement.setString(11, managerialStaff.getWorkingHours());
            preparedStatement.setString(12, managerialStaff.getOfficeLocation());
            preparedStatement.setInt(13, managerialStaff.getTeamSize());
            preparedStatement.setString(14, managerialStaff.getReportsTo());
            preparedStatement.setInt(15, managerialStaff.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a ManagerialStaff record by ID
    public boolean deleteManagerialStaff(int id) {
        String query = "DELETE FROM managerialstaff WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to map a ResultSet to a ManagerialStaff object
    private ManagerialStaff mapResultSetToManagerialStaff(ResultSet resultSet) throws SQLException {
        ManagerialStaff managerialStaff = new ManagerialStaff();
        managerialStaff.setId(resultSet.getInt("id"));
        managerialStaff.setFirstName(resultSet.getString("firstName"));
        managerialStaff.setLastName(resultSet.getString("lastName"));
        managerialStaff.setEmail(resultSet.getString("email"));
        managerialStaff.setPhoneNumber(resultSet.getString("phoneNumber"));
        managerialStaff.setAddress(resultSet.getString("address"));
        managerialStaff.setHireDate(resultSet.getDate("hireDate")); // Direct from ResultSet
        managerialStaff.setSalary(resultSet.getBigDecimal("salary"));
        managerialStaff.setStatus(resultSet.getString("status"));
        managerialStaff.setDepartment(resultSet.getString("department"));
        managerialStaff.setJobTitle(resultSet.getString("jobTitle"));
        managerialStaff.setWorkingHours(resultSet.getString("workingHours"));
        managerialStaff.setOfficeLocation(resultSet.getString("officeLocation"));
        managerialStaff.setTeamSize(resultSet.getInt("teamSize"));
        managerialStaff.setReportsTo(resultSet.getString("reportsTo"));
        return managerialStaff;
    }
}
