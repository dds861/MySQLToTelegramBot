package Presenter;

public class UsersAllWords {
    //Chat Variables
    private String eventTime;
    private String message;
    private String lastName;

    //Chat contructer
    public UsersAllWords(String eventTime, String message, String lastName) {
        this.eventTime      = eventTime;
        this.message        = message;
        this.lastName       = lastName;
    }

    //Getters and Setters

    public String getEventTime() {
        return eventTime;
    }

    public String getMessage() {
        return message;
    }

    public String getLastName() {
        return lastName;
    }

}
