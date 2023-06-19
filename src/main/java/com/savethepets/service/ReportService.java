package com.savethepets.service;

import com.savethepets.entity.Report;
/**
 * Description<br>
 *  - ReportService Interface<br>
 * <br>
 * Method <br>
 *  - createReport : 신고를 생성하는 메소드 <br> 
 * @author Yuseung lee.
 * @since 2023.06.19
 */
public interface ReportService {
    boolean createReport(Report report);
}
