package main;

import java.util.Scanner;

import org.hibernate.Session;

import api.PersonRelatedImpl;
import api.StatisticImpl;

public class Application {
	
	PersonRelatedImpl priAPI;
	StatisticImpl statAPI;
	HibernateUtil hibernate;
	Session session;
	boolean exit = false;

	public Application(PersonRelatedImpl priAPI, StatisticImpl statAPI, HibernateUtil hibernate, Session session) {
		this.priAPI = priAPI;
		this.statAPI = statAPI;
		this.hibernate = hibernate;
		this.session = session;
	}
	
	public int readSelection() {
		Scanner scanner = new Scanner(System.in);
		return Integer.parseInt(scanner.nextLine());
	}
	
	public void performAction(int selection) {
		switch(selection) {
			case 0: exit = true; break;
			case 1: priAPI.getProfile(readID(), session); break;
			case 2: priAPI.getCommonInterestOfMyFriends(readID(), session); break;
			case 3: 
				System.out.println("ID 1");
				long id1 = readID();
				System.out.println("ID 2");
				long id2 = readID();
				priAPI.getCommonFriends(id1, id2, session); 
				break;
			case 4: priAPI.getPersonsWithMostCommonInterests(readID(), session); break;
			case 5: priAPI.getJobRecommendations(readID(), session);; break;
			case 6: 
				System.out.println("ID 1");
				long pid1 = readID();
				System.out.println("ID 2");
				long pid2 = readID();
				priAPI.getShortestFriendshipPath(pid1, pid2, session);
			case 7: statAPI.getTagClassHierarchy(session); break;
			case 8: statAPI.getPopularComments(session, getMinimumLikes()); break;
			case 9: statAPI.getMostPostingCountry(session); break;
			default: exit = true; break;
		}
	}
	
	public void printMenu() {
		System.out.println("\n----------------------------------------------");
		System.out.println("DBPraktikum - Teil 3");
		System.out.println("Optionen für Eingabe:");
		System.out.println("0 - Exit");
		System.out.println("----------------------------------------------");
		System.out.println("PersonRelatedAPI:");
		System.out.println("1 - getProfile");
		System.out.println("2 - getCommonInterestOfMyFriends");
		System.out.println("3 - getCommonFriends");
		System.out.println("4 - getPersonsWithMostCommonInterests");
		System.out.println("5 - getJobRecommendations");
		System.out.println("6 - getShortestFriendshipPath:");
		System.out.println("----------------------------------------------");
		System.out.println("StatisticAPI:");
		System.out.println("7 - getTagClassHierarchy");
		System.out.println("8 - getPopularComments");
		System.out.println("9 - getMostPostingCountry");
		System.out.println("----------------------------------------------");
		System.out.println("Eingabe:");
	}

	public void startApplication() {
		while(!exit) {
			printMenu();
			int selection = readSelection();
			performAction(selection);
		}
		System.out.println("Ende der Application");
	}
	
	public long readID() {
		Scanner scanner = new Scanner(System.in);
		boolean flag = false;
		long id = 0L;
		System.out.println("Geben Sie eine ID ein:");
		while(!flag) {
			try {
				id = Long.parseLong(scanner.nextLine());
				flag = true;
			} catch (Exception ex) {
				System.out.println("Geben Sie eine valide ID ein!");
				flag = false;
			}
		}
		return id;
	}

	public int getMinimumLikes() {
		System.out.println("Geben Sie die Anzahl der minimalen Likes ein:");
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
		return minLikes;
	}
}
