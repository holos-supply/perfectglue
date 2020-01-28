package org.perfectglue;

import java.io.IOException;
import java.util.List;

import org.perfectglue.config.Projects;
import org.perfectglue.config.model.Client;
import org.perfectglue.config.model.File;
import org.perfectglue.config.model.Project;
import org.perfectglue.connector.GitConnector;
import org.perfectglue.util.DataHandler;

public class Main {
	
	public static void main(String[] args) throws IOException {
		GitConnector con = new GitConnector();
		Projects projects = DataHandler.initializeProjects();
		List<Client> clients = projects.getClient();
		System.out.println(clients.get(0).getClientName()); //nullpointer?
		for (Client c : clients) {
			for (Project p : c.getProject()) {
				for (File f : p.getFiles()) {
					if (f.getFileName().contentEquals("demo.xml")) { // theoretically get from different place
						System.out.println(f.getCommitMessage());
						con.getFileContentsByCommitMessage("", f.getCommitMessage());
					}
				}
			}
		}
	}

}
