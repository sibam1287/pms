package com.pms.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
        private Long id;
        private String productName;
        private String description;
        private String imageUrl;
        private String price;
        private String category;
        private String uploadedBy;

        private String deletedBy;
        private String flagDeleted;
        private String loggedUserEmail;
        private String loggedUserId;
        private String loggedUserType;


    public void trim() {
        this.productName = safeTrim(this.productName);
        this.description = safeTrim(this.description);
        this.imageUrl = safeTrim(this.imageUrl);
        this.price = safeTrim(this.price);
        this.category = safeTrim(this.category);
        this.uploadedBy = safeTrim(this.uploadedBy);
        this.deletedBy = safeTrim(this.deletedBy);
        this.flagDeleted = safeTrim(this.flagDeleted);
        this.loggedUserEmail = safeTrim(this.loggedUserEmail);
        this.loggedUserId = safeTrim(this.loggedUserId);
        this.loggedUserType = safeTrim(this.loggedUserType);
    }


    private String safeTrim(String value) {
        return (value == null) ? null : value.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(String flagDeleted) {
        this.flagDeleted = flagDeleted;
    }

    public String getLoggedUserEmail() {
        return loggedUserEmail;
    }

    public void setLoggedUserEmail(String loggedUserEmail) {
        this.loggedUserEmail = loggedUserEmail;
    }

    public String getLoggedUserId() {
        return loggedUserId;
    }

    public void setLoggedUserId(String loggedUserId) {
        this.loggedUserId = loggedUserId;
    }

    public String getLoggedUserType() {
        return loggedUserType;
    }

    public void setLoggedUserType(String loggedUserType) {
        this.loggedUserType = loggedUserType;
    }
}
