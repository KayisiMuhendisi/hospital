public class Task {
    private int id;
    private int nurseId;
    private String taskDescription;
    private String assignedDate;

    public Task(int id, int nurseId, String taskDescription, String assignedDate) {
        this.id = id;
        this.nurseId = nurseId;
        this.taskDescription = taskDescription;
        this.assignedDate = assignedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    @Override
    public String toString() {
        return "Görev ID: " + id + "\nHemşire ID: " + nurseId + "\nAçıklama: " + taskDescription + "\nAtanma Tarihi: " + assignedDate;
    }
}
