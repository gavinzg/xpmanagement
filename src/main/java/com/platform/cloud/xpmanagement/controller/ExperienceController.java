package com.platform.cloud.xpmanagement.controller;

import com.platform.cloud.xpmanagement.model.Experience;
import com.platform.cloud.xpmanagement.model.ExperienceLog;
import com.platform.cloud.xpmanagement.service.IExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/experience")
public class ExperienceController {
    private IExperienceService experienceService;

    public ExperienceController(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }
    @GetMapping(value="/{player_id}")
    public ResponseEntity<Experience> getExperienceBalance(@RequestBody String request) {
        int playerId = Integer.parseInt(request);

        Experience experience = experienceService.getExperience(playerId);


        return new ResponseEntity<>(experience, HttpStatus.OK);
    }

    @PostMapping(value="/{player_id}")
    public ResponseEntity<ExperienceLog> addExperiencePoints(@RequestBody String request) {

        return null;
    }
}
