package com.mycompany.dto.pagination;

import java.util.List;

public class PaginationResponse<T> extends Pagination {

    private Long total;
    private List<T> content;

    public PaginationResponse(Integer page, Long total, List<T> content) {
        super.setPage(page);
        this.total = total;
        this.content = content;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
