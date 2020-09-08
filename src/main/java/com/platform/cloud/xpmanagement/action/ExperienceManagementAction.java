package com.platform.cloud.xpmanagement.action;

import com.platform.cloud.xpmanagement.dao.entity.Experience;
import com.platform.cloud.xpmanagement.dao.entity.ExperienceLog;
import com.platform.cloud.xpmanagement.dao.entity.Type;
import com.platform.cloud.xpmanagement.service.IExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class ExperienceManagementAction {
    private static final int AMOUNT = 5;
    private static final int INITIAL_BALANCE = 0;
    private IExperienceService experienceService;

    @Autowired
    public ExperienceManagementAction(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    public Experience getExperienceAction(int playerId) {
        Experience experience = experienceService.getExperience(playerId);

        if (experience != null) {
            return experience;
        } else {
            createExperience(playerId);
            return experienceService.getExperience(playerId);
        }
    }

    public int addExperienceAction(int playerId) {
        Experience experience = experienceService.getExperience(playerId);

        if (experience == null) {
            createExperience(playerId);
            experience = experienceService.getExperience(playerId);
        }

        if (experience != null) {
            createExperienceLog(experience);
        }

        return updateBalance(experience);
    }

    private void createExperience(int playerId) {
        Experience experience = new Experience();
        experience.setBalance(INITIAL_BALANCE);
        experience.setPlayerId(playerId);

        Timestamp currentTimeStamp = new Timestamp(new Date().getTime());
        experience.setCreatedAt(currentTimeStamp);
        experience.setUpdatedAt(currentTimeStamp);
        experienceService.addExperience(experience);
    }

    private int updateBalance(Experience experience) {
        int balance = INITIAL_BALANCE;
        if (experience != null) {
            List<Integer> amountList = experienceService.findExperienceLogAmount(experience.getExperienceId());
            for (int amount: amountList) {
                balance += amount;
            }
            experience.setBalance(balance);
            experience.setUpdatedAt(new Timestamp(new Date().getTime()));
            experienceService.updateBalance(experience);

            return balance;
        }

        return balance;
    }

    private void createExperienceLog(Experience experience) {
        if (experience != null) {
            ExperienceLog experienceLog = new ExperienceLog();
            experienceLog.setExperience(experience);
            experienceLog.setPlayerId(experience.getPlayerId());
            experienceLog.setCreatedAt(new Timestamp(new Date().getTime()));
            experienceLog.setAmount(AMOUNT);
            if (experience.getBalance() >= 0) {
                experienceLog.setType(Type.EARN.name());
            } else {
                experienceLog.setType(Type.PENALTY.name());
            }

            experienceService.addExperienceLog(experienceLog);
        }
    }
}
