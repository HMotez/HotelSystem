package Staff.Technical;

import Utils.DbConnection;
import java.sql.*;
import java.util.*;

public class TechnicalDAL {

    // Insert a new Technical record
    public static boolean insertTechnical(Technical technical) {
        String insertQuery = "INSERT INTO Technical (id, technicalSkills, certifications, workLocation, lastTrainingDate) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            // Insert Technical-specific fields
            stmt.setInt(1, technical.getId());
            stmt.setString(2, technical.getTechnicalSkills());
            stmt.setString(3, technical.getCertifications());
            stmt.setString(4, technical.getWorkLocation());
            stmt.setDate(5, new java.sql.Date(technical.getLastTrainingDate().getTime()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;  // Return true if insertion is successful, otherwise false

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a Technical record by ID
    public static Technical getTechnicalById(int id) {
        // Use explicit JOIN syntax
        String selectQuery = "SELECT s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.address, s.hireDate, s.salary, s.status, s.department, s.jobTitle, s.workingHours, " +
                             "t.technicalSkills, t.certifications, t.workLocation, t.lastTrainingDate " +
                             "FROM Staff s " +
                             "JOIN Technical t ON s.id = t.id " +
                             "WHERE t.id = ?";
    
        Technical technical = null;
    
        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
            // Set the ID parameter in the prepared statement
            stmt.setInt(1, id);
    
            // Execute the query
            ResultSet rs = stmt.executeQuery();
    
            // Check if a result is found
            if (rs.next()) {
                technical = new Technical(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getDate("hireDate"),
                        rs.getBigDecimal("salary"),
                        rs.getString("status"),
                        rs.getString("department"),
                        rs.getString("jobTitle"),
                        rs.getString("workingHours"),
                        rs.getString("technicalSkills"),
                        rs.getString("certifications"),
                        rs.getString("workLocation"),
                        rs.getDate("lastTrainingDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return technical;
    }
    
    


    // Get all Technical records
    public static List<Technical> getAllTechnicals() {
        String selectQuery = "SELECT s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.address, " +
                             "s.hireDate, s.salary, s.status, s.department, s.jobTitle, s.workingHours, " +
                             "t.technicalSkills, t.certifications, t.workLocation, t.lastTrainingDate " +
                             "FROM Staff s " +
                             "JOIN Technical t ON s.id = t.id";
        List<Technical> technicalList = new ArrayList<>();
    
        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
    
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Technical technical = new Technical(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getDate("hireDate"),
                        rs.getBigDecimal("salary"),
                        rs.getString("status"),
                        rs.getString("department"),
                        rs.getString("jobTitle"),
                        rs.getString("workingHours"),
                        rs.getString("technicalSkills"),
                        rs.getString("certifications"),
                        rs.getString("workLocation"),
                        rs.getDate("lastTrainingDate")
                );
                technicalList.add(technical);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return technicalList;
    }
    

    // Update a Technical record by ID
    public static boolean updateTechnical(Technical technical) {
        String updateQuery = "UPDATE Technical SET technicalSkills = ?, certifications = ?, workLocation = ?, lastTrainingDate = ? "
                + "WHERE id = ?";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, technical.getTechnicalSkills());
            stmt.setString(2, technical.getCertifications());
            stmt.setString(3, technical.getWorkLocation());
            stmt.setDate(4, new java.sql.Date(technical.getLastTrainingDate().getTime()));
            stmt.setInt(5, technical.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Return true if update is successful, otherwise false

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a Technical record by ID
    public static boolean deleteTechnical(int id) {
        String deleteQuery = "DELETE FROM Technical WHERE id = ?";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;  // Return true if deletion is successful, otherwise false

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
