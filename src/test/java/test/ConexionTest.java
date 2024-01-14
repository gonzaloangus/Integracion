package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.Conexion;

public class ConexionTest {

	@Test
	public void testGetConnection() {
		Conexion conexion = new Conexion();
        assertNotNull(conexion.getConnection());
	}

}
