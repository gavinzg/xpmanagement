package com.platform.cloud.xpmanagement.dao;

import com.platform.cloud.xpmanagement.dao.entity.Experience;
import com.platform.cloud.xpmanagement.dao.entity.ExperienceLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    @Override
    public List<Experience> getExperience(int playerId) {
        return (List<Experience>) hibernateTemplate.find("from Experience e where e.playerId=?", playerId);

    }

    @Override
    public void addExperience(Experience experience) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(experience);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateBalance(Experience experience) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.update(experience);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Integer> findAmountFromExperienceLog(int experienceId) {
       return (List<Integer>) hibernateTemplate.find("select log.amount from ExperienceLog log where log.experienceId.experienceId=?", experienceId);
    }

    @Override
    public void addExperienceLog(ExperienceLog experienceLog) {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(experienceLog);
        transaction.commit();
        session.close();
    }
}
