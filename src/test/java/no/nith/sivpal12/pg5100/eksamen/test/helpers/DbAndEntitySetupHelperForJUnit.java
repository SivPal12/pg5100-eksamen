package no.nith.sivpal12.pg5100.eksamen.test.helpers;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.h2.tools.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class DbAndEntitySetupHelperForJUnit {
    private static Server server;

    private EntityManagerFactory factory;
    protected EntityManager entityManager;

    @BeforeClass
    public static void startDb() throws SQLException {
        server = Server.createTcpServer("-tcpAllowOthers");
        server.start();
    }

    @AfterClass
    public static void showdownDb() {
        server.shutdown();
    }

    @Before
    public void setUp() throws ClassNotFoundException {
        factory = Persistence.createEntityManagerFactory("myTestManager");
         entityManager = factory.createEntityManager();
    }

    @After
    public void tearDown() {
         entityManager.close();
         factory.close();
    }
}
