package org.perfectglue.stepdefs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.Map;

import org.perfectglue.connector.QueueConnector;
import org.perfectglue.util.MessageHandler;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

/**
 * Demo Step Definition class using made up examples to explain how to write proper 
 * step definitions. 
 * <p/>
 * In this demo, we are testing if a remote API (on http://my.remote.domain/api) is
 * working correctly. We obtain data from it via REST api and communicate to other systems
 * using a queue as well.
 * 
 * @author Rok Pu&#154;nik
 * @author Bojan Ivanovi&#263;
 *
 */
public class DemoStepDefinitions {
	private int candy;
	private static MessageHandler handler = new MessageHandler(); 
	private String payload; //let's presume there's something actually in here.
	
	/**
	 * The first method we write, in this case it is mostly a setup step.
	 * with an assert to make sure that our starting conditions are correct:
	 * the API agrees that we have the same amount of candy.
	 */
	@Given("I had {int} bags of candy")
	public void iHadCandy(int number) {
		String apicandy = given()
				.get("http://my.remote.domain/api/candy/check")
				.asString();
		this.candy = number;
		assertEquals(Integer.toString(this.candy), apicandy);
	}
	
	/**
	 * In this method we create a so-called QueueConnector, connecting to a Queue to get
	 * candy from people, maybe it's our birthday, however, we do not know the
	 * queue name, we only know the person giving us the candy, so we use the
	 * resolveQueueName method, giving the person's name, and have it hopefully give us
	 * the queue we want to listen to.
	 * 
	 * If the resolution fails, the method will return an error, which we need to 
	 * check against. Thus the "assertNotEquals" method.
	 * there are several other types of asserts found at:
	 *     https://junit.org/junit4/javadoc/4.12/org/junit/Assert.html
	 * 
	 * Once we are sure we actually have our queue, we can create the QueueConnector,
	 * giving it our queueName as parameter in its creation (to its constructor)
	 * 
	 * We now have a queue we can listen to. In this case we know that there is only one
	 * message in that queue, or that we are the only ones checking about it, so we use
	 * the getFirstMessage method. In other cases there may be other methods requiring
	 * parameters to only grab messages meant for us. A currently-existing method like
	 * that is getNotificationMessage(), which requires a parameter for the VA ID to
	 * check against.
	 * 
	 * We save the message we get into a String, then use our MessageHandler to grab the 
	 * XML element's value from the message.
	 * 
	 * Next, we use the value we obtained from the message, to update our local
	 * candy count
	 * 
	 * We put the number into a JSON payload which we then use to tell our API how many 
	 * new bags of candy we have.
	 */
	@When("I am given {int} bags of candy from {word}")
	public void iAmGivenCandy(int number, String person) {
		String queueName = resolveQueueName(person);
		assertNotEquals(queueName,"Error"); //it should not be error!
		QueueConnector queue = new QueueConnector(queueName);
		String message = queue.getFirstMessage();
		String numberOfBags = handler.getXmlElement(message, "NumberOfBags");
		candy += Integer.parseInt(numberOfBags);
		
		handler.setJsonElement(payload, 
				null, /*has no parent tag*/
				"amountOfCandy", 
				Integer.toString(candy));
		given()
		.when()
		.contentType(ContentType.JSON)
		.body(payload)
		.post("http://my.remote.domain/api/candy/got");
	}
	/**
	 * This time, we are eating candy, so we need to tell our API how many candy bags we 
	 * have eaten. 
	 * 
	 * So we will write the amount of candy we have written into a payload using the
	 * MessageHandler, and use restassured to POST it to the remote API 
	 */
	@When("I eat {int} bags of candy") 
	public void iEatCandy(int number) {
		handler.setJsonElement(payload, null, "amountOfCandy", Integer.toString(number));
		given()
		.when()
		.contentType(ContentType.JSON)
		.body(payload)
		.post("http://my.remote.domain/api/candy/eaten");
	}
	/**
	 * In this method, we need to confirm that the API's amount of candy bags is identical
	 * to ours. 
	 * 
	 * To do so we will ask it about how much candy it thinks we should have
	 * 
	 * Then we will use our MessageHandler to fetch the amount of candy from the response
	 * 
	 * Before comparing it with the amount of candy we have in our local variable.
	 * 
	 * If they do not match, we call the fail() method and explain the result.
	 */
	@Then("I have {int} bags of candy")
	public void iHaveCandy(int number) {
		Response response = given()
		.when()
		.get("http://my.remote.domain/api/candy/check");
		if (
				!handler.getJsonElement(response.asString(), "amountOfCandy")
				.equals(Integer.toString(candy))
			) {
			fail("the web API messed up the amount of candy bags");
		}
	}
	/**
	 * We know that, should we eat more than 2 bags of candy in one sitting, that our
	 * stomach will hurt. Our microservice has been made to also be aware of that, so we 
	 * will check the status on it, see if it knows our stomach should be hurting now
	 * 
	 * To do that, we first get the data from it using restassured
	 * 
	 * Then we will use a for-each loop to go through the little
	 * table we made in the feature file and search for a key-value pair
	 * 
	 * We expect the API response to look something like:
	 * {
	 *   "head": {
	 *     "status":"something"
	 *   },
	 *   "stomach": {
	 *     "status":"something"
	 *   },
	 *   ...
	 * }
	 * 
	 * so our feature file is already made using the "stomach.status" notation.
	 * 
	 * We now only check that the JSON response has a field stomach.status, and that that
	 * status is what we expect it to be.
	 */
	@And("my situation is")
	public void mySituationIs(Map<String, String> data) {
		ValidatableResponse response = given()
										.when()
										.get("http://my.remote.domain/api/status")
										.then();
		for (Map.Entry<String, String> field : data.entrySet()) {
			response.body(field.getKey(), equalTo(field.getValue()));
		}
	}
	/**
	 * in practice this method could be named differently, 
	 * have different parameters, and would look into
	 * a JSON or YAML structure to find the relevant queue name.
	 * it would likely not look like this.
	 */
	private String resolveQueueName(String name) {

		if (name.contentEquals("Jack")) {
			return "MY.QUEUE.NAME";
		}
		else return "Error";
	}
}

