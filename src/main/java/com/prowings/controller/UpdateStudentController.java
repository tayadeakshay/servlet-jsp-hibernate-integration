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

public class UpdateStudentController extends HttpServlet {

	StudentService service = new StudentServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Student std = new Student();
		std.setRoll(Integer.parseInt(req.getParameter("roll")));
		std.setName(req.getParameter("name"));
		std.setAddress(req.getParameter("address"));
		
		PrintWriter out = resp.getWriter();
		if(service.updateStudent(std.getRoll(), std))
		{
			out.println("Student record updated successfully as : " +"Id-"+ std.getId() +" Roll-"+ std.getRoll() +" Name-"+ std.getName()+" Address-"+ std.getAddress());
		}else {
		out.println("Unable to update details");
		}
		
		}
	}
	
