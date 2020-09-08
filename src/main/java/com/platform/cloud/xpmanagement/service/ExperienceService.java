package com.platform.cloud.xpmanagement.service;

import com.platform.cloud.xpmanagement.dao.entity.Experience;
import com.platform.cloud.xpmanagement.dao.ExperienceManagementDaoImpl;
import com.platform.cloud.xpmanagement.dao.entity.ExperienceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService implements IExperienceService {

    private ExperienceManagementDaoImpl experienceManagementDao;

    @Autowired
    public ExperienceService(ExperienceManagementDaoImpl experienceManagementDao) {
        this.experienceManagementDao = experienceManagementDao;
    }

    @Override
    public Experience getExperience(int playerId) {
        List<Experience> experienceList =  experienceManagementDao.getExperience(playerId);
        if(experienceList != null && experienceList.size() > 0) {
            return experienceList.get(0);
        }

        return null;
    }

    @Override
    public void addExperience(Experience experience) {
        experienceManagementDao.addExperience(experience);
    }

    @Override
    public void updateBalance(Experience experience) {
        experienceManagementDao.updateBalance(experience);
    }

    @Override
    public List<Integer> findExperienceLogAmount(int experienceId) {
        return experienceManagementDao.findAmountFromExperienceLog(experienceId);
    }

    @Override
    public void addExperienceLog(ExperienceLog experienceLog) {
        experienceManagementDao.addExperienceLog(experienceLog);
    }
}
