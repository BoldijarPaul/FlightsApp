package Repository;

import Domain.Flight;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.mapped.MappedPreparedStmt;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class FlightRepository {
    private Dao<Flight, Integer> accountDao;
    Connection connection;

    public FlightRepository(Connection connection) {
        this.connection = connection;

        try {
            String databaseUrl = "jdbc:sqlite:" + "mpp.db";
            // create a connection source to our database
            ConnectionSource connectionSource =
                    new JdbcConnectionSource(databaseUrl);
            accountDao =
                    DaoManager.createDao(connectionSource, Flight.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> getAll() throws SQLException {
        return accountDao.queryForAll();
    }

    public void buyTickets(String destination, int tickets) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update flights set seats=seats-? where destination=? and seats>=?");
        preparedStatement.setInt(1, tickets);
        preparedStatement.setString(2, destination);
        preparedStatement.setInt(3, tickets);
        preparedStatement.execute();
    }

    public Flight getFlight(String destination) throws SQLException {
        List<Flight> query = accountDao.queryBuilder().where().eq("destination", destination).query();
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    public Flight getFlight(int id) throws SQLException {
        return accountDao.queryForId(id);
    }
}
