package com.zsgc.core.bo.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.zsgc.core.bo.CommentProjectBO;
import com.zsgc.core.bo.MsgBO;
import com.zsgc.core.dao.UserExtendDAO;
import com.zsgc.core.dao.WarehouseProjectDAO;
import com.zsgc.core.model.UserExtend;
import com.zsgc.core.model.WarehouseProjects;

@Service("commentProjectBO")
public class DefaultCommentProjectBO implements CommentProjectBO {

	@Autowired
	private UserExtendDAO userExtendDAO;

	@Autowired
	private WarehouseProjectDAO warehouseProjectDao;

	@Autowired
	private MsgBO msgBO;

	/* 进入评论界面 */
	@Override
	public Map<String, Object> openComment(UserExtend ue) {
		Map<String, Object> map = new HashMap<>();
		UserExtend selByNowPId = userExtendDAO.selByNowPId(ue);
		if (selByNowPId==null) {
			selByNowPId = new UserExtend();
		}
		Integer isScore = selByNowPId.getIsScore();
		if (isScore == null || isScore == 0) {
			map.put("ref", "0");
		} else if (isScore == 1) {
			map.put("ref", "1");
			map.put("firScore", selByNowPId.getIsScore());
			map.put("content", selByNowPId.getContent());
		} else if (isScore == 2) {
			map.put("ref", "2");
			map.put("score", selByNowPId.getIsScore());
			map.put("content", selByNowPId.getContent());
		}
		return map;
	}

	/* 项目评论 */
	@Override
	@Transactional
	public int commentProject(UserExtend ue, double score, String content) {
		UserExtend userExtend = userExtendDAO.selByNowPId(ue);
		int index = 0;
		int index_up = 0;
		try {
			if (userExtend == null) {
				ue.setpId(ue.getNowPId());
				ue.setIsCollection(null);
				index++;
				index_up += userExtendDAO.insertByCollection(ue);
				userExtend = ue;
			}
			if (userExtend.getIsScore() != null && userExtend.getIsScore() > 1) {
				return 2;
			}

			List<WarehouseProjects> list = new ArrayList<>();
			WarehouseProjects wp = new WarehouseProjects();
			wp.setpId(userExtend.getpId());
			list.add(wp);
			WarehouseProjects project = warehouseProjectDao.selByPids(list).get(0);
			Integer commentNum = project.getCommentNum();
			if (commentNum == null) {
				commentNum = 0;
			}
			if (userExtend.getIsScore() == null || userExtend.getIsScore() == 0) {
				commentNum++;
			}
			BigDecimal score2 = project.getScore();
			BigDecimal multiply = null;
			BigDecimal add = null;
			BigDecimal new_score = null;
			if (userExtend.getIsScore() == null || userExtend.getIsScore() == 0) {
				if (score2 == null) {
					score2 = new BigDecimal(0);
				}
				multiply = score2.multiply(new BigDecimal(commentNum - 1));
				add = new BigDecimal(score).add(multiply);
				new_score = add.divide(new BigDecimal(commentNum), 1, RoundingMode.HALF_UP);
				userExtend.setIsScore(1);
				wp.setCommentNum(commentNum);
				msgBO.commentProjectsMsg(project, ue.getuId(), 1, score, new_score.doubleValue());

			} else if (userExtend.getIsScore() == 1) {
				multiply = score2.multiply(new BigDecimal(commentNum));
				BigDecimal subtract = multiply.subtract(userExtend.getScore());
				add = subtract.add(new BigDecimal(score));
				new_score = add.divide(new BigDecimal(commentNum), 1, RoundingMode.HALF_UP);
				userExtend.setIsScore(2);
				msgBO.commentProjectsMsg(project, ue.getuId(), 2, score, new_score.doubleValue());
			}
			userExtend.setContent(content);
			userExtend.setScore(new BigDecimal(score));
			wp.setScore(new_score);
			index_up += warehouseProjectDao.updateByPid(wp);
			index_up += userExtendDAO.updateByPrimaryKey(userExtend);
			index += 2;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			index = -1;
		}
		if (index == index_up) {
			return 1;
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}
}
