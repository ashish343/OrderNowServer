package utility;

import java.util.UUID;

public class UniqueIdGenerator {
	public static String generateId() {
		return UUID.randomUUID().toString();
	}
}
