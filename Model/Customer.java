import java.util.ArrayList;

public class Customer extends User {
    //instance data members
    private ArrayList<String> commentsGivenByCustomer;

    //constructor
    public Customer(String password, String email, String username) {
        super(password, email, username);
        commentsGivenByCustomer = new ArrayList<String>();
    }

    //getters
    public ArrayList<String> getCommentsGivenByCustomer() {
        return commentsGivenByCustomer;
    }

    //setters
    public void setCommentsGivenByCustomer(ArrayList<String> commentsGivenByCustomer) {
        this.commentsGivenByCustomer = commentsGivenByCustomer;
    }

    //add the comment made by the customer to the list of comments given by this customer
    public void addCommentToCustCommentList(String comment) {
        commentsGivenByCustomer.add(comment);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
