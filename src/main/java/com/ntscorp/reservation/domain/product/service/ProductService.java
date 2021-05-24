package com.ntscorp.reservation.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ntscorp.reservation.domain.product.dto.Product;
import com.ntscorp.reservation.domain.product.dto.ProductImage;
import com.ntscorp.reservation.domain.product.dto.ProductPrice;
import com.ntscorp.reservation.domain.product.mapper.ProductMapper;

@Service
public class ProductService {
	private static final int ALL_CATEGORY_ID = 0;
	
	private static final int ITEM_COUNT = 4;
	private static final int IMAGE_COUNT = 4;

	private final ProductMapper productMapper;
	
	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	public List<Product> getProductList(int categoryId, int start) {
		if (categoryId == ALL_CATEGORY_ID) {
			return productMapper.selectProductAllCategory(start, ITEM_COUNT);
		}

		return productMapper.selectProductByCategoryId(categoryId, start, ITEM_COUNT);
	}

	public int getProductCount(int categoryId) {
		if (categoryId == ALL_CATEGORY_ID) {
			return productMapper.selectProductCountAll();
		}

		return productMapper.selectProductCountByCategoryId(categoryId);
	}

	public List<ProductImage> getProductImageList(int productId) {
		return productMapper.selectProductImageListByProductId(productId, IMAGE_COUNT);
	}

	public List<ProductPrice> getProductPriceList(int productId) {
		return productMapper.selectProductPriceListByProductId(productId);
	}
}
