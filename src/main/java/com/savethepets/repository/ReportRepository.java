package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.Report;
import com.savethepets.id.ReportId;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
/**
 * Description<br>
 *  - ReportRepository Class : Report Table에 접근 Repository Class<br>
 * <br>
 * Field<br>
 *  - em : Entity 관리 Object<br>
 * <br>
 * Method<br>
 *  - save : 신고를 저장하는 메소드<br>
 *  - remove : 신고를 삭제하는 메소드<br>
 *  - findOne : 신고를 검색하는 메소드<br>
 *  - findByType : 신고 타입과 일치하는 북마크를 검색하는 메소드<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Repository
@RequiredArgsConstructor
public class ReportRepository {
	private final EntityManager em;
	/**
	 * Description<br>
	 *  - save : 신고를 저장하는 메소드<br>
	 * @param report Report Object
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public void save(Report report) {
		em.persist(report);
	}
	/**
	 * Description<br>
	 *  - remove : 신고를 삭제하는 메소드<br>
	 * @param report Report Object
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public void remove(Report report) {
		em.remove(report);
	}
	/**
	 * Description<br>
	 *  - findOne : 신고를 검색하는 메소드<br>
	 * @param reportId ReportId({@link com.savethepets.id.ReportId})
	 * @return Bookmark Object 또는 null(존재하지 않을 경우)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public Report findOne(ReportId reportId) {
		return em.find(Report.class, reportId);
	}
	/**
	 * Description<br>
	 *  - findByUserId : 신고 타입과 일치하는 신고를 검색하는 메소드<br>
	 * @param type 0:게시물/1:댓글
	 * @return Report Object List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public List<Report> findByType(Boolean type) {
		String query = "select r from Report r where r.ReportId.type = :type";
		return em.createQuery(query,Report.class).setParameter("type", type).getResultList();
	}
	
}
