package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public List<Customer> getCustomer() {
		
		//get a hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create query
		Query<Customer> theQuery =
				     currentSession.createQuery("from Customer order by lastName",Customer.class);
		
		//execute query and get results
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCustomer);
		
	}


	@Override
	public Customer getCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class,theId);
		return theCustomer;
	}


	@Override
	public void deleteCustomer(Customer theCustomer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(theCustomer);
		
	}

}
