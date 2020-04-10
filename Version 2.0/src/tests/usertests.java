package tests;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import hospital_gui.SQLQUERIES;

public class usertests {
	
	private SQLQUERIES commands = new SQLQUERIES();
	
	@Test
	public void testPatientAccount() {
		String username="testy";
		String password="McTesterson";
		String role="patient";
		try{
			Connection con = commands.getConnection();
			PreparedStatement enterinfo = con.prepareStatement("INSERT INTO allaccounts (username, password, role) VALUES ('"+username+"','"+password+"','"+role+"')");
			enterinfo.executeUpdate();
		}
		catch(Exception e){	System.out.println(e);}
		assertEquals("patient", commands.getrole(username, password));
	}
	
	@Test
	public void deletePatientAccount() {
		String username="testy";
		String password="McTesterson";
		try {
			Connection con = commands.getConnection();
			String query = String.format("DELETE FROM allaccounts WHERE username = \"%s\" AND password = \"%s\"", username, password);
			PreparedStatement remove = con.prepareStatement(query);
			remove.executeUpdate();
		} catch (Exception e){
			System.out.println(e);
		}
		assertEquals(false, commands.checkaccount(username,password));
	}
}