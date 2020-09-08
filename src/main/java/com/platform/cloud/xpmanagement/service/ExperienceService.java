package com.platform.cloud.xpmanagement.service;

import com.platform.cloud.xpmanagement.dao.entity.Experience;
import com.platform.cloud.xpmanagement.dao.ExperienceManagementDaoImpl;
import com.platform.cloud.xpmanagement.dao.entity.ExperienceLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExperienceService implements IExperienceService {

    private ExperienceManagementDaoImpl experienceManagementDao;

    @Autowired
    public ExperienceService(ExperienceManagementDaoImpl experienceManagementDao) {
        this.experienceManagementDao = experienceManagementDao;
    }

    @Override
    public Experience getExperience(int playerId) {
        log.info("Service for getting experience by playerId {}", playerId);
        List<Experience> experienceList =  experienceManagementDao.getExperience(playerId);
        if(experienceList != null && experienceList.size() > 0) {
            log.info("Found the experience: {}", experienceList.get(0).toString());

            return experienceList.get(0);
        }

        log.warn("Experience is null!!!");
        return null;
    }

    @Override
    public void addExperience(Experience experience) {
        log.info("Service for adding experience {}", experience.toString());
        experienceManagementDao.addExperience(experience);
    }

    @Override
    public void updateBalance(Experience experience) {
        log.info("Service for updating balance {}", experience.getBalance());
        experienceManagementDao.updateBalance(experience);
    }

    @Override
    public List<Integer> findExperienceLogAmount(int experienceId) {
        log.info("Service for finding experience amounts from log");
        return experienceManagementDao.findAmountFromExperienceLog(experienceId);
    }

    @Override
    public void addExperienceLog(ExperienceLog experienceLog) {
        log.info("Service for adding experience log {}", experienceLog.toString());
        experienceManagementDao.addExperienceLog(experienceLog);
    }
}
