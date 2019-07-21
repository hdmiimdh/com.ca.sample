package com.ca.sample.dto;

import java.io.Serializable;

public class DepositResponseDto implements Serializable {

    private static final long serialVersionUID = 7839110308880917541L;

    private Long id;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DepositResponseDto{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
