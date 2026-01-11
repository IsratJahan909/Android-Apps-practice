package com.israt.employeeproject.entity;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int age;
    private double salary;
    private int active; // 1 for true, 0 for false

    private long joiningDate;
    private String department;
    private String skills;
    private String profileImagePath;


    public Employee() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public int getActive() {
        return active;
    }

    public long getJoiningDate() {
        return joiningDate;
    }

    public String getDepartment() {
        return department;
    }

    public String getSkills() {
        return skills;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setJoiningDate(long joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }



    public Employee(int id, String name,
                    String email,
                    String phone,
                    int age,
                    double salary,
                    int active,
                    long joiningDate,
                    String department,
                    String skills,
                    String profileImagePath) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.salary = salary;
        this.active = active;
        this.joiningDate = joiningDate;
        this.department = department;
        this.skills = skills;
        this.profileImagePath = profileImagePath;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", active=" + active +
                ", joiningDate=" + joiningDate +
                ", department='" + department + '\'' +
                ", skills='" + skills + '\'' +
                ", profileImagePath='" + profileImagePath + '\'' +
                '}';
    }

}

