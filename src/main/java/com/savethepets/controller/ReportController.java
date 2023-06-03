
package com.savethepets.controller;

import com.savethepets.service.ReportServiceImpl;
import com.savethepets.utility.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.*;
import com.savethepets.entity.Report;
import com.savethepets.id.ReportId;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/report")
public class ReportController {
	private final ReportServiceImpl reportService;
	
	@PostMapping()
	ResponseEntity<Boolean> createPostReport(@RequestHeader("token") String token, @RequestBody ReportDTO reportDTO){
		String userId;
		if((userId = Utilities.verifiy(token)) != null)
		{
			ReportId tempId = new ReportId(reportDTO.getObjectId(), userId, reportDTO.getType());
			Report temp = new Report(tempId, reportDTO.getReportType(), reportDTO.getReportReason());
			// DB에 recode 삽입이 성공한 경우
			if(reportService.createReport(temp) == true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			// DB에 recode 삽입이 실패한 경우 (Id에 해당하는 record가 이미 있는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
	
}