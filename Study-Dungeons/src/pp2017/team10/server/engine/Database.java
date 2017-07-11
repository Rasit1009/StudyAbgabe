package pp2017.team10.server.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Database {
	
	ArrayList<Highscore> highscore = new ArrayList<Highscore>();
	ArrayList<User> userdata = new ArrayList<User>();
	
	public Database() throws IOException{
		
		FileReader frHighscore = new FileReader("Highscores.txt");
		BufferedReader brHighscore = new BufferedReader(frHighscore);
		String readLineHighscore = brHighscore.readLine();
		String score = "";
		String user = "";
		String pos = "";
		int i = 0;
		while(readLineHighscore != null){
			i = 0;
			while(readLineHighscore.charAt(i) != '#'){
				pos = pos + readLineHighscore.charAt(i);
				i++;
			}
			while(readLineHighscore.charAt(i) != '#'){
				user = user + readLineHighscore.charAt(i);
				i++;
			}
			while(readLineHighscore.charAt(i) != '#'){
				score = score + readLineHighscore.charAt(i);
				i++;
			}
			readLineHighscore = brHighscore.readLine();
		}
		
		brHighscore.close();
		frHighscore.close();
		
		FileReader frUser = new FileReader("User.txt");
		BufferedReader brUser = new BufferedReader(frUser);
			String username = "";
			String pw = "";
			int j = 0;
			String readLineUser = brUser.readLine();
			while(readLineUser != null){
				username = "";
				pw = "";
				while(readLineUser.charAt(j) != '#'){
					username = username + readLineUser.charAt(j);
					j++;
				}
				j++;
				while(readLineUser.charAt(j) != '#'){
					pw = pw + readLineUser.charAt(j);
					j++;
				}
				j = 0;
				readLineUser = brUser.readLine();
				User u = new User(username, pw);
				userdata.add(u);
			}
		
		brUser.close();
		frUser.close();
	}
	
	public ArrayList<Highscore> getHighscore(){
		return highscore;
	}
	
	public void addHighscore(Highscore highscoreadd){
		highscore.add(highscoreadd);
		
		if(highscore.isEmpty()){
			highscore.add(highscoreadd);
		}
		else{
			Collections.sort(highscore, new Comparator<Highscore>(){

				@Override
				public int compare(Highscore o1, Highscore o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o1.pos, o2.pos);
				}
			});	
			Collections.reverse(highscore);
			
			if(highscore.size() < 10){
				while(highscore.size() > 10){
					highscore.remove(10);
				}
			}
			
			for(int i = 0; i < highscore.size(); i++){
				highscore.get(i).pos = i + 1;
			}
			}
		
		try {
			FileWriter fwHighscore = new FileWriter("Highscore.txt");
			BufferedWriter bwHighscore = new BufferedWriter(fwHighscore);
			
			for(Highscore h : highscore){
				String toWrite = h.pos + "#" + h.user + "#" + h.pos + "#";
				bwHighscore.write(toWrite);
				bwHighscore.newLine();
				bwHighscore.flush();
			}
			
			bwHighscore.close();
			fwHighscore.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String addUser(String username, String password){
		for(User u : userdata){
			if(u.getUser().equals(username))
				return "This name is already taken!";
		}
		
		Password p = new Password();
		String pw = p.hashing(password);
		
		userdata.add(new User(username, pw));
		
		try{
			FileWriter fwAdd = new FileWriter("User.txt", true);
			BufferedWriter bwAdd = new BufferedWriter(fwAdd);
			bwAdd.write(username + "#" + pw + "#");
			bwAdd.newLine();
			
			bwAdd.close();
			fwAdd.close();

			return "The User was added succesfully.";
		}	catch(IOException e){
			System.out.println("Datei existiert nicht.");
			return null;
		}
	}
}