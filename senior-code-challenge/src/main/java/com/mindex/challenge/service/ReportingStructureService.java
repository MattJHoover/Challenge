package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

public interface ReportingStructureService {
    ReportingStructure create(ReportingStructure reportingStructure);
    ReportingStructure read(Employee employee);
    ReportingStructure update(ReportingStructure reportingStructure);
}
