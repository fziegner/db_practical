package api;

import org.hibernate.Session;

import model.TagClass;

public class StatisticImpl implements StatisticAPI {

	public StatisticImpl() {
	}
	
	@Override
	public void getTagClassHierarchy(Session session) {
		session.get(TagClass.class, 0);
		TagClass rootTagClass = session.get(TagClass.class, 0);
		System.out.println("0 " + rootTagClass.getTagClassName());
		printHierarchy("0", rootTagClass);
	}
	
	private void printHierarchy(String level, TagClass rootTagClass) {
		int i = 1;
		for(TagClass tc : rootTagClass.getChildTagClass()) {
			if(!tc.getChildTagClass().isEmpty()) {
				System.out.println(level + "." + i + " " + tc.getTagClassName());
				printHierarchy(level + "." + i, tc);
			} else {
			System.out.println(level + "." + i + " " + tc.getTagClassName());
			}
			i++;
		}
	}
}