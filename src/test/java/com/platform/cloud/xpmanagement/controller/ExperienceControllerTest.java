package com.platform.cloud.xpmanagement.controller;

import com.platform.cloud.xpmanagement.action.ExperienceManagementAction;
import com.platform.cloud.xpmanagement.constant.ExperienceConstant;
import com.platform.cloud.xpmanagement.constant.TestConstant;
import com.platform.cloud.xpmanagement.dao.entity.Experience;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import util.TestFixture;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExperienceControllerTest {
    @Mock
    private ExperienceManagementAction mockAction;
    private ExperienceController experienceController;

    @Before
    public void setup() {
        initMocks(this);

        when(mockAction.getExperienceAction(eq(TestConstant.PLAYER_ID_1))).thenReturn(TestFixture.mockExperience());
        when(mockAction.addExperienceAction(eq(TestConstant.PLAYER_ID_1))).thenReturn(TestConstant.AMOUNT);

        experienceController = new ExperienceController(mockAction);
    }

    @Test
    public void testGetExperience() {
        ResponseEntity<Experience> response = experienceController.getExperienceBalance(TestConstant.PLAYER_ID);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getExperienceId().intValue(), TestConstant.EXPERIENCE_ID);
        assertEquals(response.getBody().getBalance().intValue(), TestConstant.BALANCE);
    }

    @Test
    public void testAddExperiencePoints() {
        ResponseEntity<Map<String, Integer>> response = experienceController.addExperiencePoints(TestConstant.PLAYER_ID);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey(ExperienceConstant.POINTS_KEY));
        assertEquals(response.getBody().get(ExperienceConstant.POINTS_KEY).intValue(), TestConstant.BALANCE);
    }
}
