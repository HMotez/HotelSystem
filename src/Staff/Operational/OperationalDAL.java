package Staff.Operational;

import Utils.DbConnection;
import java.sql.*;
import java.util.*;

public class OperationalDAL {

    // Insert a new Operational record
    public static boolean insertOperational(Operational operational) {
        String insertQuery = "INSERT INTO Operational (id, shiftType, location, responsibilityLevel, taskCount, performanceRating) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setInt(1, operational.getId());
            stmt.setString(2, operational.getShiftType());
            stmt.setString(3, operational.getLocation());
            stmt.setString(4, operational.getResponsibilityLevel());
            stmt.setInt(5, operational.getTaskCount());
            stmt.setBigDecimal(6, operational.getPerformanceRating());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get an Operational record by ID
    public static Operational getOperationalById(int id) {
        String selectQuery = "SELECT s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.address, s.hireDate, s.salary, s.status, s.department, s.jobTitle, s.workingHours, " +
                             "o.shiftType, o.location, o.responsibilityLevel, o.taskCount, o.performanceRating " +
                             "FROM Staff s " +
                             "JOIN Operational o ON s.id = o.id " +
                             "WHERE o.id = ?";

        Operational operational = null;

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                operational = new Operational(
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
                        rs.getString("shiftType"),
                        rs.getString("location"),
                        rs.getString("responsibilityLevel"),
                        rs.getInt("taskCount"),
                        rs.getBigDecimal("performanceRating")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operational;
    }

    // Get all Operational records
    public static List<Operational> getAllOperationals() {
        String selectQuery = "SELECT s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.address, " +
                             "s.hireDate, s.salary, s.status, s.department, s.jobTitle, s.workingHours, " +
                             "o.shiftType, o.location, o.responsibilityLevel, o.taskCount, o.performanceRating " +
                             "FROM Staff s " +
                             "JOIN Operational o ON s.id = o.id";

        List<Operational> operationalList = new ArrayList<>();

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Operational operational = new Operational(
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
                        rs.getString("shiftType"),
                        rs.getString("location"),
                        rs.getString("responsibilityLevel"),
                        rs.getInt("taskCount"),
                        rs.getBigDecimal("performanceRating")
                );
                operationalList.add(operational);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operationalList;
    }

    // Update an Operational record by ID
    public static boolean updateOperational(Operational operational) {
        String updateQuery = "UPDATE Operational SET shiftType = ?, location = ?, responsibilityLevel = ?, taskCount = ?, performanceRating = ? "
                + "WHERE id = ?";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, operational.getShiftType());
            stmt.setString(2, operational.getLocation());
            stmt.setString(3, operational.getResponsibilityLevel());
            stmt.setInt(4, operational.getTaskCount());
            stmt.setBigDecimal(5, operational.getPerformanceRating());
            stmt.setInt(6, operational.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an Operational record by ID
    public static boolean deleteOperational(int id) {
        String deleteQuery = "DELETE FROM Operational WHERE id = ?";

        try (Connection conn = DbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
