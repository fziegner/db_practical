package main;

import java.util.Scanner;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import api.PersonRelatedImpl;
import api.StatisticImpl;

public class Application {

	Scanner scanner = new Scanner(System.in);
	
	PersonRelatedImpl priAPI;
	StatisticImpl statAPI;
	HibernateUtil hibernate;
	Session session;

	public Application(PersonRelatedImpl priAPI, StatisticImpl statAPI, HibernateUtil hibernate, Session session) {
		this.priAPI = priAPI;
		this.statAPI = statAPI;
		this.hibernate = hibernate;
		this.session = session;
	}

	public void startApplication() {
		System.out.println("DBPraktikum - Teil 3");
		System.out.println("Geben sie ihre ID ein:");
		long personID = scanner.nextLong();
		System.out.println("Optionen für Eingabe:");
		System.out.println("0 - Exit");
		System.out.println("PersonRelatedAPI:");
		System.out.println("1 - getProfile");
		System.out.println("2 - getCommonInterestOfMyFriends");
		System.out.println("3 - getCommonFriends");
		System.out.println("4 - getPersonsWithMostCommonInterests");
		System.out.println("5 - getJobRecommendations");
		System.out.println("6 - getShortestFriendshipPath:");
		System.out.println("StatisticAPI:");
		System.out.println("7 - getTagClassHierarchy");
		System.out.println("8 - getPopularComments");
		System.out.println("9 - getMostPostingCountry");
		System.out.println("Eingabe:");
		int in = scanner.nextInt();
		
		switch(in) {
			case 0: System.exit(0); break;
			case 1: priAPI.getProfile(personID, session); break;
			case 2: priAPI.getCommonInterestOfMyFriends(personID, session); break;
			case 3: priAPI.getCommonFriends(personID, readID(), session); break;
			case 4: priAPI.getPersonsWithMostCommonInterests(personID, session); break;
			case 5: priAPI.getJobRecommendations(personID, session);; break;
			case 6: System.err.println("FUNKTION WIRD NOCH NICHT UNTERSTÜTZT!"); break;
			case 7: statAPI.getTagClassHierarchy(session); break;
			case 8: statAPI.getPopularComments(session, getMinimumLikes()); break;
			case 9: statAPI.getMostPostingCountry(session); break;
			default: System.exit(0); break;
		}
		
		System.out.println("Ende der Application");
	}
	
	public long readID() {
		Scanner scanner = new Scanner(System.in);
		boolean flag = false;
		long id = 0L;
		System.out.println("Geben sie eine weitere ID ein:");
		while(!flag) {
			try {
				id = scanner.nextLong();
				flag = true;
			} catch (Exception ex) {
				System.out.println("Geben sie eine valide ID ein!");
				flag = false;
			}
		}
		scanner.close();
		return id;
	}

	public int getMinimumLikes() {
		System.out.println("Geben sie die Anzahl der minimalen Likes ein:");
		Scanner scanner = new Scanner(System.in);
		boolean flag = false;
		int minLikes = 0;
		while(!flag) {
			try {
				minLikes = scanner.nextInt();
				flag = true;
			} catch (Exception ex) {
				System.out.println("Keine valide Eingabe!");
				flag = false;
			}
		}
		scanner.close();
		return minLikes;
	}
}
