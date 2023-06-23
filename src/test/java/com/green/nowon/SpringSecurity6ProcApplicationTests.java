package com.green.nowon;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.CategoryEntityRepository;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.ItemEntityRepository;
import com.green.nowon.domain.entity.MyUserEntity;
import com.green.nowon.domain.entity.MyUserEntityRepository;
import com.green.nowon.security.MyRole;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class SpringSecurity6ProcApplicationTests {

	@Autowired
	private MyUserEntityRepository dao;
	@Autowired
	private  PasswordEncoder pe;
	
	//@Test
	void admin계정생성() {
		
		dao.save(MyUserEntity.builder()
				.email("admin").pass(pe.encode("1234"))
				.nickName("관리자")
				.build()
				.addRole(MyRole.USER)
				.addRole(MyRole.SELLER)
				.addRole(MyRole.ADMIN)
				);
	}
	
	@Autowired
	CategoryEntityRepository cateRepo;
	
	String[] fc= {"가구","도서","디지털/가전","식품","출산/육아","패션의류"};
	String[][] sc= {
		{"DIY자재/용품","거실가구","서재/사무용가구"},
		{"가정/요리","건강/취미","경제/경영"},
		{"PC","PC부품","노트북","모니터","생활가전","휴대폰"},
		{"과자/베이커리","김치","농산물","수산물","축산물"},
		{"유아동의류","유모차","이유식"},
		{"남성의류","여성의류","남성언더웨어/잠옷","여성언더웨어/잠옷"}
	};
	
	//@Test
	void 가구카테고리() {
		for (int i=0; i<fc.length; i++) {
			for(int j=0; j<sc[i].length; j++) {
				cateRepo.save(CategoryEntity.builder()
						.name(sc[i][j])
						.parent(cateRepo.findByName(fc[i]).orElseThrow())
						.build());
			}
		}
	}
	void 카테고리2차() {
		List<String[]> list=Arrays.asList(sc);
		list.forEach(sArray->{
			int i=list.indexOf(sArray);
			CategoryEntity parent=cateRepo.findByName(fc[i]).orElseThrow();
			
			Arrays.asList(sArray).forEach(name->{
				cateRepo.save(CategoryEntity.builder()
						.name(name)
						.parent(parent)
						.build());
			});
		});
	}
	String[] fruitArr= {"감","감귤","딸기","레드향","레몬","망고","매실","멜론","무화과","바나나","배","복분자","복숭아"
			,"블루베리"};
	String[] farmArr= {"건과류","견과류","과일","쌀","잡곡/혼합곡","채소"};
	String[] arr1= {"TV거실장","소파","장식장","테이블"};
	String[] arr2= {"거실테이블","사이드테이블","접이식테이블"};
	//@Test
	void 하위카테고리_입력테스트() {
		String pName="테이블";
		String[] elements=arr2;
		CategoryEntity parent=cateRepo.findByName(pName).orElseThrow();
		for(String name:elements) {
			cateRepo.save(CategoryEntity.builder()
					.name(name)
					.parent(parent)
					.build());
		}
	}
	
	//@Test
	void 카테1입력() {
		Arrays.asList(fc).forEach(name->{
			cateRepo.save(CategoryEntity.builder()
					.name(name)
					.build());
		});
	}
	
	//@Test
	void 카테고리입력테스트() {
		
		cateRepo.save(CategoryEntity.builder()
				.name("축산물")
				.build());
		log.debug(">>>카테고리입력성공");
	}
	
	@Autowired
	ItemEntityRepository itemRepo;
	//@Test
	void 카테고리적용_상품등록테스트(){
		itemRepo.save(ItemEntity.builder()
				.title("농산물상품테스트")
				.price(1000)
				.stock(100)
				.content("무농약 수박입니다.")
				.build().addCategory(cateRepo.findByNameAndParentIsNull("농산물").orElseThrow())
				);
		log.debug("상품+카테고리 등록");
	}
	
	//@Test
	void 카테고리적용_상품등록테스트2(){
		itemRepo.save(ItemEntity.builder()
				.title("맛있는참외")
				.price(1000)
				.stock(100)
				.content("무농약 참외입니다.")
				.build().addCategory(cateRepo.save(CategoryEntity.builder().name("참외")
												.parent(cateRepo.findByName("농산물").orElseThrow())
												.build()))
				);
		log.debug("상품+카테고리 등록");
	}
	
	//@Test
	void 삭제테스트() {
		long no=1007;
		itemRepo.deleteById(no);
		log.debug("상품 삭제: "+no);
		//long a=10;
		//Long b=Long.valueOf(10);
	}

}
