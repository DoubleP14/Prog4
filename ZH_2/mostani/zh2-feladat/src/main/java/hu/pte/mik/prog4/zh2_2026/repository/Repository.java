package hu.pte.mik.prog4.zh2_2026.repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Repository {

    private static DataSource dataSource;

    protected Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Nem sikerült kapcsolódni az adatbázishoz!", e);
        }
    }

    private static DataSource getDataSource() {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                // Itt pontosan a context.xml-ben lévő nevet kell megadni!
                dataSource = (DataSource) envContext.lookup("jdbc/ZH2_2026_MariaDB");
            } catch (Exception e) {
                throw new RuntimeException("Nem található a DataSource!", e);
            }
        }
        return dataSource;
    }
}