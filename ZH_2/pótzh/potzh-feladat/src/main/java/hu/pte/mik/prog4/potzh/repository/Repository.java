package hu.pte.mik.prog4.potzh.repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Repository {

    // EZ A NÉV PONTOSAN EGYEZIK A context.xml-BEN LÉVŐVEL!
    private static final String JNDI_DATASOURCE_NAME = "jdbc/potZH-MariaDB";
    private static final String ROOT_CONTEXT = "java:comp/env";

    private static DataSource dataSource;

    // ITT JAVÍTOTTUK A KORÁBBI HIBÁT: A getDataSource() metódust hívjuk meg!
    protected Connection getConnection() throws NamingException, SQLException {
        return getDataSource().getConnection();
    }

    private static DataSource getDataSource() throws NamingException {
        if (dataSource == null) {
            Context context = new InitialContext();
            Context ctx = (Context) context.lookup(ROOT_CONTEXT);
            dataSource = (DataSource) ctx.lookup(JNDI_DATASOURCE_NAME);
        }
        return dataSource;
    }
}