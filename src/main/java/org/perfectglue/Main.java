package org.perfectglue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.perfectglue.config.Projects;
import org.perfectglue.config.model.Client;
import org.perfectglue.config.model.File;
import org.perfectglue.config.model.Project;
import org.perfectglue.connector.GitConnector;
import org.perfectglue.util.DataHandler;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Projects projects = DataHandler.initializeProjects(); //inits to yamltest and searches for democlientlist - to get the clientList.yml back
		List<Client> clients = projects.getClients();
		for (Client c : clients) {
			for (Project p : c.getProjects()) {
				for (File f : p.getFiles()) {
					if (f.getFileName().contentEquals("demo.xml")) { // theoretically get from gherkin
						GitConnector con = new GitConnector("holos-supply/testdata-test");
						System.out.println(f.getCommitMessage());
						con.getFileContentsByCommitMessage("", f.getCommitMessage());
					}
				}
			}
		}
	}

}
