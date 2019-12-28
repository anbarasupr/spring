package com.hibernate.crud.employee.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.crud.entity.Employee;
import com.hibernate.crud.entity.Student;

public class UpdateEmployeeDemo {

	public static void main(String[] args) {

		// create the session factory
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		try {
			Employee emp = null;
			Session session = null;
			// case 1: 2 inserts happen - different session (It will not check if employee already exist in table or not, it always do insert)
			// emp = save(factory, null);
			// emp = save(factory, emp);

			// case 2: 1 insert & 1 update happen - different session (check if employee already exist in table, it exist do update else insert)
			// emp = saveOrUpdate(factory, null);
			// emp = saveOrUpdate(factory, emp);

			// case 3: 2 inserts happen - same session not possible without commiting test
			// with ehcahche
			/*
			 * session=factory.getCurrentSession(); session.beginTransaction(); emp = new
			 * Employee("anbu", "same", "BNY Mellon"); session.save(emp); emp =
			 * save(factory, emp); session.getTransaction().commit();
			 */

//			// case 4:   partial update happen - same session
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			// get employee
//			int id=1;
//			System.out.println("retieving employee with id:" +id);
//			emp = session.get(Employee.class, id);
//			System.out.println("retieved employee:" + emp);
//
//			emp.setLastName("wow");
//			System.out.println("employee lastname modified:" + emp);
//			session.getTransaction().commit();

//			// case 5: Error: Employee was altered from 1 to 0
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			// get employee
//			int id = 1;
//			System.out.println("retieving employee with id:" + id);
//			emp = session.get(Employee.class, id);
//			System.out.println("retieved employee:" + emp);
//
//			// emp.setId(0); // partial update happen if commented
//			emp.setLastName("new");
//			
//			System.out.println("employee lastname modified:" + emp);
//			session.getTransaction().commit();
			
			//case 6: update via query
			updateQuery(factory);

		} finally {
			// close the session factory
			factory.close();
		}

	}

	
	public static void updateQuery(SessionFactory factory) {
		// create session
		Session session = factory.getCurrentSession();

		// start a transaction
		session.beginTransaction();

		session.createQuery("update Employee set company='None'")
		.executeUpdate();

		// commit the transaction
		session.getTransaction().commit();

	}

	
	public static Employee save(SessionFactory factory, Employee emp) {
		// create session
		Session session = factory.getCurrentSession();
		// create employee
		if (emp == null)
			emp = new Employee("anbu", "separate", "BNY Mellon");

		// start a transaction
		session.beginTransaction();

		// persist employee object
		session.save(emp);
		System.out.println("Employee saved emp:" + emp);

		// commit the transaction
		session.getTransaction().commit();

		return emp;
	}

	public static Employee saveOrUpdate(SessionFactory factory, Employee emp) {
		// create session
		Session session = factory.getCurrentSession();
		// create employee
		if (emp == null)
			emp = new Employee("anbu", "default", "BNY Mellon");

		// start a transaction
		session.beginTransaction();

		// persist employee object
		session.saveOrUpdate(emp);
		System.out.println("Employee saved emp:" + emp);

		// commit the transaction
		session.getTransaction().commit();

		return emp;
	}
}
