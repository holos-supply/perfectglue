package org.perfectglue;

import org.perfectglue.config.ProjectProperties;
import org.perfectglue.config.model.Client;
import org.perfectglue.config.model.File;
import org.perfectglue.config.model.Project;
import org.perfectglue.config.model.Projects;
import org.perfectglue.connector.GitConnector;
import org.perfectglue.util.DataHandler;

public class Main {

	public static void main(String[] args) throws Exception {
		ProjectProperties projects = DataHandler.initializeProjects(); 
		for (Projects pr : projects.getProjects()) { //use more streams instead!
			for (Client c : pr.getClient()) {
				for (Project p : c.getProjects()) {
					for (File f : p.getFiles()) {
						if (f.getFileName().contentEquals("demo.xml")) { // theoretically get from gherkin
							GitConnector con = new GitConnector(); // this is the repo where the test message is
							System.out.println("commitmessage: " + f.getCommitMessage());
							System.out.println("contents: " + con.getFileContentsByCommitMessage("", f.getCommitMessage()));
						}
					}
				}
			}
		}
	}
	// Map<String, Object> map = projects.getAdditionalProperties();
	// System.out.println("\nSize: "+map.size());
	// System.out.println("Projects content: "+map.get("projects"));
}