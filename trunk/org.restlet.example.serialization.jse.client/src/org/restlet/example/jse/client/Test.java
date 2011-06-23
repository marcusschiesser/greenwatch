package org.restlet.example.jse.client;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.example.common.Contact;
import org.restlet.example.common.ContactResource;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class Test {

    public static void main(String[] args) throws ResourceException,
            IOException {
        ClientResource cr = new ClientResource(
                "http://restlet-example-serialization.appspot.com/contacts/123");
        
        // Get the Contact object
        ContactResource resource = cr.wrap(ContactResource.class);
        Contact contact = resource.retrieve();
        if (contact != null) {
            System.out.println("firstname: " + contact.getFirstName());
            System.out.println(" lastname: " + contact.getLastName());
            System.out.println("      age: " + contact.getAge());
        }

        // In case the Contact object is not available
        // Get a JSON representation of the contact
        System.out.println("\nJSON representation");
        cr.get(MediaType.APPLICATION_JSON).write(System.out);
    }
}
