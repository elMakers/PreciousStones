package net.sacredlabyrinth.Phaed.PreciousStones.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

abstract class AbstractDBCore implements DBCore {
    
    private final VendorType vendorType;
    
    AbstractDBCore(VendorType vendorType) {
        this.vendorType = vendorType;
    }
    
    abstract HikariDataSource getDataSource();
    
    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    
    @Override
    public VendorType getVendorType() {
        return vendorType;
    }
    
    @Override
    public void close() {
        getDataSource().close();
    }

    @Override
    public boolean tableExists(Connection conn, String table) throws SQLException {
        try (ResultSet tables = conn.getMetaData().getTables(null, null, table, null)) {

            return tables.next();
        }
    }

    @Override
    public boolean columnExists(Connection conn, String table, String column) throws SQLException {
        try (ResultSet columns = conn.getMetaData().getColumns(null, null, table, column)) {

            return columns.next();
        }
    }
    
}
