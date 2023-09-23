package com.management.employee.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "employee_attendace_report")
public class EmployeeAttendaceReport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "employee_id")
	private long employeeId;
	
	@Column(name="attend_date")
	private String attendDate;

	@Column(name = "day")
	private String day;

	@Column(name = "month")
	private String month;

	@Column(name = "year")
	private String year;

	@Column(name = "updatedDateTime")
	private LocalDateTime updatedDateTime;

	@Column(name = "final_report")
	private String finalReport;

}
