package com.platform.cloud.xpmanagement.dao;

import com.platform.cloud.xpmanagement.dao.entity.Experience;
import com.platform.cloud.xpmanagement.dao.entity.ExperienceLog;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ExperienceManagementDaoImpl implements ExperienceManagementDao {
    private static SessionFactory ourSessionFactory;
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public ExperienceManagementDaoImpl() {
        this.hibernateTemplate = new HibernateTemplate(ourSessionFactory);
    }

    static {
        sessionFactorySetup();
    }

    private static void sessionFactorySetup() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Failed to setup session factory", ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    @Override
    public List<Experience> getExperience(int playerId) {
        List<Experience> experiences = new ArrayList<>();
        try {
            log.info("Start to search experience by playerId {} from database", playerId);
            experiences = (List<Experience>) hibernateTemplate.find("from Experience e where e.playerId=?", playerId);
            log.info("Successfully executed statement, the experience is {}", experiences.get(0).toString());

        } catch (Exception e) {
            log.error("Failed to get experience by playerId {}.", playerId, e);
        }

        return experiences;
    }

    @Override
    public void addExperience(Experience experience) {
        try {
            final Session session = getSession();
            Transaction transaction = session.beginTransaction();
            log.info("Start to save experience {}", experience.toString());
            session.save(experience);
            log.info("Add the experience successfully!");
            transaction.commit();
            session.close();
        } catch (Exception e) {
            log.error("Failed to add a new Experience {}.", experience.toString(), e);
        }

    }

    @Override
    public void updateBalance(Experience experience) {
        try {
            final Session session = getSession();
            Transaction transaction = session.beginTransaction();
            log.info("Start to update the balance for experience {}", experience.toString());
            session.update(experience);
            transaction.commit();
            log.info("Update the balance successfully!");
            session.close();
        } catch (Exception e) {
            log.error("Failed to update the balance for experience {}", experience.toString(), e);
        }

    }

    @Override
    public List<Integer> findAmountFromExperienceLog(int experienceId) {
        List<Integer> amounts = new ArrayList<>();
        try {
            log.info("Start to find the amount list by experienceId: {}", experienceId);
            amounts = (List<Integer>)  hibernateTemplate.find("select log.amount from ExperienceLog log where log.experienceId.experienceId=?", experienceId);
            log.info("Successfully executed the statement, the amounts is {}", amounts.toString());
        } catch (Exception e) {
            log.error("Failed to find the amount by experienceId {}", experienceId);
        }
       return amounts;

    }

    @Override
    public void addExperienceLog(ExperienceLog experienceLog) {
        try {
            final Session session = getSession();
            Transaction transaction = session.beginTransaction();
            log.info("Start to add experience log {}", experienceLog);
            session.save(experienceLog);
            transaction.commit();
            log.info("Add a new experience log successfully!");
            session.close();
        } catch (Exception e) {
            log.error("Failed to add experience log {}", experienceLog, e);
        }

    }
}
