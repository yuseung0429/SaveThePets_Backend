
import com.savethepets.service.CommentReportServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto;

@Slf4j
@RestController
@RequestMapping(value = "/commentreport")

public class CommentReportController {
	private final CommentReportServiceImpl commentReportService;
	
	@PostMapping("/create")
	ResponseEntity<Boolean> createPostReport(@RequestBody CommentReportDTO commentReportDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@PutMapping("/update")
	ResponseEntity<Boolean> updatePostReport(@RequestBody CommentReportDTO commentReportDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
}