package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.ReportingStructureRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
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

    @Override
    public ReportingStructure create(ReportingStructure reportingStructure) {
        LOG.debug("Creating reporting structure [{}]", reportingStructure);

        reportingStructureRepository.insert(reportingStructure);

        return reportingStructure;
    }

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating reporting structure with employeeId [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = reportingStructureRepository.findByEmployee(employee);

        if (reportingStructure == null) {
            throw new RuntimeException("Invalid employee: " + id);
        }

        return reportingStructure;
    }

    @Override
    public ReportingStructure update(ReportingStructure reportingStructure) {
        LOG.debug("Updating reporting structure [{}]", reportingStructure);

        return reportingStructureRepository.save(reportingStructure);
    }
}
