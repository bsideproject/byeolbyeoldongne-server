package com.chdlsp.datapractice.domain.interfaces.spec;

public interface SearchQueryParameter {
    String getQuery();
    String getCategoryGroupCode();
    Double getX();
    Double getY();
    Integer getRadius();
    String getRect();
    Integer getPage();
    Integer getSize();
}
