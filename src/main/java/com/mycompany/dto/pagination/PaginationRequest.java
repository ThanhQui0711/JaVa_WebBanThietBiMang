package com.mycompany.dto.pagination;

public class PaginationRequest<T> extends Pagination {

    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
