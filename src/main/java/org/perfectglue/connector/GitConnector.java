package org.perfectglue.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHFileNotFoundException;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.perfectglue.config.GitProperties;
import org.perfectglue.util.DataHandler;
import org.kohsuke.github.GHCommit.File;
/**
 * The <code>GitConnector</code> is a connector class used to connect to Git and to get
 * data from it
 *  
 * @author Rok Pu&#154;nik
 * @author Bojan Ivanovi&#263;
 *
 */
public class GitConnector {
	private GitHub github;
	private static GHRepository repo;
	private GitProperties properties = DataHandler.initializeGitPreoperties();
	
	
	public GitConnector() {
		try {
			github = GitHub.connectUsingOAuth(properties.getGithub().getOauth()); //todo: implement getting token from application.yml
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			repo = github.getRepository(""); //todo: implement getting repo address from application.yml
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFileContentsByCommitMessage(String messageName, String commitmessage) throws IOException {
		System.out.println(commitmessage);
		List<GHCommit> list = repo.listCommits().asList();
		for (GHCommit comment : list) {
			System.out.println(comment.getCommitShortInfo().getMessage());
			if (comment.getCommitShortInfo().getMessage().equals(commitmessage)) {
				List<File> files = comment.getFiles();
				for (File f : files) {
					
					
					//TODO: message name and commit message need to fit for file to be accepted...
					
					
					if (f.getFileName().matches("^(..)/([A-Z][a-z]+)/\\1_\\2_.+_"+commitmessage+".xml")) {
					try (PrintWriter out = new PrintWriter("src/test/testdata/"+f.getFileName())) {
					
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(repo.getFileContent(f.getFileName()).read()));

						return org.apache.commons.io.IOUtils.toString(reader);
						
					} catch(GHFileNotFoundException e) {
							System.out.println("caught: "+e.getMessage());
					}
					} else {
						throw new IOException("wrong file");
					}
				}
			} else {
				//if commit message isn't what we're looking for...
				System.out.println("something went wobbly, try a different ID?");
			}
		}
		return "";
	}
}
