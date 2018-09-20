package enumtest;

import java.util.HashMap;
import java.util.Map;

public class TagTest {
	
	public static final Map<TagEnum, TagEnum> tagMap = new HashMap<TagEnum, TagEnum>();
	
	static {
			tagMap.put(TagEnum.ET, TagEnum.ED);
			tagMap.put(TagEnum.GT, TagEnum.GC);
	};
	

	
	public static void main(String[] args ) {
		System.out.println(TagEnum.GT.tag());
		String testString = "this is test for ET GT";
		for (TagEnum key: tagMap.keySet()) {
			testString = testString.replace(key.tag(), tagMap.get(key).tag());
		}
		System.out.println(testString);
		
	}
}
