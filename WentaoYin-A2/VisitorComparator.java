import java.util.Comparator;

// Implement the Comparator interface and sort by "members first + age in descending order"
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 1. Sort by membership status
        int memberCompare = Boolean.compare(v2.isMember(), v1.isMember());
        if (memberCompare != 0) {
            return memberCompare;
        }
        // If the membership status is the same, sort in descending order of age.
        return Integer.compare(v2.getAge(), v1.getAge());
    }
}