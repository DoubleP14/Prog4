package hu.pte.mik.prog4.potzh_2026.repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public abstract class Repository {
    protected Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/PotZH_pet_MariaDB");
        return ds.getConnection();
    }
}