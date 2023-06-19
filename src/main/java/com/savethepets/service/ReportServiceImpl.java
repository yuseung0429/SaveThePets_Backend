package com.savethepets.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.Report;
import com.savethepets.repository.ReportRepository;

import lombok.RequiredArgsConstructor;

/**
 * Description<br>
 *  - ReportServiceImpl Class : ReportService를 구현한 구현체 클래스<br>
 * <br>
 * Field<br>
 *  - reportRepository : Report Table 접근 Repository <br>
 * <br>
 * Method<br>
 *  - createReport : 신고를 생성하는 메소드 <br> 
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	
	private final ReportRepository reportRepository;
	
	/**
	 * Description<br>
	 *  - createReport : 신고를 생성하는 메소드 <br> 
	 * @param report Report Object
	 * @return true/false (생성 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
    @Override
    public boolean createReport(Report report) {
    	if(reportRepository.findOne(report.getReportId())==null)
    	{
    		reportRepository.save(report);
    		return true;
    	}
    	else
    		return false;
    }
}
