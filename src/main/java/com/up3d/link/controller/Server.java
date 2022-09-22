package com.up3d.link.controller;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-16  14:27
 * @Description: stripe 回调demo测试
 */
// Server.java
//
// Use this sample code to handle webhook events in your integration.
//
// Using Maven:
// 1) Paste this code into a new file (src/main/java/com/stripe/sample/Server.java)
//
// 2) Create a pom.xml file. You can quickly copy one from an official Stripe Sample.
//   curl https://raw.githubusercontent.com/stripe-samples/accept-a-payment/8e94e50e68072c344f12b02f65a6240e1c656d4a/custom-payment-flow/server/java/pom.xml -o pom.xml
//
// 3) Build and run the server on http://localhost:4242
//   mvn compile
//   mvn exec:java -Dexec.mainClass=com.stripe.sample.Server

import com.google.gson.JsonSyntaxException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;

import static spark.Spark.port;
import static spark.Spark.post;

public class Server {
    public static void main(String[] args) {

        // This is your Stripe CLI webhook secret for testing your endpoint locally.
        String endpointSecret = "whsec_7f88ab79e9cadc6ccef51199fe48e34577fc9cf9b76ab2aeabaa1c1c616cd664";

        port(4242);

        post("/webhook", (request, response) -> {
            String payload = request.body();
            String sigHeader = request.headers("Stripe-Signature");
            Event event = null;

            try {
                event = Webhook.constructEvent(
                        payload, sigHeader, endpointSecret
                );
            } catch (JsonSyntaxException e) {
                // Invalid payload
                response.status(400);
                return "";
            } catch (SignatureVerificationException e) {
                // Invalid signature
                response.status(400);
                return "";
            }

            // Deserialize the nested object inside the event
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;
            if (dataObjectDeserializer.getObject().isPresent()) {
                stripeObject = dataObjectDeserializer.getObject().get();
            } else {
                // Deserialization failed, probably due to an API version mismatch.
                // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
                // instructions on how to handle this case, or return an error here.
            }
            // Handle the event
            switch (event.getType()) {
                case "payment_intent.succeeded": {
                    // Then define and call a function to handle the event payment_intent.succeeded
                    break;
                }
                // ... handle other event types
                default:
                    System.out.println("Unhandled event type: " + event.getType());
            }

            response.status(200);
            return "";
        });
    }
}
