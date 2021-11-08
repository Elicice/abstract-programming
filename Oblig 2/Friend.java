package no.uib.info233.oblig2;

import java.util.Objects;

/**
 * Klasse for en venn
 *
 * @author Cecilie Lyshoel
 * @version oblig2, 1.0
 */
public class Friend implements Comparable<Friend>{


    private String lastName;
    private String firstName;
    private String eMail;
    private String phoneNumber;

    public Friend(String lastName, String firstName, String eMail, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    /**
     * Metode for sammenligning av etternavn
     * @param o the object to be compared.
     *
     */
    @Override
    public int compareTo(Friend o) {
        return this.lastName.compareTo(o.lastName);
    }

    /**
     * Metode for at Friend objektet i klassen er det samme som Friend objektet i metoden
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; //sjekker at annet objekt ikke er null eller at de ikke er i samme klasse
        Friend friend = (Friend) o;
        return Objects.equals(lastName, friend.lastName) &&
                Objects.equals(firstName, friend.firstName) &&
                Objects.equals(eMail, friend.eMail) &&
                Objects.equals(phoneNumber, friend.phoneNumber);
    }

    /**
     * Metode for å sette hashcode-verdi
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, eMail, phoneNumber);
    }
}