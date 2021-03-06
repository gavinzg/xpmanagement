package com.platform.cloud.xpmanagement.service;

import com.platform.cloud.xpmanagement.dao.entity.Experience;
import com.platform.cloud.xpmanagement.dao.entity.ExperienceLog;

import java.util.List;

public interface IExperienceService {
    Experience getExperience(int playerId);

    void addExperience(Experience experience);

    void updateBalance(Experience experience);

    List<Integer> findExperienceLogAmount(int experienceId);

    void addExperienceLog(ExperienceLog experienceLog);
}
