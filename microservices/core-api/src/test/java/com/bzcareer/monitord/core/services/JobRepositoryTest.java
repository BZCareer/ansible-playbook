package com.bzcareer.monitord.core.services;

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
import com.bzcareer.monitord.core.model.JobDAO;

 
/**
 * Provided unit test to check that the data was inserted properly.
 * 
 * @author Zak.Hassan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MondCoreServicesApplication.class)
@WebAppConfiguration
public class JobRepositoryTest {

	@Autowired
	public JobService service;

	@Before
	public void bootStrap() {
		service.deleteAll(); //String name, String status, String start, String end, String type)
			
		service.create(new JobDAO("hadoop-1","success", "2014-10-20_20:12:00","2013-03-20_20:12:00" ,"server-provision"));
		service.create(new JobDAO("spark-3","success", "2013-12-20_20:42:00","2014-03-20_20:12:00" ,"server-provision"));
		service.create(new JobDAO("java-webapp","success", "2011-03-22_23:42:00","2013-12-20_20:12:00" ,"code-deploy"));
	}

	@Test
	public void nodeInsertedDataSuccessfully() {
		assertThat("User Data Access Code Inserted Data Successfully", service.findAll().size(), is(3));
	}

	@Test
	public void nodeNegativeScenarioTest() {
		assertThat("User Data Access Code Inserted Data Negative Scenario", service.findAll().size(), not(4));
	}

 	@Test
	public void checkingForWrongDataInserted() {
		JobDAO nodeDAO = service.findAll().get(0);
		nodeDAO.setStatus("failure");
		service.update(nodeDAO);
		assertThat("Wrong Valid Data Is Correct", service.findById(nodeDAO.getId()).getStatus(),
				is("failure"));
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