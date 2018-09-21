package enumtest;

import java.util.HashMap;
import java.util.Map;

public class Filter {

	public static String tag72 = ":72:";
	
	public static final Map<TagEnum, TagEnum> tagMap = new HashMap<TagEnum, TagEnum>();
	
	static {
			tagMap.put(TagEnum.ET, TagEnum.ED);
			tagMap.put(TagEnum.GT, TagEnum.GC);
	};
	
	
	public void filterOne(StringBuffer buf) {
		System.out.println("Before ==>");
		System.out.println(buf);
		
		int index72 = buf.indexOf(tag72);
	
		if (index72 == -1) {
			filterTwo(buf);
		} 
		
		int indexOfNewline = buf.indexOf("\n", index72);
		if (indexOfNewline != -1 && (buf.length() > indexOfNewline+6)) {
			String subTag = buf.substring(indexOfNewline+4, indexOfNewline+6);
			for (TagEnum key: tagMap.keySet()) {
				System.out.println(subTag);
				if (key.getTag().equals(subTag)) {
					buf.replace(indexOfNewline+4, indexOfNewline+6, tagMap.get(key).getTag());
					System.out.println(buf);
					break;
				}
			}
			//publisher send message
		} 
	}
	
	public void filterTwo(StringBuffer buffer) {
		//TODO
	}
	
	public static void main(String[] args ) {
		String testString = "{1:DATTRADEW1NXXXX000000051}{2:o1031623}{4:\n" +
				":20:1002MEX0002000002	01\n" +
				":71A:SHA\n" +
				":72:ZQA9H5360000000000162100000\n"+
				"TLXET          N          N\n" +
				"/BEN/INVOICE 1234567\n" +
				"-          \n" +
				"-}";
		
		String testString2 = "{1:DATTRADEW1NXXXX000000051}{2:o1031623}{4:\n" +
				":20:1002MEX0002000002	01\n" +
				":71A:SHA\n" +
				":72:ZQA9H5360000000000162100000\n"+
				"}";
		StringBuffer strbuf = new StringBuffer(testString2);
		Filter filter = new Filter();
		filter.filterOne(strbuf);
		System.out.println("strbuf after -->" + strbuf);
	}
}
