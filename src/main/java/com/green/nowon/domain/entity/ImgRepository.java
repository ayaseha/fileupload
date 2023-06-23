package com.green.nowon.domain.entity;

import org.springframework.data.repository.CrudRepository;

public interface ImgRepository
		extends CrudRepository<ItemImageEntity, Long>
,CustomImgRepository<ItemImageEntity>{

}
