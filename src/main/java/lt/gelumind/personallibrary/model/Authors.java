package lt.gelumind.personallibrary.model;

import java.util.ArrayList;
import java.util.List;

public class Authors {

    private List<Author> authorList;

    public List<Author> getAuthorList() {
        if (authorList == null) {
            authorList = new ArrayList<>();
        }
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
