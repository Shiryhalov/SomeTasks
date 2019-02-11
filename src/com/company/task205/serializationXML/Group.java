package com.company.task205.serializationXML;

import java.io.Serializable;
import java.util.Objects;

public class Group implements Serializable{
    private int groupNumber;
    private String direction;

    public Group() {
    }

    public Group(int groupNumber, String direction) {
        this.groupNumber = groupNumber;
        this.direction = direction;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupNumber == group.groupNumber &&
                Objects.equals(direction, group.direction);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupNumber, direction);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupNumber=" + groupNumber +
                ", direction='" + direction + '\'' +
                '}';
    }
}
