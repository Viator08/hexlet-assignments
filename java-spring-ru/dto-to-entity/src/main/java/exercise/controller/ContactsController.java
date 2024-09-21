package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactCreateDTO contactCreateDTO) {
        Contact contact = new Contact();
        contact.setPhone(contactCreateDTO.getPhone());
        contact.setFirstName(contactCreateDTO.getFirstName());
        contact.setLastName(contactCreateDTO.getLastName());

        var contactSaved = contactRepository.save(contact);

        var contactDTO = new ContactDTO();
        contactDTO.setId(contactSaved.getId());
        contactDTO.setPhone(contactSaved.getPhone());
        contactDTO.setFirstName(contactSaved.getFirstName());
        contactDTO.setLastName(contactSaved.getLastName());
        contactDTO.setCreatedAt(contactSaved.getCreatedAt());
        contactDTO.setUpdatedAt(contactSaved.getUpdatedAt());
        return contactDTO;
    }
    // END
}
