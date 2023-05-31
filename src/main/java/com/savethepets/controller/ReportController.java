
package com.savethepets.controller;

import com.savethepets.service.ReportServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/report")

public class ReportController {
	//private final PostReportServiceImpl postReportService;
	
	@PostMapping("/create")
	ResponseEntity<Boolean> createPostReport(@RequestBody ReportDTO reportDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
}