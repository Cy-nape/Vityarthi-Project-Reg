package domain;

public abstract class Person {
    private String id;
    private String regNo;
    private String fullName;

    public Person(String id, String regNo, String fullName) {
        this.id = id;
        this.regNo = regNo;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", RegNo: " + regNo + ", Name: " + fullName;
    }
}