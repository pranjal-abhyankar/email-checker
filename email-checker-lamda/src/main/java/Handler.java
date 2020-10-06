import com.amazonaws.services.lambda.runtime.Context;
import java.util.HashSet;

public class Handler {

    public int handleRequest(String[] emails, Context context) {
        return numUniqueEmails(emails);
    }

    public int numUniqueEmails(String[] emails) {
        HashSet<String> hashSet = new HashSet();
        for(String email : emails) {
            String emailSplit[] = email.split("@");
            String localName = emailSplit[0];
            String domainName = emailSplit[1];
            localName = removePeriods(localName);
            localName = ignorePlus(localName);
            String key = localName + "@" + domainName;
            hashSet.add(key);
        }
        return hashSet.size();
    }
    public String removePeriods(String localName) {
        return localName.replaceAll("\\.", "");
    }

    public String ignorePlus(String localName) {
        return localName.contains("+") ? localName.substring(0, localName.indexOf('+')) : localName;
    }
}
