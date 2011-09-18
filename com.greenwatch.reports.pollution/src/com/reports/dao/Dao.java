package com.reports.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.reports.model.Report;



public enum Dao {
	INSTANCE;

	public List<Report> listReports() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Report m");
		List<Report> reps = q.getResultList();
		return reps;
	}

	public void add(String userId, String summary, String description,
			String url) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Report rep = new Report(userId, summary, description, url);
			em.persist(rep);
			em.close();
		}
	}

	public List<Report> getReports(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select r from Report r where r.author = :userId");
		q.setParameter("userId", userId);
		List<Report> reps = q.getResultList();
		return reps;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Report rep = em.find(Report.class, id);
			em.remove(rep);
		} finally {
			em.close();
		}
	}
}
