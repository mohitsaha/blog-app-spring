package com.mohit.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min=4,message="Min size is of category id is 4")
	private String categoryTitle;
	@NotBlank
	@Size(max=10,message="max size of category is 10")
	private String categoryDescription;
}
