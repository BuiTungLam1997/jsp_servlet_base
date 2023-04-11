package com.jsp_servlet_base_paging;

import javax.persistence.criteria.CriteriaBuilder;

public interface Pgaeble {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
}
