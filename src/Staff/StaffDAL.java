package Staff;

import Utils.DbConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffDAL {

    // Insert Staff record into the database
    public boolean addStaff(Staff staff) {
        String query = "INSERT INTO staff (id, firstName, lastName, email, phoneNumber, address, hireDate, salary, status, department, jobTitle, workingHours) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            // Prompt for and set the 'id' manually
            stmt.setInt(1, staff.getId());  // Set the ID from the user input
            stmt.setString(2, staff.getFirstName());
            stmt.setString(3, staff.getLastName());
            stmt.setString(4, staff.getEmail());
            stmt.setString(5, staff.getPhoneNumber());
            stmt.setString(6, staff.getAddress());
            stmt.setDate(7, new java.sql.Date(staff.getHireDate().getTime()));
            stmt.setBigDecimal(8, staff.getSalary());
            stmt.setString(9, staff.getStatus());
            stmt.setString(10, staff.getDepartment());
            stmt.setString(11, staff.getJobTitle());
            stmt.setString(12, staff.getWorkingHours());
    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    // Retrieve a Staff record by ID
    public Staff getStaffById(int id) {
        String query = "SELECT * FROM staff WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToStaff(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all Staff records
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                staffList.add(mapResultSetToStaff(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    // Update a Staff record
    public boolean updateStaff(Staff staff) {
        String query = "UPDATE staff SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, address = ?, hireDate = ?, salary = ?, status = ?, department = ?, jobTitle = ?, workingHours = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, staff.getFirstName());
            stmt.setString(2, staff.getLastName());
            stmt.setString(3, staff.getEmail());
            stmt.setString(4, staff.getPhoneNumber());
            stmt.setString(5, staff.getAddress());
            stmt.setDate(6, new java.sql.Date(staff.getHireDate().getTime()));
            stmt.setBigDecimal(7, staff.getSalary());
            stmt.setString(8, staff.getStatus());
            stmt.setString(9, staff.getDepartment());
            stmt.setString(10, staff.getJobTitle());
            stmt.setString(11, staff.getWorkingHours());
            stmt.setInt(12, staff.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a Staff record by ID
    public boolean deleteStaff(int id) {
        String query = "DELETE FROM staff WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to map a ResultSet row to a Staff object
    private Staff mapResultSetToStaff(ResultSet rs) throws SQLException {
        Staff staff = new Staff();
        staff.setId(rs.getInt("id"));
        staff.setFirstName(rs.getString("firstName"));
        staff.setLastName(rs.getString("lastName"));
        staff.setEmail(rs.getString("email"));
        staff.setPhoneNumber(rs.getString("phoneNumber"));
        staff.setAddress(rs.getString("address"));
        staff.setHireDate(rs.getDate("hireDate"));
        staff.setSalary(rs.getBigDecimal("salary"));
        staff.setStatus(rs.getString("status"));
        staff.setDepartment(rs.getString("department"));
        staff.setJobTitle(rs.getString("jobTitle"));
        staff.setWorkingHours(rs.getString("workingHours"));
        return staff;
    }
}
