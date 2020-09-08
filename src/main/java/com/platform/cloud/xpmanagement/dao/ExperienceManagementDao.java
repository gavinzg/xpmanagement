package com.platform.cloud.xpmanagement.dao;

import com.platform.cloud.xpmanagement.dao.entity.Experience;
import com.platform.cloud.xpmanagement.dao.entity.ExperienceLog;

import java.util.List;

public interface ExperienceManagementDao {
    List<Experience> getExperience(int playerId);

    void addExperience(Experience experience);

    void updateBalance(Experience experience);

    List<Integer> findAmountFromExperienceLog(int experienceId);

    void addExperienceLog(ExperienceLog experienceLog);
}
