package org.perfectglue;

import java.util.List;

import org.perfectglue.config.Projects;
import org.perfectglue.config.model.Client;
import org.perfectglue.config.model.File;
import org.perfectglue.config.model.Project;
import org.perfectglue.connector.GitConnector;
import org.perfectglue.util.DataHandler;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Projects projects = DataHandler.initializeProjects(); //inits to holos-supply/yamltest and searches for democlientlist commit - to get the clientList.yml back, then puts it into pojo and returns it
		List<Client> clients = projects.getClients(); //supposed to get the List<Client>
		for (Client c : clients) {
			for (Project p : c.getProjects()) {
				for (File f : p.getFiles()) {
					if (f.getFileName().contentEquals("demo.xml")) { // theoretically get from gherkin
						GitConnector con = new GitConnector("holos-supply/testdata-test"); //this is the repo where the test message is
						System.out.println(f.getCommitMessage());
						con.getFileContentsByCommitMessage("", f.getCommitMessage());
					}
				}
			}
		}
	}
}
