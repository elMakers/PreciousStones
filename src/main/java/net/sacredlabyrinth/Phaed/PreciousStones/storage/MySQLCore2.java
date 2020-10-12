package net.sacredlabyrinth.Phaed.PreciousStones.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

class MySQLCore2 extends AbstractDBCore {

    private final HikariDataSource dataSource;
    
    /**
     * Creates from the given credentials
     * 
     * @param host database host
     * @param database database name
     * @param username username
     * @param password password
     */
    MySQLCore2(String host, int port, String database, String username, String password) {
        super(VendorType.MYSQL);
        HikariConfig hikariConf = new HikariConfig();
        hikariConf.setJdbcUrl("jdbc:mysql://" + host + ':' + port + '/' + database + "?useUnicode=true&characterEncoding=utf-8");
        hikariConf.setUsername(username);
        hikariConf.setPassword(password);
        hikariConf.setMinimumIdle(3);
        hikariConf.setMaximumPoolSize(3);
        dataSource = new HikariDataSource(hikariConf);
    }

    @Override
    HikariDataSource getDataSource() {
        return dataSource;
    }
    
    @Override
    public boolean supportsGetDataType() {
        return true;
    }
    
    @Override
    public String getDataType(Connection conn, String table, String column) throws SQLException {
        String dataType = "";
        try (PreparedStatement prepStmt = conn.prepareStatement(
                "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?")) {
            prepStmt.setString(1, table);
            prepStmt.setString(2, column);
            try (ResultSet resultSet = prepStmt.executeQuery()) {
                while (resultSet.next()) {
                    dataType = resultSet.getString("DATA_TYPE");
                }
            }
        }
        return (dataType == null) ? "" : dataType;
    }
    
}
