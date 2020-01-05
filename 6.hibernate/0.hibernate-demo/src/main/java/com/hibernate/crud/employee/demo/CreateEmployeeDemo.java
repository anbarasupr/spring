package com.hibernate.crud.employee.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.crud.entity.Employee;
import com.hibernate.crud.entity.Student;

public class CreateEmployeeDemo {

	public static void main(String[] args) {

		// create the session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		try {
			// save(factory);
			saveOrUpdate(factory);

		} finally {
			// close the session factory
			factory.close();
		}

	}
	
	public static void save(SessionFactory factory) { // do insert
		// create session
		Session session = factory.getCurrentSession();

		// create employee
		Employee emp = new Employee("anbarasu", "priya", "BNY Mellon");

		// start a transaction
		session.beginTransaction();

		// persist employee object
		session.save(emp);

		// commit the transaction
		session.getTransaction().commit();
	}
	
	public static void saveOrUpdate(SessionFactory factory) { // check if employee id is already in table, if it is do insert else full update
		// create session
		Session session = factory.getCurrentSession();

		// create employee
		Employee emp = new Employee("anbarasu", "priya", "BNY Mellon");
		// emp.setId(1);
		// start a transaction
		session.beginTransaction();

		// persist employee object
		session.saveOrUpdate(emp);

		// commit the transaction
		session.getTransaction().commit();
	}

}
