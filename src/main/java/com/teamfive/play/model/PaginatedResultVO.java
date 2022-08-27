package com.teamfive.play.model;

import java.io.Serializable;
import java.util.List;

public class PaginatedResultVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<T> data;
    private Integer count;

    /**
     * default constructor.
     */
    public PaginatedResultVO() {

    }

    /**
     * Construct the object by List and total number of records.
     *
     * @param data  List of objects
     * @param count Objects Count
     */
    public PaginatedResultVO(List<T> data, Integer count) {
        this.data = data;
        this.count = count;
    }

    /**
     * To get Paginated Data.
     *
     * @return data.
     */
    public List<T> getData() {
        return data;
    }

    /**
     * To set Paginated Data.
     *
     * @param data data.
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * To get count.
     *
     * @return results count.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * TO set the count.
     *
     * @param count count.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}

