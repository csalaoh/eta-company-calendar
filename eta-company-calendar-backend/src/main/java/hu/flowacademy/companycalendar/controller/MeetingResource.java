package hu.flowacademy.companycalendar.controller;

import hu.flowacademy.companycalendar.model.dto.MeetingCreateDTO;
import hu.flowacademy.companycalendar.model.dto.MeetingDTO;
import hu.flowacademy.companycalendar.model.dto.MeetingListItemDTO;
import hu.flowacademy.companycalendar.service.MeetingService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meetings")
@AllArgsConstructor
public class MeetingResource {

    private final MeetingService meetingService;

    @GetMapping
    public List<MeetingDTO> getAll() {
        return meetingService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<MeetingListItemDTO> getFromQuery(@PathVariable Long userId,
        @RequestParam Long startingTimeFrom,
        @RequestParam Long startingTimeTo) {
        return meetingService.findByUserIdAndTimeRange(userId, startingTimeFrom, startingTimeTo);
    }

    @GetMapping("/invited/{userId}")
    public List<MeetingCreateDTO> getByInvitedFromQuery(@PathVariable Long userId) {
        return meetingService.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public MeetingCreateDTO getOne(@PathVariable Long id) {
        return meetingService.findAndGetDTO(id);
    }

    @PostMapping
    public ResponseEntity<Object> createMeeting(@RequestBody MeetingCreateDTO dto) {
        meetingService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<Object> createWithEmails(@RequestBody MeetingCreateDTO dto) {
        meetingService.createWithEmails(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateMeeting(@RequestBody MeetingCreateDTO meetingCreateDTO) {
        meetingService.updateMeeting(meetingCreateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        meetingService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
