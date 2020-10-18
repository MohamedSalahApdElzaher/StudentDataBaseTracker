package com.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Create out Connection poll
	@Resource(name="jdbc/web_student_tracker" )
	private DataSource dataSource;

	private StudentDbUtil db;
	
	// initialize 
   
	@Override
	public void init() throws ServletException {
		super.init();
		db = new StudentDbUtil(dataSource);
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// read the command come from JSP files
		String comand = request.getParameter("comand");
		
		if(comand == null)comand="LIST"; // if no command sent
		
		switch (comand) {
			case "LIST":
			try {
				ListStudents(request , response);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				break;
	
			case "ADD":
			try {
				AddStudent(request,response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				break;
				
			case "LOAD":
			try {
				LoadStudent(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
				
			case "UPDATE":
				try {
					UpdateStudent(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
					
			case "DELETE":
				try {
					DeleteStudent(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
				
			default:
			try {
				ListStudents(request , response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			}
		
	
	}



	private void DeleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student id
		String id = request.getParameter("studentId");
		// delete student from database based on id
		db.delete(id);
		// update JSP view
		ListStudents(request , response);
	}



	private void UpdateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read the request
				String fname = request.getParameter("firstname");
				String lname = request.getParameter("lastname");
				String email = request.getParameter("email");
				String id = request.getParameter("studentID");
				int i_D = Integer.parseInt(id);
				
				// insert into database
				db.updateStudent(i_D,fname , lname , email);
				// update JSP view
				ListStudents(request , response);
	}



	private void LoadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		// read studentId
		String id = request.getParameter("studentId");
		
		// get student from database depend on id
		Student student = db.getStudentById(id);
		
		// place a student in a request attribute
		request.setAttribute("THE_STUDENT", student);
		
		// send a request to JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}



	private void AddStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read the request
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		// insert into database
		db.insertStudent(fname , lname , email);
		// update JSP view
		ListStudents(request , response);

	}



	private void ListStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get student from database util
		List<Student> list = db.getStudents();
		
		// add student to request
		request.setAttribute("STUDENT_LIST", list);
		
		// send to JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/student_list.jsp");
		dispatcher.forward(request, response);
	}


}
