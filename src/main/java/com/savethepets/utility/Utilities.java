package com.savethepets.utility;

import java.time.LocalDateTime;
import java.util.List;

import com.savethepets.dto.AlarmInfoDTO;
import com.savethepets.dto.MyCommentInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.dto.UserInfoDTO;
import com.savethepets.entity.Alarm;
import com.savethepets.entity.Bookmark;
import com.savethepets.entity.Report;
import com.savethepets.id.BookmarkId;
import com.savethepets.id.ReportId;
import com.savethepets.service.AlarmService;
import com.savethepets.service.BookmarkService;
import com.savethepets.service.ReportService;
import com.savethepets.service.UserService;

public class Utilities {
	public static String verifiy(String token)
	{
		// not null && not expired && user exist in DB
		if(token != null)
			//token이 아닌 UserId 반환 => 테스트에서는 token을 반환 하는걸로 
			return token;
		else
			return null;
	}
	
	public static void testGetUserInfo(UserService userService, String userId)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		System.out.println("------------------------------");
		System.out.println("■ 유저 정보 검색, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t결과 : 성공");
			System.out.println("\t - 아이디 : " + temp.getUserId());
			System.out.println("\t - 닉네임 : " + temp.getNickname());
			System.out.println("\t - 프로필사진 : " + temp.getPicture());
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
	
	public static void testUpdateNickname(UserService userService, String userId, String changeNickname)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		
		System.out.println("------------------------------");
		System.out.println("■ 유저 닉네임 변경, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t유저 존재 여부 : 성공");
			System.out.println("\t - 변경전 닉네임 : " + temp.getNickname());
			if(userService.updateNickname(userId, changeNickname)==true)
			{
				System.out.println("\t결과 : 성공");
				temp = userService.getUserInfo(userId);
				System.out.println("\t - 변경후 닉네임 : " + temp.getNickname());
			}
			else
				System.out.println("\t결과 : 실패(중복된 닉네임)");
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
	
	public static void testUpdatePicture(UserService userService, String userId, byte[] changePicture)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		
		System.out.println("------------------------------");
		System.out.println("■ 유저 프로필사진 변경, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t유저 존재 여부 : 성공");
			System.out.println("\t - 변경전 프로필사진 : " + temp.getPicture());
			if(userService.updatePicture(userId, changePicture)==true)
			{
				System.out.println("\t결과 : 성공");
				temp = userService.getUserInfo(userId);
				System.out.println("\t - 변경후 프로필사진 : " + temp.getPicture());
			}
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
	
	public static void testGetMyPosts(UserService userService, String userId)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		List<PostInfoDTO> postDTOs;
		System.out.println("------------------------------");
		System.out.println("■ 유저 작성한 게시물 보기, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t유저 존재 여부 : 성공");
			if((postDTOs = userService.getMyPosts(userId))!=null)
			{
				System.out.println("\t결과 : 조회 성공");
				System.out.println("\t작성한 게시물 정보");
				System.out.println("\t\t------------------------------");
				for(PostInfoDTO i : postDTOs)
				{
					System.out.println("\t\t포스트 아이디 : " + i.getPostId());
					System.out.println("\t\t포스트 작성시간 : " + i.getTimestamp());
					System.out.println("\t\t포스트 위도 : " + i.getPostLat());
					System.out.println("\t\t포스트 경도 : " + i.getPostLot());
					System.out.println("\t\t포스트 타입 : " + getPostType(i.getType()));
					System.out.println("\t\t포스트 종 : " + i.getSpecies());
					System.out.println("\t\t포스트 품종 : " + i.getBreed());
					System.out.println("\t\t포스트 썸네일 : " + i.getPicture());
					System.out.println("\t\t------------------------------");
				}
			}
			else
				System.out.println("\t결과 : 조회 성공 (작성한 게시물이 없음)");
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
	
	public static void testGetMyComments(UserService userService, String userId)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		List<MyCommentInfoDTO> commentDTOs;
		System.out.println("------------------------------");
		System.out.println("■ 유저 작성한 댓글 보기, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t유저 존재 여부 : 성공");
			if((commentDTOs = userService.getMyComments(userId))!=null)
			{
				System.out.println("\t결과 : 조회 성공");
				System.out.println("\t작성한 댓글 정보");
				System.out.println("\t\t------------------------------");
				for(MyCommentInfoDTO i : commentDTOs)
				{
					System.out.println("\t\t댓글 작성시간 : " + i.getTimestamp());
					System.out.println("\t\t댓글 내용 : " + i.getContent());
					System.out.println("\t\t포스트 아이디 : " + i.getPostId());
					System.out.println("\t\t포스트 작성시간 : " + i.getTime());
					System.out.println("\t\t포스트 위도 : " + i.getPostLat());
					System.out.println("\t\t포스트 경도 : " + i.getPostLot());
					System.out.println("\t\t포스트 타입 : " + getPostType(i.getType()));
					System.out.println("\t\t포스트 종 : " + i.getSpecies());
					System.out.println("\t\t포스트 품종 : " + i.getBreed());
					System.out.println("\t\t포스트 썸네일 : " + i.getPicture());
					System.out.println("\t\t------------------------------");
				}
			}
			else
				System.out.println("\t결과 : 조회 성공 (작성한 댓글이 없음)");
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
	
