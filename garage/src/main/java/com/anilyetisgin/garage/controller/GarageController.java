package com.anilyetisgin.garage.controller;


import com.anilyetisgin.garage.service.GarageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("garage")
public class GarageController {

    private final GarageService garageService;

    @PostMapping
    public ResponseEntity<String> issueTicket(String action) {
        return ResponseEntity.ok(garageService.issueTicket(action));
    }

}
