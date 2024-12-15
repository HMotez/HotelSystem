package Staff.ManagerialStaff;

import Utils.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerialStaffDAL {

    // Insert a new ManagerialStaff record
    public static boolean insertManagerialStaff(ManagerialStaff managerialStaff) {
        String insertQuery = "INSERT INTO ManagerialStaff (id, officeLocation, teamSize, reportsTo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            // Insert fields of ManagerialStaff class and Staff superclass
            stmt.setInt(1, managerialStaff.getId());
            stmt.setString(2, managerialStaff.getOfficeLocation());
            stmt.setInt(3, managerialStaff.getTeamSize());
            stmt.setString(4, managerialStaff.getReportsTo());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Return true if insertion is successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a ManagerialStaff record by ID
    public static ManagerialStaff getManagerialStaffById(int id) {
        String selectQuery = "SELECT * FROM ManagerialStaff WHERE id = ?";
        ManagerialStaff managerialStaff = null;

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                managerialStaff = new ManagerialStaff(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getDate("hire_date"),
                        rs.getBigDecimal("salary"),
                        rs.getString("status"),
                        rs.getString("department"),
                        rs.getString("job_title"),
                        rs.getString("working_hours"),
                        rs.getString("officeLocation"),
                        rs.getInt("teamSize"),
                        rs.getString("reportsTo")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managerialStaff;
    }

    // Retrieve all ManagerialStaff records
    public static List<ManagerialStaff> getAllManagerialStaff() {
        String selectQuery = "SELECT * FROM ManagerialStaff";
        List<ManagerialStaff> managerialStaffList = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ManagerialStaff managerialStaff = new ManagerialStaff(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getDate("hire_date"),
                        rs.getBigDecimal("salary"),
                        rs.getString("status"),
                        rs.getString("department"),
                        rs.getString("job_title"),
                        rs.getString("working_hours"),
                        rs.getString("officeLocation"),
                        rs.getInt("teamSize"),
                        rs.getString("reportsTo")
                );
                managerialStaffList.add(managerialStaff);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managerialStaffList;
    }

    // Update a ManagerialStaff record by ID
    public static boolean updateManagerialStaff(ManagerialStaff managerialStaff) {
        String updateQuery = "UPDATE ManagerialStaff SET officeLocation = ?, teamSize = ?, reportsTo = ? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, managerialStaff.getOfficeLocation());
            stmt.setInt(2, managerialStaff.getTeamSize());
            stmt.setString(3, managerialStaff.getReportsTo());
            stmt.setInt(4, managerialStaff.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Return true if update is successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a ManagerialStaff record by ID
    public static boolean deleteManagerialStaff(int id) {
        String deleteQuery = "DELETE FROM ManagerialStaff WHERE id = ?";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0; // Return true if deletion is successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
