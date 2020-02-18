package pl.swpws.model;

public class User {
    enum Gender {
        MALE, FEMALE //mężczyzna, Kobieta
    }


    public User(int number, int age, String sex, String profession, int yearsEducation) {
//        this.id = new Random(1000).nextInt();
        this.number = number;
        this.id = number;
        this.age = age;
        this.sex = sex;
        this.profession = profession;
        this.yearsEducation = yearsEducation;
    }

    private int id;
    private int number; //numer
    private int age; //wiek
    private String sex; //płeć
    private String profession;//zawód
    private int yearsEducation; //lata formalnej edukacji


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getYearsEducation() {
        return yearsEducation;
    }

    public void setYearsEducation(int yearsEducation) {
        this.yearsEducation = yearsEducation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", number=" + number +
                ", age=" + age +
                ", sex=" + sex +
                ", profession='" + profession + '\'' +
                ", yearsEducation=" + yearsEducation +
                '}';
    }
}
