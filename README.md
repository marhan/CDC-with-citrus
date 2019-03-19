# CDC-with-citrus
Sample project showing a small consumer driven contract testing framework using citrus

# About consumer driven contracts
As the naming of the article
['Consumer-Driven Contracts: A Service Evolution Pattern'](https://www.martinfowler.com/articles/consumerDrivenContracts.html)
by Ian Robinson implies, consumer driven contracts are a *pattern* that can be used in various ways to solve problems
of growing service architecture. Therefore consumer driven contracts are not bound to a single framework or technology.
This project illustrates that by showing a small consumer driven contract framework based on the integration testing 
framework [Citrus](https://github.com/citrusframework).

# Project domain
The Domain of the system under test is hotel information. In this sample the service holds data about hotel ratings, rooms,
availabilities, etc. Based on this hotel information we have several consumers interested in the provided information.
One is the availability consumer requesting availability information for a specified hotel. The other consumer is
searching for hotels by specified criteria. Both consumer expect different responses for their requests.

**availability response**
```json
{
  "correlationId": "${correlationId}",
  "hotelId": 31415,
  "rooms": [{
    "roomId": 14,
    "roomName": "Double bed deluxe",
    "availability": 12
  },
    {
      "roomId": 15,
      "roomName": "King size bed deluxe",
      "availability": 5
    }]
}
```

**search response**
```json
[
  {
    "hotelId": 42,
    "name": "Hotel Gloria de Sant Jaume",
    "stars": 5,
    "arrivalAirport": "PMI"
  },
  {
    "hotelId": 84,
    "name": "Es Princep",
    "stars": 5,
    "arrivalAirport": "PMI"
  }
]
```

# CDC + Citrus 
Core of the cdc framework is the `ContractBehavior`. A Behavior is a repeatable, configurable set of test actions
that are executable with Citrus. The `ContractBehavior` contains the instructions to send a configured HTTP Request to
the system under test and expects a configured response. The configuration of the behavior is performed using the
`ContractBehaviorBuilder`. Both classes are located under `src/test/java/com/github/svettwer/citruscdc/contracts`.
The payload to be send and received are located in the test resources under `src/test/resources/contracts` as csv and
json files.

Let us take a look at the search contract implementation.
```java
@Test
@CitrusTest
void searchHotelContract(@CitrusResource final TestRunner runner) {
    runner.applyBehavior(
            new ContractBehaviorBuilder(runner)
                    .withClient(httpClient)
                    .withEndpoint("/search")
                    .withHttpMethod(HttpMethod.GET)
                    .withQueryParameter(new ClassPathResource("contracts/search/queryParameter.csv"))
                    .withResponsePayload(new ClassPathResource("contracts/search/responsePayload.json"))
                    .build());
}
```

As you can see, you just configure the `ContractBehavior` via the `ContractBehaviorBuilder`. When the test is executed,
Citrus will perform the HTTP request, receive the response and verifies the content of the response payload compared to
the test data. 

# Adding a new contract
Let us imagine, someone wants to add a new contract to the project. In this case, someone would create a pull request 
for the project containing the payload data added to the resources folder as well as the test case configuring the 
`ContractBehavior` to execute the contract test. The consumer and the provider could now discuss the payload and request
setup. If they agree, the provider uses the contract integration test as acceptance criteria during implementation.

# What's next?
Based on this concepts, one could setup a more sophisticated version of the framework to handle more complex contracts
depending on the requirements of the project.

# Executing the tests
`mvn clean verify`