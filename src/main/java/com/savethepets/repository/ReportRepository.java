package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.Report;
import com.savethepets.id.ReportId;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReportRepository {
	private final EntityManager em;
	
	public void save(Report report) {
		em.persist(report);
	}
	
	public void remove(Report report) {
		em.remove(report);
	}
	
	public Report findOne(ReportId reportId) {
		return em.find(Report.class, reportId);
	}
	
	public List<Report> findByType(Boolean type) {
		String query = "select r from Report r where type = :type";
		return em.createQuery(query,Report.class).setParameter("type", type).getResultList();
	}
	
	public List<Report> findByObjectIdAndType(Long objectId, Boolean type) {
		String query = "select r from Report r where (objectId =:objectId) and (type = :type)";
		return em.createQuery(query,Report.class).setParameter("objectId", objectId).setParameter("type", type).getResultList();
	}
	
	public List<Report> findByUserIdAndType(String userId, Boolean type) {
		String query = "select r from Report r where (userId =:userId) and (type = :type)";
		return em.createQuery(query,Report.class).setParameter("userId", userId).setParameter("type", type).getResultList();
	}
	
}
