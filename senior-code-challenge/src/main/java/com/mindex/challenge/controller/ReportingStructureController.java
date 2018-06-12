package com.mindex.challenge.controller;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportingStructureService reportingStructureService;
    
    @PostMapping("/reportingStructure")
    public ReportingStructure create(@RequestBody ReportingStructure reportingStructure) {
        LOG.debug("Received reporting structure create request for [{}]", reportingStructure);

        return reportingStructureService.create(reportingStructure);
    }

    @GetMapping("/reportingStructure/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received reporting structure create request for id [{}]", id);

        return reportingStructureService.read(id);
    }
    
    @PutMapping("/reportingStructure/{id}")
    public ReportingStructure update(@PathVariable String id, @RequestBody ReportingStructure reportingStructure) {
        LOG.debug("Received reporting structure create request for id [{}] and reporting structure [{}]", id, reportingStructure);
        
        Employee employee = employeeRepository.findByEmployeeId(id);
        reportingStructure.setEmployee(employee);
        return reportingStructureService.update(reportingStructure);
    }
}
