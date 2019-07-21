package com.ca.sample.dto;

import java.io.Serializable;

public class ErrorMessageDto implements Serializable {

    private static final long serialVersionUID = 5762266029357413750L;

    private String status;

    public ErrorMessageDto(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
