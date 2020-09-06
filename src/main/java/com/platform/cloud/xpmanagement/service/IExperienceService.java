package com.platform.cloud.xpmanagement.service;

import com.platform.cloud.xpmanagement.model.Experience;

public interface IExperienceService {
    public Experience getExperience(int playerId);
    public void addPoints(int playerId);
}
