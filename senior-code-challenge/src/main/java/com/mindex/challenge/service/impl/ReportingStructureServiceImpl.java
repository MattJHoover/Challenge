package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.ReportingStructureRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportingStructureRepository reportingStructureRepository;
    
    private int numberOfDirectReports = 0;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating reporting structure with employeeId [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        List<Employee> directReports = employee.getDirectReports();
        if(directReports != null) {
        	numberOfDirectReports = directReports.size();
        	for(Employee directReport : directReports) {
        		numberOfDirectReports += countDirectReports(directReport);
        	}
        }
        reportingStructure.setNumberOfReports(numberOfDirectReports);
        if(numberOfDirectReports > 0) {
        	numberOfDirectReports = 0;
        }
        reportingStructureRepository.insert(reportingStructure);
        ReportingStructure reporting = reportingStructureRepository.findByEmployee(employee);

        if (reporting == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return reporting;
    }
    
    public int countDirectReports(Employee employee) {
    	int numberOfReports = 0;
    	String empId = employee.getEmployeeId();
    	Employee emp = employeeRepository.findByEmployeeId(empId);
    	List<Employee> directReports = emp.getDirectReports();
    	if(directReports == null) {
    		return 0;
    	}
    	numberOfReports += directReports.size();
    	for(Employee directReport : directReports) {
    		numberOfReports += countDirectReports(directReport);
    	}
		return numberOfReports;
    }
}