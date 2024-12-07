package com.klef.hibernateexam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Insert Operation
        insertDepartment(sessionFactory);

        // Delete Operation
        deleteDepartmentById(sessionFactory, 1);

        sessionFactory.close();
    }

    private static void insertDepartment(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Department department = new Department();
            department.setName("Computer Science");
            department.setLocation("Building A");
            department.setHodName("Dr. John Doe");

            session.save(department);
            transaction.commit();

            System.out.println("Department inserted successfully!");
        }
    }

    private static void deleteDepartmentById(SessionFactory sessionFactory, int deptId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("DELETE FROM Department WHERE departmentId = ?1");
            query.setParameter(1, deptId);

            int result = query.executeUpdate();
            transaction.commit();

            if (result > 0) {
                System.out.println("Department with ID " + deptId + " deleted successfully!");
            } else {
                System.out.println("No department found with ID " + deptId);
            }
        }
    }
}
