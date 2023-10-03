package se331.lab08_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se331.lab08_1.service.OrganizerService;

@RestController
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;
    @GetMapping("/organizer")
    ResponseEntity<?> getOrganizers(){
        return ResponseEntity.ok(organizerService.getAllOrganizer());
    }
}
