package com.kv.fb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReturnUniqueContacts {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

class Contact {
    int contactId;
    List<String> emails;

    Contact(int contactId, List<String> emails) {
        this.contactId = contactId;
        this.emails = emails;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Contact)) return false;
        Contact otherContact = (Contact) o;
        for(String email : emails)
            if(otherContact.emails.contains(email)) {
                merge(otherContact.emails);
                return true;
            }
        return false;
    }

    private void merge(List<String> emails) {
        for(String email : this.emails) {
            if(!emails.contains(email)) {
                emails.add(email);
            }
        }
    }

    @Override
    public int hashCode() {
        return 7;
    }
}

class Cleaner {
    public Set<Contact> cleanContacts(List<Contact> contacts) {
        return new HashSet<>(contacts);
    }
}
