package com.prowings.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.prowings.entities.StudentEntity;

public class StudentDaoImpl implements StudentDao{

	SessionFactory factory = HibernateUtil.getSessionFactory();
	
	@Override
	public boolean createStudent(StudentEntity std) {
		
		Session session = null;
		try {
			session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(std);
		
		tx.commit();
		session.close();
		System.out.println("Student record created successfully");
		return true;
		}catch(Exception ex){
			System.out.println("Unable to store student record in DB");
			ex.printStackTrace();
			return false;
		}
		finally{
			session.close();
		}
		
	}

	@Override
	public StudentEntity getStudentByRoll(int roll) {
		
		StudentEntity entity;
		Session session = null;
		String query = "From StudentEntity student where student.roll = :roll";
		try {
			session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query q=session.createQuery(query);
		q.setParameter("roll", roll);
		entity = (StudentEntity)q.uniqueResult();
		
		tx.commit();
		System.out.println("Student record fetched successfully");
		return entity;
		}catch(Exception ex){
			System.out.println("Unable to fetch student record from DB");
			ex.printStackTrace();
			return null;
		}
		finally{
			session.close();
		}
	}

	@Override
	public List<StudentEntity> getAllStudents() {
		List<StudentEntity> entity;
		Session session = null;
		String query = "From StudentEntity student";
		try {
			session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query q=session.createQuery(query);
		entity = q.list();
		
		tx.commit();
		System.out.println("All Student records fetched successfully");
		return entity;
		}catch(Exception ex){
			System.out.println("Unable to fetch student records from DB");
			ex.printStackTrace();
			return null;
		}
		finally{
			session.close();
		}
	}

	@Override
	public boolean updateStudent(int roll, StudentEntity std) {

		
		Session session = null;
		String query = "update StudentEntity s set s.name= :name,s.address = :address where s.roll = :roll";
		try {
			session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			Query q=session.createQuery(query);
			q.setParameter("roll", roll);
			q.setParameter("name", std.getName());
			q.setParameter("address", std.getAddress());
			q.executeUpdate();
			
			tx.commit();
			session.close();
			System.out.println("Student record updated successfully");
			return true;
			}catch(Exception ex){
				System.out.println("Unable to update student record in DB");
				ex.printStackTrace();
				return false;
			}
			finally{
				session.close();
			}
	}

	@Override
	public boolean deleteStudent(int roll) {

		Session session = null;
		String query = "delete from StudentEntity student where student.roll = :roll";
		try {
			session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query q=session.createQuery(query);
		q.setParameter("roll", roll);
		q.executeUpdate();
		
		tx.commit();
		System.out.println("Student record deleted successfully");
		return true;
		}catch(Exception ex){
			System.out.println("Unable to delete student record from DB");
			ex.printStackTrace();
			return false;
		}
		finally{
			session.close();
		}
	}

	
}
