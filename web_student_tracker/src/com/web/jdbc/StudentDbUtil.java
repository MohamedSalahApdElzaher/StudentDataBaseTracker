package com.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.sql.*;

public class StudentDbUtil {

	// define our collection pool
	private DataSource dataSource;
	private Connection con;
	private Statement st;
	private ResultSet res;
	
	// make constructor
 	public StudentDbUtil(DataSource data) {
		dataSource=data;
	}
 	
 	// method connect to data Base
 	public void CoonectDb() throws Exception {
 		con = dataSource.getConnection();
 		st = con.createStatement();
 	}
 

	// Method to List Of Students
	public List <Student> getStudents() throws Exception{
		
		// step 1: create Empty List
		List<Student> studentList = new ArrayList<>();
		
		try {

			// connect to db
			CoonectDb();

			// Execute sql query ordered by their last name
				String query = "SELECT * FROM student ORDER BY last_name";
				
			// process ResultSet
				res = st.executeQuery(query);
				while(res.next()) {
                   studentList.add(new Student(res.getInt("id") , 
                		   res.getString("first_name") , res.getString("last_name") ,
                		   res.getString("email")));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			closeConnection(con , st , res);
		}
		
		return studentList;		

	}

	private void closeConnection(Connection con2, Statement st2, ResultSet res2) {
		
		try {
			  if(con2 != null)con2.close();
			  if(st2 != null)st2.close();
			  if(res2 != null)res2.close();
		}catch(Exception  e) {
			e.printStackTrace();
		}
		
	}

	// Method to add new student

	public void insertStudent(String fname, String lname, String email) {
	    try {
	    	     // connect to db
	    		CoonectDb();
		      String query = "INSERT INTO student (first_name,last_name,email) VALUES ( '"+fname+"' , '"+lname+"' , '"+email+"') ";
		      st.execute(query);
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    finally {
	    	try {
	    		if(con != null && st!=null) {
	    			con.close();
					st.close();	
	    		}
	    	}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
	}


	public Student getStudentById(String id) throws Exception {
		Student student = null;
		Integer studentId = Integer.parseInt(id);
		// connect to db
		CoonectDb();
		String query = "SELECT first_name, last_name, email FROM student WHERE id='"+studentId+"'";
		ResultSet res = st.executeQuery(query);
		while(res.next()) {
			String fname = res.getString("first_name");
			String lname = res.getString("last_name");
			String email = res.getString("email");
			
			student = new Student(studentId, fname, lname, email);
		}
		closeConnection(con, st, res);

		return student;
	}

	public void updateStudent(int id , String fname, String lname, String email) throws Exception {
		CoonectDb();
		String query = 
		"UPDATE student SET first_name='"+fname+"',last_name='"+lname+"',email='"+email+"' WHERE id='"+id+"'";
		st.executeUpdate(query);
		st.close();
		con.close();
	}

	public void delete(String id) throws Exception {
		int ID = Integer.parseInt(id);
		CoonectDb();
		String query = "DELETE FROM student WHERE id='"+ID+"'";
		st.execute(query);
		con.close();
		st.close();
	}

}
