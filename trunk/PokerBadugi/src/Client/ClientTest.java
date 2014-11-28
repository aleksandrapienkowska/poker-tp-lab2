package Client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.DefaultCaret;


public class ClientTest {
	
	static Socket s;
	static Scanner tIn = new Scanner(System.in);
	private static OutputStream out;
	private static InputStream in;
	public static ClientInstance Client;
	
	public static void main(String[] args) {
	try {
	
		
	s = new Socket(InetAddress.getLocalHost(), Integer.parseInt(args[0]));
	in = s.getInputStream();
	out = s.getOutputStream();
	InputStreamReader isr = new InputStreamReader(in);
	Client=new ClientInstance(out, in);
   
   

	} catch (Exception e) {
	Client.broadcast("Nie można nawiązać połączenia z serverem");
	}
	
	}

}
