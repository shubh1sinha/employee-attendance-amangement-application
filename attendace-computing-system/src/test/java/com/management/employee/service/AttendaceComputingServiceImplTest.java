package com.management.employee.service;

import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.management.employee.entity.EmployeeAttendaceReport;
import com.management.employee.entity.EmployeeAttendance;
import com.management.employee.entity.EmployeeReport;
import com.management.employee.entity.InOutRecords;
import com.management.employee.enums.SwipedCase;
import com.management.employee.exceptions.InvalidRequestException;
import com.management.employee.feign.EmployeeTrackingFeign;
import com.management.employee.repository.impl.AttendanceComputingRepositoryImplementation;
import com.management.employee.service.impl.AttendanceComputingServiceImplementation;


@ExtendWith(MockitoExtension.class)
public class AttendaceComputingServiceImplTest {
	
	@InjectMocks
	private AttendanceComputingRepositoryImplementation attendanceComputingRespository;
	
	@Mock
	private AttendanceComputingServiceImplementation attendanceComputingServiceImplementation;
	
	@Mock
	private EmployeeTrackingFeign employeeTrackingFeign;

    @Test
    public void testCalculateTotalWorkingHoursWhenInRecordsAndOutRecordsAreEmpty() {
        long employeeId = 1;
        EmployeeAttendance matchingAttendance = new EmployeeAttendance();
        matchingAttendance.setInOutRecords(new ArrayList<>());
        try {
            long result = attendanceComputingServiceImplementation.calculateTotalWorkingHours(employeeId, matchingAttendance);
            Assertions.assertEquals(0, result);
        } catch (InvalidRequestException e) {
            Assertions.fail("Unexpected InvalidRequestException was thrown");
        }
    }

    @Test
    public void testCalculateTotalWorkingHoursWhenInRecordsAndOutRecordsAreEqual() {
        long employeeId = 1;
        EmployeeAttendance matchingAttendance = new EmployeeAttendance();
        List<InOutRecords> inOutRecords = new ArrayList<>();
        InOutRecords inOutRecord1 = new InOutRecords();
        inOutRecord1.setSwipedType(SwipedCase.SWIPED_IN.displayString());
        inOutRecord1.setUpdatedTime(LocalTime.of(9, 0));
        InOutRecords inOutRecord2 = new InOutRecords();
        inOutRecord2.setSwipedType(SwipedCase.SWIPED_OUT.displayString());
        inOutRecord2.setUpdatedTime(LocalTime.of(9, 0));
        inOutRecords.add(inOutRecord1);
        inOutRecords.add(inOutRecord2);
        matchingAttendance.setInOutRecords(inOutRecords);
        try {
            long result = attendanceComputingServiceImplementation.calculateTotalWorkingHours(employeeId, matchingAttendance);
            Assertions.assertEquals(0, result);
        } catch (InvalidRequestException e) {
        	Assertions.fail("Unexpected InvalidRequestException was thrown");
        }
    }
    
//    @Test
//    public void testSaveEveryDayAttendanceRecord() throws InvalidRequestException {
//        EmployeeReport employeeReport = new EmployeeReport();
//        employeeReport.setAttendaceReport("ABSENT");
//        employeeReport.getEmployeeRecord().setEmployeeId(1);
//        when(attendanceComputingRespository.saveEveryDayAttendaceRecord(employeeReport)).thenReturn("Attendance record saved successfully");
//        String result = attendanceComputingServiceImplementation.saveEveryDayAttendaceRecord(employeeReport);
//        Assertions.assertEquals("Attendance record saved successfully", result);
//    }
//
//    @Test
//    public void testGetAbsentEmployeesByDate() {
//        Map<String, String> dateRange = new HashMap<>();
//        dateRange.put("TODAY", "");
//        dateRange.put("Range", "2022-01-31");
//        List<EmployeeAttendaceReport> expectedReports = new ArrayList<>();
//        when(attendanceComputingRespository.getAbsentEmployeesByDate("2022-01-01")).thenReturn(expectedReports);
//        List<EmployeeAttendaceReport> result = attendanceComputingServiceImplementation.getAbsentEmployeesByDate(dateRange);
//        Assertions.assertEquals(expectedReports, result);
//    }
	
	

}
