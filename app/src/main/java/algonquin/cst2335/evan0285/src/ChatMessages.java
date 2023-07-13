package algonquin.cst2335.evan0285.src;

public class ChatMessages {

    private String message;
    private String timeSent;
    private boolean isSentButton;

    public ChatMessages(String m, String t, boolean sent){
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage(){
        return message;
    }
    public String getTimeSent(){
        return timeSent;
    }
    public boolean isSentButton(){
        return isSentButton;
    }

}
