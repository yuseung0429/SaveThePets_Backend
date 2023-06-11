package com.savethepets.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.savethepets.dto.DistancePostDTO;
import com.savethepets.dto.FilterDTO;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.savethepets.entity.Post;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepository {
	private final EntityManager em;
	
	public void save(Post post) {
		em.persist(post);
	}

	public Long create(Post post) {
		save(post);
		return post.getPostId();
	}
	
	public void remove(Post post) {
		em.remove(post);
	}
	
	public Post findOne(Long postId) {
        return em.find(Post.class, postId);
    }
	
	public List<Post> findAll(){
		String query = "select p from Post p";
		return em.createQuery(query, Post.class).getResultList();
	}
	
	public List<Post> findByPostIds(List<Long> postIds){
		String query = "select p from Post p where p.postId in :postIds";
		return em.createQuery(query, Post.class).setParameter("postIds", postIds).getResultList();
	}
	
	public List<Post> findByUserId(String userId){
		String query = "select p from Post p where userId = :userId";
		return em.createQuery(query, Post.class).setParameter("userId", userId).getResultList();
	}

	public List<Post> findAllPosts() {
		String query = "SELECT p FROM Post p";
		return em.createQuery(query, Post.class)
				.getResultList();
	}
	public List<Post> findLostPostsByUserId(String userId) {
		String query = "SELECT p FROM Post p WHERE p.userId = :userId AND p.type = 0 ";
		return em.createQuery(query, Post.class)
				.setParameter("userId", userId)
				.getResultList();
	}

	public List<Post> findPostsByDistance(DistancePostDTO distancePostDTO){
		// 디스턴스디티오에서 우상단 좌표와 좌하단 좌표를 가져옴
		Double rightUpLat = distancePostDTO.getRightUpLat();
		Double rightUpLot = distancePostDTO.getRightUpLot();
		Double leftDownLat = distancePostDTO.getLeftDownlat();
		Double leftDownLot = distancePostDTO.getLeftDownlot();

		// 위도와 경도의 최소값과 최대값 계산
		Double minLat = Math.min(rightUpLat, leftDownLat);
		Double maxLat = Math.max(rightUpLat, leftDownLat);
		Double minLot = Math.min(rightUpLot, leftDownLot);
		Double maxLot = Math.max(rightUpLot, leftDownLot);

		// 축척 계산
		Double latRange = maxLat - minLat;
		Double lotRange = maxLot - minLot;

		// 축척을 기준으로 필요한 게시글 조회를 위한 조건 계산
		Double latThreshold = latRange * 0.1; // 위도의 10% 범위
		Double lotThreshold = lotRange * 0.1; // 경도의 10% 범위

		// 게시글 조회를 위한 쿼리 작성
		String query = "select p from Post p where p.lat between :minLat and :maxLat and p.lot between :minLot and :maxLot " +
				"and (p.lat between :minLatThreshold and :maxLatThreshold or p.lot between :minLotThreshold and :maxLotThreshold)";
		TypedQuery<Post> typedQuery = em.createQuery(query, Post.class);
		typedQuery.setParameter("minLat", minLat);
		typedQuery.setParameter("maxLat", maxLat);
		typedQuery.setParameter("minLot", minLot);
		typedQuery.setParameter("maxLot", maxLot);
		typedQuery.setParameter("minLatThreshold", minLat - latThreshold);
		typedQuery.setParameter("maxLatThreshold", maxLat + latThreshold);
		typedQuery.setParameter("minLotThreshold", minLot - lotThreshold);
		typedQuery.setParameter("maxLotThreshold", maxLot + lotThreshold);

		return typedQuery.getResultList();
	}

	public List<Post> findFilteredPosts(FilterDTO filterDTO) {
		String query = "SELECT p FROM Post p WHERE 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();

		if (filterDTO.getSpecies() != 0) {
			query += "AND p.species = :species ";
			parameters.put("species", filterDTO.getSpecies());
		}
		if (filterDTO.getBreed() != 0) {
			query += "AND p.breed = :breed ";
			parameters.put("breed", filterDTO.getBreed());
		}
		if (filterDTO.getType() != null && filterDTO.getType().length > 0) {
			query += "AND (";
			for (int i = 0; i < filterDTO.getType().length; i++) {
				query += "p.type = :type" + i;
				parameters.put("type" + i, filterDTO.getType()[i]);
				if (i < filterDTO.getType().length - 1) {
					query += " OR ";
				}
			}
			query += ") ";
		}
		if (filterDTO.getStartDate() != null) {
			query += "AND p.timestamp >= :startDate ";
			parameters.put("startDate", filterDTO.getStartDate());
		}
		if (filterDTO.getEndDate() != null) {
			query += "AND p.timestamp <= :endDate ";
			parameters.put("endDate", filterDTO.getEndDate());
		}
		if (filterDTO.getUserLat() != null && filterDTO.getUserLot() != null && filterDTO.getRange() > 0) {
			double distance = filterDTO.getRange() / 111.0; // km를 위도 차이로 변환

			double minLat = filterDTO.getUserLat() - distance;
			double maxLat = filterDTO.getUserLat() + distance;
			double minLot = filterDTO.getUserLot() - (distance / (Math.cos(Math.toRadians(filterDTO.getUserLat()))));
			double maxLot = filterDTO.getUserLot() + (distance / (Math.cos(Math.toRadians(filterDTO.getUserLat()))));

			query += "AND p.lat >= :minLat AND p.lat <= :maxLat AND p.lot >= :minLot AND p.lot <= :maxLot ";
			parameters.put("minLat", minLat);
			parameters.put("maxLat", maxLat);
			parameters.put("minLot", minLot);
			parameters.put("maxLot", maxLot);

		}

		TypedQuery<Post> typedQuery = em.createQuery(query, Post.class);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			typedQuery.setParameter(entry.getKey(), entry.getValue());
		}

		System.out.println(typedQuery.getResultList() != null);
		return typedQuery.getResultList();
	}
}
