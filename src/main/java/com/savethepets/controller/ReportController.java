
package com.savethepets.controller;

import com.savethepets.service.AuthServiceImpl;
import com.savethepets.service.ReportServiceImpl;
import lombok.RequiredArgsConstructor;

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
/**
 * Description<br>
 *  - ReportController Class : Report 관련 정보를 처리하는 ReportController Class<br>
 * <br>
 * Field<br>
 * 	- reportService : 신고 관련 처리를 위한 ReportService <br>
 *  - authService : 인증 관련 처리를 위한 AuthService <br>
 * Method<br>
 *  - createReport : 신고를 생성하는 Service와 연결하는 메소드 <br> 
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/report")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {
	private final ReportServiceImpl reportService;
	private final AuthServiceImpl authService;
	/**
	 * Description<br>
	 *  - createReport : 신고를 생성하는 Service와 연결하는 메소드 <br> 
	 * <br>
	 * EndPoint<br>
	 *  - /report<br>
	 * <br>
	 * Mapping method<br>
	 *  - PostMapping<br>
	 * @param token JWT
	 * @param reportDTO ({@link com.savethepets.dto.ReportDTO})
	 * @return ResponseEntity와 true/false(생성 성공 여부)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@PostMapping()
	ResponseEntity<Boolean> createReport(@RequestHeader("token") String token, @RequestBody ReportDTO reportDTO){
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