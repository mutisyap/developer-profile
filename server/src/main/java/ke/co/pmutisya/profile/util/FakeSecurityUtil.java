package ke.co.pmutisya.profile.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Pretends to offer security
 */
public class FakeSecurityUtil {
    private static final Map<String, String> authorSecurityCodes = new HashMap<>();

    static {
        authorSecurityCodes.put("f40e8cf57ced", "Brian Kitunda");
        authorSecurityCodes.put("2b4a3fda7bc4", "Peter Mutisya");
    }

    public static Optional<String> resolveAuthor(String authorCode) {
        return Optional.ofNullable(authorSecurityCodes.get(authorCode));
    }
}
