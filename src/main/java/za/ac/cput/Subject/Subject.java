package za.ac.cput.Subject;

public class Subject {
    
    private String subjectCode;
    private String Description;
    
    public Subject(){
    }

    public Subject(String subjectCode, String Description) {
        this.subjectCode = subjectCode;
        this.Description = Description;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
