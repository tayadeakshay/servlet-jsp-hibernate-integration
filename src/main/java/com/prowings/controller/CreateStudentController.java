package com.prowings.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prowings.model.Student;
import com.prowings.service.StudentService;
import com.prowings.service.StudentServiceImpl;

public class CreateStudentController extends HttpServlet{

	StudentService service = new StudentServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	Student model = new Student();
	
	model.setRoll(Integer.parseInt(req.getParameter("roll")));
	model.setName(req.getParameter("name"));
	model.setAddress(req.getParameter("address"));
	
	PrintWriter out = resp.getWriter();
	
	if(service.createStudent(model))
	{
		out.println("Student record created successfully");
		
	}else {
	out.println("Unable to save record");
	}
	}
	}
	

