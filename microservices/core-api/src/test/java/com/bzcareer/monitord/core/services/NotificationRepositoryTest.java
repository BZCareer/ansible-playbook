package com.bzcareer.monitord.core.services;

//import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bzcareer.monitord.core.config.MondCoreServicesApplication;
import com.bzcareer.monitord.core.model.NotificationDAO;

/**
 * Provided unit test to check that the data was inserted properly.
 * 
 * @author Zak.Hassan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MondCoreServicesApplication.class)
@WebAppConfiguration
public class NotificationRepositoryTest {

	@Autowired
	public NotificationService service;

	@Before
	public void bootStrap() {
		service.deleteAll(); //String notification_type, String from, String message
		service.create(new NotificationDAO("SMS", "johnsmith", "Service Outage"));
		service.create(new NotificationDAO("Email", "jimmypickel", "Out of Memory"));
		service.create(new NotificationDAO("Voicemail","henrysimmons", "Out Of CPU"));
	}

	@Test
	public void NotificationInsertedDataSuccessfully() {
		assertThat("User Data Access Code Inserted Data Successfully", service.findAll().size(), is(3));
	}

	@Test
	public void NotificationNegativeScenarioTest() {
		assertThat("User Data Access Code Inserted Data Negative Scenario", service.findAll().size(), not(4));
	}

 	@Test
	public void checkingForWrongDataInserted() {
 		NotificationDAO NotificationDAO = service.findAll().get(0);
 		NotificationDAO.setNotification_type("Hipchat"); 
		service.update(NotificationDAO);
		assertThat("Wrong Valid Data Is Correct", service.findById(NotificationDAO.getId()).getNotification_type(),
				is("Hipchat"));
	}

	@Test
	public void checkDeleteOperation() {
		service.delete(service.findAll().get(0));
		assertThat("User Data Access Code Inserted Data Successfully Deleted One", service.findAll().size(),
				is(2));
	}

	@Test
	public void checkDeleteAll() {
		service.deleteAll();
		assertThat("User Data Access Code Inserted Data Successfully Deleted All", service.findAll().size(),
				is(0));
	}
 

}
