package com.platform.cloud.xpmanagement.controller;

import com.platform.cloud.xpmanagement.action.ExperienceManagementAction;
import com.platform.cloud.xpmanagement.constant.ExperienceConstant;
import com.platform.cloud.xpmanagement.dao.entity.Experience;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/experience")
public class ExperienceController {
    private ExperienceManagementAction action;

    public ExperienceController(ExperienceManagementAction action) {
        this.action = action;
    }
    @GetMapping(value="/{playerId}")
    public ResponseEntity<Experience> getExperienceBalance(@PathVariable String playerId) {
        try {
            int convertedPlayerId = Integer.parseInt(playerId);

            Experience experience = action.getExperienceAction(convertedPlayerId);

            return new ResponseEntity<>(experience, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping(value="/{playerId}")
    public ResponseEntity<Map<String, Integer>> addExperiencePoints(@PathVariable String playerId) {
        try {
            int convertedPlayerId = Integer.parseInt(playerId);
            int points = action.addExperienceAction(convertedPlayerId);

            Map<String, Integer> pointsObj = new HashMap<>();
            pointsObj.put(ExperienceConstant.POINTS_KEY, points);

            return new ResponseEntity<>(pointsObj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