	public static void testGetAlarms(UserService userService, String userId)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		List<AlarmInfoDTO> alarmDTOs;
		System.out.println("------------------------------");
		System.out.println("■ 유저 수신된 알람 보기, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t유저 존재 여부 : 성공");
			if((alarmDTOs = userService.getAlarms(userId))!=null)
			{
				System.out.println("\t결과 : 조회 성공");
				System.out.println("\t수신된 알람 정보");
				System.out.println("\t\t------------------------------");
				for(AlarmInfoDTO i : alarmDTOs)
				{
					System.out.println("\t\t알람 아이디 : " + i.getAlarmId());
					System.out.println("\t\t알람 타입 : " + getPostType(i.getType()));
					System.out.println("\t\t포스트 아이디 : " + i.getPostId());
					if(i.getType() == Alarm.COMMENT)
					{
						System.out.println("\t\t발신자 닉네임 : " + i.getNickname());
						System.out.println("\t\t발신자 프로필사진 : " + i.getPicture());
					}
					else
					{
						System.out.println("\t\t포스트 종 : " + i.getSpecies());
						System.out.println("\t\t포스트 품종 : " + i.getBreed());
						System.out.println("\t\t포스트 사진 : " + i.getPicture());
					}
					System.out.println("\t\t------------------------------");
				}
			}
			else
				System.out.println("\t결과 : 조회 성공 (작성한 알람이 없음)");
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
	public static void testGetBookmarks(UserService userService, String userId)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		List<PostInfoDTO> postDTOs;
		System.out.println("------------------------------");
		System.out.println("■ 유저 북마크한 게시물 보기, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t유저 존재 여부 : 성공");
			if((postDTOs = userService.getBookmarks(userId))!=null)
			{
				System.out.println("\t결과 : 조회 성공");
				System.out.println("\t작성한 게시물 정보");
				System.out.println("\t\t------------------------------");
				for(PostInfoDTO i : postDTOs)
				{
					System.out.println("\t\t포스트 아이디 : " + i.getPostId());
					System.out.println("\t\t포스트 작성시간 : " + i.getTimestamp());
					System.out.println("\t\t포스트 위도 : " + i.getPostLat());
					System.out.println("\t\t포스트 경도 : " + i.getPostLot());
					System.out.println("\t\t포스트 타입 : " + getPostType(i.getType()));
					System.out.println("\t\t포스트 종 : " + i.getSpecies());
					System.out.println("\t\t포스트 품종 : " + i.getBreed());
					System.out.println("\t\t포스트 썸네일 : " + i.getPicture());
					System.out.println("\t\t------------------------------");
				}
			}
			else
				System.out.println("\t결과 : 조회 성공 (작성한 게시물이 없음)");
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
	
	public static void testCreateReport(ReportService reportService, Long objectId, String reporterId, boolean type, int reportType, String reportReason)
	{
		Report temp = new Report(new ReportId(objectId, reporterId, type), reportType, reportReason);
		System.out.println("------------------------------");
		System.out.println("■ 신고 삽입하기");
		System.out.println("\t생성전 데이터");
		System.out.println("\t\t신고 대상 아이디 : "+temp.getReportId().getObjectId());
		System.out.println("\t\t신고자 아이디 : "+temp.getReportId().getReporterId());
		System.out.println("\t\t타입 : "+temp.getReportId().getType());
		System.out.println("\t\t신고 타입 : "+temp.getReportType());
		System.out.println("\t\t신고 이유 : "+temp.getReportReason());
		if(reportService.createReport(temp))
			System.out.println("\t결과 : 생성 성공");
		else
			System.out.println("\t결과 : 생성 실패");
		System.out.println("------------------------------");
	}
	public static void testCreateBookmark(BookmarkService bookmarkService, String userId, Long postId) 
	{
		Bookmark temp = new Bookmark(new BookmarkId(userId, postId), LocalDateTime.now());
		System.out.println("------------------------------");
		System.out.println("■ 북마크 삽입하기");
		System.out.println("\t생성전 데이터");
		System.out.println("\t\t신고 대상 아이디 : "+temp.getBookmarkId().getUserId());
		System.out.println("\t\t신고자 아이디 : "+temp.getBookmarkId().getPostId());
		System.out.println("\t\t타입 : "+temp.getTimestamp());

		if(bookmarkService.createBookmark(temp))
			System.out.println("\t결과 : 생성 성공");
		else
			System.out.println("\t결과 : 생성 실패");
		System.out.println("------------------------------");
	}
	
	public static void testRemoveBookmark(BookmarkService bookmarkService, String userId, Long postId) 
	{
		System.out.println("------------------------------");
		System.out.println("■ 북마크 삭제하기");
		if(bookmarkService.removeBookmark(new BookmarkId(userId, postId)))
			System.out.println("\t결과 : 삭제 성공");
		else
			System.out.println("\t결과 : 삭제 실패");
		System.out.println("------------------------------");
	}
	
	public static void testRemoveAlarm(AlarmService alarmService, Long alarmId) 
	{
		System.out.println("------------------------------");
		System.out.println("■ 알람 삭제하기");
		if(alarmService.removeAlarm(alarmId))
			System.out.println("\t결과 : 삭제 성공");
		else
			System.out.println("\t결과 : 삭제 실패");
		System.out.println("------------------------------");
	}

	public static String getPostType(int type)
	{
		switch (type) {
			case 0: return "실종";
			case 1: return "목격";
			case 2: return "보호";
			case 3: return "분양";
			case 4: return "댓글";
		}
		return null;
	}
}
