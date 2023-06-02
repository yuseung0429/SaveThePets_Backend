package com.savethepets.service;

import com.savethepets.entity.Report;

public interface ReportService {
    boolean createReport(Report report);
    boolean updateReport(Report report);
}
