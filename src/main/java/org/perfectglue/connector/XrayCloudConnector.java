package org.perfectglue.connector;

import static io.restassured.RestAssured.given;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.response.Response;
/**
 * The <code>XrayServerConnector</code> is a core class used by Spring Boot to connect to 
 * Xray for a cloud instance of Jira
 * 
 * @author Willm T&uuml;ting
 *
 */
public class XrayCloudConnector implements IXrayConnector {

	private String client_id;
	private String client_secret;
	private String jiraXrayUrl;
	private String accessToken;

	Logger logger = LoggerFactory.getLogger(XrayCloudConnector.class);

	public XrayCloudConnector(String client_id, String client_secret, String jiraXrayUrl) {
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.jiraXrayUrl = jiraXrayUrl;
		authenticateXrayCloud();
		logger.info("Connection to Xray Cloud established and authorized.");
	}

	public void authenticateXrayCloud() {

		// POST authenticate
		HashMap<String, String> headerMapAuth = new HashMap<String, String>();
		headerMapAuth.put("Content-Type", "application/json");

		String body = "{ \"client_id\": \"" + client_id + "\",\"client_secret\": \"" + client_secret + "\" }";

		Response response = given().headers(headerMapAuth).body(body).post(jiraXrayUrl + "/api/v1/authenticate");

		// delete " at the beginning and the end
		StringBuilder builder = new StringBuilder(response.getBody().asString());

		builder.deleteCharAt(builder.length() - 1);
		builder.deleteCharAt(0);

		// return accessToken
		this.accessToken = builder.toString();
	}

	@Override
	public void getGherkinFromTestCloud(String id) {

		// query xray to get feature of a test
		HashMap<String, String> headerMap = new HashMap<String, String>();

		headerMap.put("Content-Type", "application/json");
		headerMap.put("Authorization", "Bearer " + accessToken);

		// fetch result
		Response response = given().headers(headerMap).get(jiraXrayUrl + "/api/v1/export/cucumber?keys=" + id);

		// write feature files to disk
		try {

			InputStream fis = response.body().asInputStream();
			byte[] buffer = new byte[2048];

			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry ze;
			
			Path outDir = Paths.get("src/test/features/");

			while ((ze = zis.getNextEntry()) != null) {

				Path filePath = outDir.resolve(ze.getName());

				FileOutputStream fos = new FileOutputStream(filePath.toFile());
				BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					bos.write(buffer, 0, len);
				}
				bos.flush();
				bos.close();
				fos.flush();
				fos.close();
				//fis.close();
				//zis.close();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		logger.info("Export of Gherkins from Xray done.");
	}

	@Override
	public void publishResults(String id) {

		// query xray to get feature of a test
		HashMap<String, String> headerMap = new HashMap<String, String>();

		headerMap.put("Content-Type", "application/json");
		headerMap.put("Authorization", "Bearer " + accessToken);

		// read results
		String body = new String();
		try {
			body = new String(Files.readAllBytes(Paths.get("target/report/cucumber.json")));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		logger.trace("RESULTS: "+body);
		logger.info("Results of the TestRun found.");

		// add the results to the given TestExecution
		//MessageHandler handle = new MessageHandler();
		//handle.setJsonElement(body, "info", "testExecutionKey", "TPT-1222");

		// post results to xray
		// Response response
		given().headers(headerMap).body(body).post(jiraXrayUrl+"/api/v1/import/execution/cucumber");

		logger.info("Results of the tests publish to Xray");
		
		// delete maybe present old feature files
		try {
			System.gc();
		FileUtils.cleanDirectory(new File("src/test/features/"));
		logger.info("Housekeeping done.");
		} catch(Exception e) {
			logger.info("Housekeeping failed.");
		}
	}

}
