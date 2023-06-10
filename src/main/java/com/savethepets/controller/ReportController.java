
package com.savethepets.controller;

import com.savethepets.service.AuthServiceImpl;
import com.savethepets.service.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {
	private final ReportServiceImpl reportService;
	private final AuthServiceImpl authService;
	
	@PostMapping()
	ResponseEntity<Boolean> createPostReport(@RequestHeader("token") String token, @RequestBody ReportDTO reportDTO){
		String userId;
		if((userId = authService.validateToken(token)) != null)
		{
			ReportId tempId = new ReportId(reportDTO.getObjectId(), userId, reportDTO.getType());
			Report temp = new Report(tempId, reportDTO.getReportType(), reportDTO.getReportReason());
			if(reportService.createReport(temp) == true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
	
}