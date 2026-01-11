package com.israt.employeecrud;

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

    public Employee() {}

    public Employee(int id, String name, String email, String phone, int age, double salary, int active, long joiningDate, String department, String skills, String profileImagePath) {
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

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public int getActive() { return active; }
    public void setActive(int active) { this.active = active; }
    public long getJoiningDate() { return joiningDate; }
    public void setJoiningDate(long joiningDate) { this.joiningDate = joiningDate; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public String getProfileImagePath() { return profileImagePath; }
    public void setProfileImagePath(String profileImagePath) { this.profileImagePath = profileImagePath; }
}
