package longtimenosee.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.util.StringUtil;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.AssignedPolicy;

/**
 * Tests that a {@code AssignedPolicy}'s {@code Policy} {@code Title} for a given {@code Person} matches any of the
 * keywords given.
 */
public class PolicyTitleContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs a PolicyTitleContainsKeywordsPredicate object, which consists of a keywords input.
     *
     * @param keywords is the keywords input by the user to be compared.
     */
    public PolicyTitleContainsKeywordsPredicate(List<String> keywords) {
        assert !keywords.isEmpty();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        for (String keyword : keywords) {
            for (AssignedPolicy assignedPolicy : person.getAssignedPolicies()) {
                if (StringUtil.containsWordIgnoreCase(assignedPolicy.getPolicy().getTitle().fullTitle, keyword)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof PolicyTitleContainsKeywordsPredicate) {
                return keywords.equals(((PolicyTitleContainsKeywordsPredicate) other).keywords);
            } else {
                return false;
            }
        }
    }
}