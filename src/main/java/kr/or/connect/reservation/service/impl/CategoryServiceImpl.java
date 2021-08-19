package kr.or.connect.reservation.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryDao;
	
	// 카테고리 목록 가져오기
	@Override
	public List<Category> getCategories() {
		List<Category> categories = categoryDao.getAll();
		return categories;
	}

	// 카테고리 삭제하기
	@Override
	public int deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		return categoryDao.getSize();
	}

}
