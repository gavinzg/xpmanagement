package util;

import com.platform.cloud.xpmanagement.constant.TestConstant;
import com.platform.cloud.xpmanagement.dao.entity.Experience;
import com.platform.cloud.xpmanagement.dao.entity.ExperienceLog;

public class TestFixture {
    public static Experience mockExperience() {
        Experience experience = new Experience();
        experience.setExperienceId(TestConstant.EXPERIENCE_ID);
        experience.setBalance(TestConstant.BALANCE);

        return experience;
    }

    public static ExperienceLog mockExperienceLog() {
        ExperienceLog experienceLog = new ExperienceLog();
        experienceLog.setExperienceId(mockExperience());

        return experienceLog;
    }
}
