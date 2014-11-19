package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;

public class Server_v2 {

	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static int maxClientsCount = 1;

	public static void main(String args[]) {
		clientThread_v2[] threads = null;

		try {
			int portNumber = Integer.valueOf(args[0]).intValue();
			if (Integer.parseInt(args[1]) <= 6
					&& Integer.parseInt(args[1]) >= 2) {
				maxClientsCount = Integer.parseInt(args[1]);

				System.out.println("Maksymalna ilość klientów: "
						+ maxClientsCount + "\nport:" + portNumber);
			} else {
				System.out
						.println("Podano nieprawidłowe ustawienia początkowe");
				System.exit(0);
			}
			threads = new clientThread_v2[maxClientsCount];
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception z) {
			System.out.println("Nieprawidlowe parametry uruchomieniowe gry");
		}

		while (true) {
			try {
				clientSocket = serverSocket.accept();

				int i = 0;
				for (i = 0; i < maxClientsCount; i++) {
					if (threads[i] == null) {
						threads[i] = new clientThread_v2(clientSocket, threads);
						threads[i].start();
						break;
					}
				}
				if (i == maxClientsCount) {
					PrintStream os = new PrintStream(
							clientSocket.getOutputStream());
					os.println("Zbyt wielu graczy");
					os.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
