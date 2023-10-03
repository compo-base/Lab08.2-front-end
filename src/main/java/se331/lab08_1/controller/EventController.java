package se331.lab08_1.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab08_1.entity.Event;
import se331.lab08_1.service.EventService;
import se331.lab08_1.util.LabMapper;

@Controller
@RequiredArgsConstructor
public class EventController {

    final EventService eventService;

    @GetMapping("events")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit",required = false)Integer perPage,
                                           @RequestParam(value = "_page",required = false)Integer page){
        Page<Event> pageOutput = eventService.getEvents(perPage,page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count",String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getEventDto(pageOutput.getContent()),responseHeaders, HttpStatus.OK);
    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id")Long id){
        Event output = eventService.getEvent(id);
        if(output != null){
            return ResponseEntity.ok(LabMapper.INSTANCE.getEventDto(output));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the given id is not found");
        }
    }

    @PostMapping("/events")
    public ResponseEntity<?> addEvent(@RequestBody Event event){
        Event output = eventService.save(event);
        return ResponseEntity.ok(LabMapper.INSTANCE.getEventDto(output));
    }


}
