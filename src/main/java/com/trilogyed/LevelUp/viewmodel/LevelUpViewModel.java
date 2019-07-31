package com.trilogyed.LevelUp.viewmodel;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class LevelUpViewModel {

    private int id;
    @NotNull(message = "Please enter a Customer ID")
    private int customerId;
    @NotNull(message = "Please enter the number of Points.")
    private int points;
    @NotNull(message = "Please enter the member Date.")
    private LocalDate memberDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDate getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(LocalDate memberDate) {
        this.memberDate = memberDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelUpViewModel that = (LevelUpViewModel) o;
        return id == that.id &&
                customerId == that.customerId &&
                points == that.points &&
                memberDate.equals(that.memberDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, points, memberDate);
    }
}
