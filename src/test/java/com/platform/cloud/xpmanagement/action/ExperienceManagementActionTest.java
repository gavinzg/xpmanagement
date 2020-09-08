package com.platform.cloud.xpmanagement.action;

import com.platform.cloud.xpmanagement.constant.TestConstant;
import com.platform.cloud.xpmanagement.dao.entity.Experience;
import com.platform.cloud.xpmanagement.service.IExperienceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import util.TestFixture;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExperienceManagementActionTest {
    @Mock
    private IExperienceService mockExperienceService;
    private ExperienceManagementAction experienceManagementAction;

    @Before
    public void setup() {
        initMocks(this);
        when(mockExperienceService.getExperience(eq(TestConstant.PLAYER_ID_1))).thenReturn(TestFixture.mockExperience());
        when(mockExperienceService.findExperienceLogAmount(eq(TestConstant.EXPERIENCE_ID))).thenReturn(Collections.singletonList(TestConstant.AMOUNT));
        experienceManagementAction = new ExperienceManagementAction(mockExperienceService);
    }

    @Test
    public void testAddExperienceActionWhenPlayerExists() {
        int result = experienceManagementAction.addExperienceAction(TestConstant.PLAYER_ID_1);

        verify(mockExperienceService, times(1)).addExperienceLog(any());
        verify(mockExperienceService, times(1)).updateBalance(any());

        assertEquals(result, TestConstant.AMOUNT);
    }

    @Test
    public void testAddExperienceActionWhenPlayerNotExist() {
        int result = experienceManagementAction.addExperienceAction(TestConstant.PLAYER_ID_2);

        verify(mockExperienceService, times(1)).addExperience(any());
        verify(mockExperienceService, times(2)).getExperience(eq(TestConstant.PLAYER_ID_2));

        assertEquals(result, 0);
    }

    @Test
    public void testGetExperienceWhenPlayerExists() {
        Experience result = experienceManagementAction.getExperienceAction(TestConstant.PLAYER_ID_1);

        assertNotNull(result);
        assertEquals(result.getExperienceId().intValue(), TestConstant.EXPERIENCE_ID);
        assertEquals(result.getBalance().intValue(), TestConstant.BALANCE);
    }

    @Test
    public void testGetExperienceWhenPlayerNotExist() {
        Experience result = experienceManagementAction.getExperienceAction(TestConstant.PLAYER_ID_2);

        assertNull(result);
        verify(mockExperienceService, times(1)).addExperience(any());
        verify(mockExperienceService, times(2)).getExperience(eq(TestConstant.PLAYER_ID_2));
    }
}
