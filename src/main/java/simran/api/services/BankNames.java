package simran.api.services;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import simran.api.databaseUtilities.DatabaseCredentials;
import simran.api.databaseUtilities.DatabaseUtils;
import simran.api.models.BranchDetails;

public class BankNames {
	
//	private String username = "wkuyhxlvdfawll";
//	private String password = "qYvEYY9B1UF5r-uqTP8paybs7_";
//	private String dbUrl = "jdbc:postgresql://ec2-54-83-22-233.compute-1.amazonaws.com:5432/dakhtvons23h2u";
	
	private String username = "postgres";
	private String password = "docomo92";
	private String dbUrl = "jdbc:postgresql://localhost:5432/simran";
	private List<String> bankNames= new ArrayList<String>();
	private List<String> citiesBankIsIN = new ArrayList<String>();
	private List<BranchDetails> branchdetails = new ArrayList<BranchDetails>();
	private String dbDriver = "org.postgresql.Driver";
	
	DatabaseCredentials commonDBObject = DatabaseCredentials.getInstance(username, password, dbUrl);
	
	public List<String> getBankNames(){
		System.out.println("entered method");
		try{
		
		 Connection c = null;
		 Statement stmt = null;
		 c =DatabaseUtils.createConnection(dbDriver, commonDBObject.getDbUrl(), commonDBObject.getUsername(), commonDBObject.getPassword());  
		 System.out.println("conected");
		 stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT name FROM BANKS;" );
         System.out.println("rs got");
         while ( rs.next() ) {
         String  name = rs.getString("name");
         bankNames.add(name);
         }
         DatabaseUtils.closeRS(rs);
         DatabaseUtils.closeST(stmt);
         DatabaseUtils.closeCN(c);
         
		 }catch ( Exception e ) {
		         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		         System.exit(0);
		       }
		System.out.println("returning bank names"+ bankNames.size());
		 return bankNames;
	}
	
	public List<String> getCitiesBankIsIn(String bankName){
	
		try{
			Connection c = null;
			 PreparedStatement  stmt = null;
			 c =DatabaseUtils.createConnection(dbDriver, commonDBObject.getDbUrl(), commonDBObject.getUsername(), commonDBObject.getPassword());  
			 String sql1 = "select id from BANKS where name = ?;";
	
			 stmt = c.prepareStatement(sql1);
			 stmt.setString(1, bankName);
	         ResultSet rs = stmt.executeQuery();
	         int bankId = 0;
	         while ( rs.next() ) {
	         bankId = rs.getInt("id");
	         }
	         DatabaseUtils.closeRS(rs);
	         DatabaseUtils.closeST(stmt);
	         
	         String sql2 = "select distinct(city) from branches where bank_id= ? order by city;";
	         stmt = c.prepareStatement(sql2);
	         stmt.setInt(1, bankId);
	         rs= stmt.executeQuery();
	         
	         
	         while ( rs.next() ) {
		        String bankCity = rs.getString("city");
		        citiesBankIsIN.add(bankCity);
		         }
	         DatabaseUtils.closeRS(rs);
	         DatabaseUtils.closeST(stmt);
	         DatabaseUtils.closeCN(c);
	         
			 }catch ( Exception e ) {
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.exit(0);
			       }
		return citiesBankIsIN;
		
	}	
	
	public List<BranchDetails> getBranchDetails(String bank, String city){
		
		try{
			
			 Connection c = null;
			 PreparedStatement  stmt = null;
			 c =DatabaseUtils.createConnection(dbDriver, commonDBObject.getDbUrl(), commonDBObject.getUsername(), commonDBObject.getPassword());    
			 String sql = "select * from bank_branches where bank_name = ? and city = ?";
	
			 stmt = c.prepareStatement(sql);
			 stmt.setString(1, bank);
			 stmt.setString(2, city);
	         ResultSet rs = stmt.executeQuery();
	        
	         
	         while ( rs.next() ) {
	        BranchDetails branchdet = new BranchDetails(null,null,null,null);
	        branchdet.setIfsc(rs.getString("ifsc"));
	        branchdet.setCity(rs.getString("city"));
	        branchdet.setBranch_address(rs.getString("address"));
	        branchdet.setBank(rs.getString("bank_name"));
	        branchdetails.add(branchdet);
	         }
	         DatabaseUtils.closeRS(rs);
	         DatabaseUtils.closeST(stmt);
	         DatabaseUtils.closeCN(c);
	         
			 }catch ( Exception e ) {
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.exit(0);
			       }
		return branchdetails;
		
	}
}


