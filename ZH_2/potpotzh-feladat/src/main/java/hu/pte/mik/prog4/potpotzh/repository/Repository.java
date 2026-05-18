package hu.pte.mik.prog4.potpotzh.repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class Repository {
    protected Connection getConnection() throws Exception {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        // A PDF-ben megadott pontos Resource név
        DataSource ds = (DataSource) envContext.lookup("jdbc/pot-potZH-MariaDB");
        return ds.getConnection();
    }
}