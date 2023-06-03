package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDTO {
    Long objectId;
    int reportType;
    String reportReason;
    Boolean type;
}
