package com.nkana.app.network.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vapalanisamy on 11/29/2015.
 */
public class RetriveProfileResponse {

    @SerializedName("Category")

    private String Category;
    @SerializedName("username")

    private String username;
    @SerializedName("Full Name")

    private String FullName;
    @SerializedName("Name of the Insitution")

    private String NameOfTheInsitution;
    @SerializedName("DOB")

    private String DOB;
    @SerializedName("Gender")

    private String Gender;
    @SerializedName("Email")

    private String Email;
    @SerializedName("Phone")

    private String Phone;
    @SerializedName("Contribution Hours")

    private String ContributionHours;
    @SerializedName("Address")

    private String Address;
    @SerializedName("Class")

    private String Class;

    /**
     *
     * @return
     * The Category
     */
    public String getCategory() {
        return Category;
    }

    /**
     *
     * @param Category
     * The Category
     */
    public void setCategory(String Category) {
        this.Category = Category;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The FullName
     */
    public String getFullName() {
        return FullName;
    }

    /**
     *
     * @param FullName
     * The Full Name
     */
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    /**
     *
     * @return
     * The NameOfTheInsitution
     */
    public String getNameOfTheInsitution() {
        return NameOfTheInsitution;
    }

    /**
     *
     * @param NameOfTheInsitution
     * The Name of the Insitution
     */
    public void setNameOfTheInsitution(String NameOfTheInsitution) {
        this.NameOfTheInsitution = NameOfTheInsitution;
    }

    /**
     *
     * @return
     * The DOB
     */
    public String getDOB() {
        return DOB;
    }

    /**
     *
     * @param DOB
     * The DOB
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
     *
     * @return
     * The Gender
     */
    public String getGender() {
        return Gender;
    }

    /**
     *
     * @param Gender
     * The Gender
     */
    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    /**
     *
     * @return
     * The Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param Email
     * The Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     *
     * @return
     * The Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     *
     * @param Phone
     * The Phone
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    /**
     *
     * @return
     * The ContributionHours
     */
    public String getContributionHours() {
        return ContributionHours;
    }

    /**
     *
     * @param ContributionHours
     * The Contribution Hours
     */
    public void setContributionHours(String ContributionHours) {
        this.ContributionHours = ContributionHours;
    }

    /**
     *
     * @return
     * The Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     *
     * @param Address
     * The Address
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     *
     * @return
     * The Class
     */
    public String getClass_() {
        return Class;
    }

    /**
     *
     * @param Class
     * The Class
     */
    public void setClass_(String Class) {
        this.Class = Class;
    }

}
