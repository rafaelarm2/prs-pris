package test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import prs.data.ConnectionFactory;

public class ConnectionFactoryTest {
	Connection connect;

    @Before
    public void before() throws SQLException {
    	connect = ConnectionFactory.getConnection();
    }

    @After
    public void after() throws SQLException {
    	ConnectionFactory.closeConnection(connect);
    }
    
    @Test
    public void testConnection() throws SQLException {
        Assert.assertNotNull(connect);
        Assert.assertTrue(connect.isValid(0));
    }
    


}
