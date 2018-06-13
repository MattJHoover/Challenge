package com.mindex.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DataBootstrap {
    private static final String DATASTORE_LOCATION = "/static/employee_database.json";

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        InputStream inputStream = this.getClass().getResourceAsStream(DATASTORE_LOCATION);

        Employee[] employees = null;

        try {
            employees = objectMapper.readValue(inputStream, Employee[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Employee employee : employees) {
            employeeRepository.insert(employee);
        }
        
        //Create compensation data
        Compensation compensation1 = new Compensation();
		Employee employee1 = new Employee();
		employee1.setEmployeeId("b7839309-3348-463b-a7e3-5de1c168beb3");
		employee1.setFirstName("Paul");
		employee1.setLastName("McCartney");
		employee1.setPosition("Developer I");
		employee1.setDepartment("Engineering");
		compensation1.setEmployee(employee1);
		compensation1.setSalary(50000.00);
		compensation1.setEffectiveDate("6/13/2018");
		compensationRepository.insert(compensation1);
		Compensation compensation2 = new Compensation();
		Employee employee2 = new Employee();
		employee2.setEmployeeId("62c1084e-6e34-4630-93fd-9153afb65309");
		employee2.setFirstName("Pete");
		employee2.setLastName("Best");
		employee2.setPosition("Developer II");
		employee2.setDepartment("Engineering");
		compensation2.setEmployee(employee2);
		compensation2.setSalary(55000.00);
		compensation2.setEffectiveDate("6/14/2018");
		compensationRepository.insert(compensation2);
		Compensation compensation3 = new Compensation();
		Employee employee3 = new Employee();
		employee3.setEmployeeId("c0c2293d-16bd-4603-8e08-638a9d18b22c");
		employee3.setFirstName("George");
		employee3.setLastName("Harrison");
		employee3.setPosition("Developer III");
		employee3.setDepartment("Engineering");
		compensation3.setEmployee(employee3);
		compensation3.setSalary(60000.00);
		compensation3.setEffectiveDate("6/15/2018");
		compensationRepository.insert(compensation3);
    }
}
