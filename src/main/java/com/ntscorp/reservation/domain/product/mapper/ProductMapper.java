package com.ntscorp.reservation.domain.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ntscorp.reservation.domain.product.dto.Product;
import com.ntscorp.reservation.domain.product.dto.ProductImage;
import com.ntscorp.reservation.domain.product.dto.ProductPrice;

@Repository
public interface ProductMapper {
	List<Product> selectProductAllCategory(@Param("start") int start, @Param("limit") int limit);
	
	List<Product> selectProductByCategoryId(@Param("categoryId") int categoryId, @Param("start") int start, @Param("limit") int limit);
	
	int selectProductCountAll();
	
	int selectProductCountByCategoryId(int categoryId);
	
	List<ProductImage> selectProductImageListByProductId(@Param("productId") int productId, @Param("limit") int limit);
	
	List<ProductPrice> selectProductPriceListByProductId(int productId);
}
