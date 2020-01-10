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
import org.kohsuke.github.GHCommit.File;

public class GitConnector {
	private GitHub github;
	private static GHRepository repo;
	
	public GitConnector() {
		try {
			github = GitHub.connectUsingOAuth("9b8d919e191637bad682f98b1f448d667d38d999");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			repo = github.getRepository("holos-supply/testdata");
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