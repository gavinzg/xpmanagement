package com.platform.cloud.xpmanagement.service;

import com.platform.cloud.xpmanagement.dao.ExperienceManagementDaoImpl;
import com.platform.cloud.xpmanagement.dao.entity.Experience;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.platform.cloud.xpmanagement.constant.TestConstant;
import util.TestFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExperienceServiceTest {

    @Mock
    private ExperienceManagementDaoImpl mockExperienceManagementDaoImpl;
    private ExperienceService experienceService;

    @Before
    public void init() {
        initMocks(this);

        List<Experience> list = new ArrayList<>();
        list.add(TestFixture.mockExperience());

        List<Integer> amountList = new ArrayList<>();
        amountList.add(TestConstant.AMOUNT);

        when(mockExperienceManagementDaoImpl.getExperience(eq(TestConstant.PLAYER_ID_1))).thenReturn(list);
        when(mockExperienceManagementDaoImpl.findAmountFromExperienceLog(eq(TestConstant.EXPERIENCE_ID))).thenReturn(amountList);

        experienceService = new ExperienceService(mockExperienceManagementDaoImpl);
    }

    @Test
    public void testGetExperience() {
        Experience result = experienceService.getExperience(TestConstant.PLAYER_ID_1);

        assertNotNull(result);
    }

    @Test
    public void testGetExperienceWhenExperienceListIsEmpty() {
        when(mockExperienceManagementDaoImpl.getExperience(eq(TestConstant.PLAYER_ID_2))).thenReturn(Collections.emptyList());

        Experience result = experienceService.getExperience(TestConstant.PLAYER_ID_2);

        assertNull(result);
    }

    @Test
    public void testGetExperienceWhenExperienceListIsNull() {
        when(mockExperienceManagementDaoImpl.getExperience(eq(TestConstant.PLAYER_ID_2))).thenReturn(null);

        Experience result = experienceService.getExperience(TestConstant.PLAYER_ID_2);

        assertNull(result);
    }

    @Test
    public void testAddExperience() {
        experienceService.addExperience(TestFixture.mockExperience());

        verify(mockExperienceManagementDaoImpl, times(1)).addExperience(any());
    }

    @Test
    public void testAddExperienceLog() {
        experienceService.addExperienceLog(TestFixture.mockExperienceLog());

        verify(mockExperienceManagementDaoImpl, times(1)).addExperienceLog(any());
    }

    @Test
    public void testUpdateBalance() {
        experienceService.updateBalance(TestFixture.mockExperience());

        verify(mockExperienceManagementDaoImpl, times(1)).updateBalance(any());
    }

    @Test
    public void testFindExperienceLogAmount() {
        List<Integer> result = experienceService.findExperienceLogAmount(1);

        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0), (Integer)TestConstant.AMOUNT);
    }
}
