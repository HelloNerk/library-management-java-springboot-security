package com.library.platform.upc.library.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public record BookPublishedDate(Date publishedDate) {

    public BookPublishedDate {
        if (publishedDate == null) {
            throw new IllegalArgumentException("publishedDate is required");
        }

        if(publishedDate.after(new Date())) {
            throw new IllegalArgumentException("publishedDate cannot be in the future");
        }
    }

    public Date getBookPublishedDate() {
        return publishedDate;
    }
}
