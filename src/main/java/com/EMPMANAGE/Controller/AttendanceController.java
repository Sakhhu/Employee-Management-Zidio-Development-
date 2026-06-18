package com.EMPMANAGE.Controller;

import com.EMPMANAGE.Entity.Attendance;
import com.EMPMANAGE.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // POST: http://localhost:8082/api/attendance/checkin/{employeeId}
    @PostMapping("/checkin/{employeeId}")
    public ResponseEntity<Attendance> logCheckIn(@PathVariable Long employeeId) {
        Attendance attendance = attendanceService.checkIn(employeeId);
        return ResponseEntity.ok(attendance);
    }

    // GET: http://localhost:8082/api/attendance/{employeeId}
    @GetMapping("/{employeeId}")
    public List<Attendance> getAttendance(@PathVariable Long employeeId) {
        return attendanceService.getEmployeeAttendance(employeeId);
    }
}
