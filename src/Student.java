public class Student {
    private String name;
    private double note;
    public Student(double note, String name) {
        this.note = note;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public double getNote() {
        return note;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNote(double note) {
        this.note = note;
    }
}
