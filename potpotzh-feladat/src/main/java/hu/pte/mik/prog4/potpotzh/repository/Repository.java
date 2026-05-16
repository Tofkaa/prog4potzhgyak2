package hu.pte.mik.prog4.potpotzh.repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Repository {

    private static final String JNDI_DATASOURCE_NAME = "jdbc/pot-potZH-MariaDB";
    private static final String ROOT_CONTEXT = "java:comp/env";

    private static DataSource dataSource;

    protected Connection getConnection() throws NamingException, SQLException {
        return getDataSource().getConnection();
    }

    private static DataSource getDataSource() throws NamingException {
        if(dataSource == null) {
            Context initCtx = new InitialContext();
            Context ctx = (Context) initCtx.lookup(ROOT_CONTEXT);
            dataSource = (DataSource) ctx.lookup(JNDI_DATASOURCE_NAME);
        }
        return dataSource;
    }

}
