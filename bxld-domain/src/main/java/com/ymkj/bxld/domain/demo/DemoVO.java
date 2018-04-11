package com.ymkj.bxld.domain.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymkj.base.core.biz.entity.BaseEntity;

public class DemoVO extends BaseEntity {

    private static final long serialVersionUID = -1148720890228191182L;

    private Long id;
    
    private String name;
    
    private int pageNum; //当前页
    
    private int pageSize; //每页条数
    
    public Long getId() {
		return id;
	}
    public void setId(Long id) {
		this.id = id;
	}
    @JsonIgnore
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	@JsonIgnore
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
