
package com.recipewelldone.parser.jsonParserReceipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Binding {

    @SerializedName("hasDishName")
    @Expose
    private HasDishName hasDishName;
    @SerializedName("hasCookingTime")
    @Expose
    private HasCookingTime hasCookingTime;
    @SerializedName("hasflavourProfile")
    @Expose
    private HasflavourProfile hasflavourProfile;
    @SerializedName("isRatedAs")
    @Expose
    private IsRatedAs isRatedAs;
    @SerializedName("belongsToCourse")
    @Expose
    private BelongsToCourse belongsToCourse;
    @SerializedName("belongsTo")
    @Expose
    private BelongsTo belongsTo;
    @SerializedName("isServedIn")
    @Expose
    private IsServedIn isServedIn;
    @SerializedName("hasInstructions")
    @Expose
    private HasInstructions hasInstructions;
    @SerializedName("url")
    @Expose
    private Url url;

    public HasDishName getHasDishName() {
        return hasDishName;
    }

    public void setHasDishName(HasDishName hasDishName) {
        this.hasDishName = hasDishName;
    }

    public HasCookingTime getHasCookingTime() {
        return hasCookingTime;
    }

    public void setHasCookingTime(HasCookingTime hasCookingTime) {
        this.hasCookingTime = hasCookingTime;
    }

    public HasflavourProfile getHasflavourProfile() {
        return hasflavourProfile;
    }

    public void setHasflavourProfile(HasflavourProfile hasflavourProfile) {
        this.hasflavourProfile = hasflavourProfile;
    }

    public IsRatedAs getIsRatedAs() {
        return isRatedAs;
    }

    public void setIsRatedAs(IsRatedAs isRatedAs) {
        this.isRatedAs = isRatedAs;
    }

    public BelongsToCourse getBelongsToCourse() {
        return belongsToCourse;
    }

    public void setBelongsToCourse(BelongsToCourse belongsToCourse) {
        this.belongsToCourse = belongsToCourse;
    }

    public BelongsTo getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(BelongsTo belongsTo) {
        this.belongsTo = belongsTo;
    }

    public IsServedIn getIsServedIn() {
        return isServedIn;
    }

    public void setIsServedIn(IsServedIn isServedIn) {
        this.isServedIn = isServedIn;
    }

    public HasInstructions getHasInstructions() {
        return hasInstructions;
    }

    public void setHasInstructions(HasInstructions hasInstructions) {
        this.hasInstructions = hasInstructions;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

}
