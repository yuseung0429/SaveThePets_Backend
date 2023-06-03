package com.savethepets.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.Report;
import com.savethepets.repository.ReportRepository;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	
	private final ReportRepository reportRepository;
	
    @Override
    public boolean createReport(Report report) {
    	// DB에 ReportId에 해당하는 record가 없는 경우
    	if(reportRepository.findOne(report.getReportId())==null)
    	{
    		reportRepository.save(report);
    		return true;
    	}
    	// DB에 ReportId에 해당하는 record가 있는 경우
    	else
    		return false;
    }
}
