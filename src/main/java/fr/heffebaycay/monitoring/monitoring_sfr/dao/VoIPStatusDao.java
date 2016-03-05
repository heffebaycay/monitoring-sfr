package fr.heffebaycay.monitoring.monitoring_sfr.dao;

import fr.heffebaycay.monitoring.monitoring_sfr.model.VoIPStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VoIPStatusDao {

    private static final Logger logger = LoggerFactory.getLogger(VoIPStatusDao.class);

    public int save(VoIPStatus status, Connection conn) {
        logger.debug("Saving VoIPStatus record to the database");
        if (status == null) {
            throw new IllegalArgumentException("'status' parameter cannot be null");
        }

        String query = "INSERT INTO" +
                "  voip_status(status, infra, hook_status, callhistory_active, date_recorded)" +
                "  VALUES(?, ?, ?, ?, ?)";

        int nb = -1;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, status.getStatus());
            ps.setString(2, status.getInfra());
            ps.setString(3, status.getHookStatus());
            ps.setString(4, status.getCallHistoryActive());
            ps.setString(5, status.getDateRecorded());

            nb = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to save VoIPStatus element to the database: {}", e);
        }

        return nb;
    }

}
