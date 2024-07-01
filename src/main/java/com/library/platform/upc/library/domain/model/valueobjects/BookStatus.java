package com.library.platform.upc.library.domain.model.valueobjects;

public record BookStatus(String status) {

    public BookStatus {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status is required");
        }
        if(status.length()>10){
            throw new IllegalArgumentException("status is too long");
        }
        if(!status.equals("AVAILABLE") && !status.equals("BORROWED")){
            throw new IllegalArgumentException("status is invalid");
        }
    }

    public String getBookStatus(){
        return status;
    }


}
