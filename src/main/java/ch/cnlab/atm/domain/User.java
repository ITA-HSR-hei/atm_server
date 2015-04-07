package ch.cnlab.atm.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Entity
@Table(name = "users")
public class User extends DomainObject<Integer> {

	// --------------------------------------
	// Constants
	// --------------------------------------

	private static final long serialVersionUID = 6813736863623150556L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private int zip;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String emailAddress;

	@Column(nullable = false)
	private String phoneNummber;

	@Column(nullable = false)
	private String password;

	@Column
	@Enumerated(EnumType.STRING)
	private Language language;

	@Column
	@Enumerated(EnumType.STRING)
	private Country country;

	@OneToMany
	private List<MeasureStation> measureStationslist;

	@ManyToMany(mappedBy = "user")
	private List<Role> roles;

	// --------------------------------------
	// Methods
	// --------------------------------------

	public boolean addMeasureStation(MeasureStation mStation) {
		Preconditions.checkNotNull(mStation);
		return measureStationslist.add(mStation);
	}

	public boolean removeMeasureStation(MeasureStation mStation) {
		Preconditions.checkNotNull(mStation);
		return measureStationslist.remove(mStation);

	}

	public boolean addRole(Role role) {
		Preconditions.checkNotNull(role);
		return roles.add(role);
	}

	public boolean removeRole(Role role) {
		Preconditions.checkNotNull(role);
		return roles.remove(role);
	}

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFirstName() {
		return Strings.nullToEmpty(firstName);
	}

	public String getLastName() {
		return Strings.nullToEmpty(lastName);
	}

	public String getEmailAddress() {
		return Strings.nullToEmpty(emailAddress);
	}

	public String getPassword() {
		return Strings.nullToEmpty(password);
	}

	public String getPhoneNummber() {
		return Strings.nullToEmpty(phoneNummber);
	}

	public String getStreet() {
		return Strings.nullToEmpty(street);
	}

	public String getCity() {
		return Strings.nullToEmpty(city);
	}

	public int getZip() {
		return zip;
	}

	public Optional<Language> getLanguage() {
		return Optional.of(language);
	}

	public Optional<Country> getCountry() {
		return Optional.of(country);
	}

	public Optional<List<Role>> getRoles() {
		return Optional.of(roles);
	}

	public Optional<List<MeasureStation>> getMeasureStations() {
		return Optional.of(measureStationslist);
	}

	public void setFirstName(String firstName) {
		Preconditions.checkNotNull(firstName);
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		Preconditions.checkNotNull(lastName);
		this.lastName = lastName;
	}

	public void setEmailAddress(String emailAddress) {
		Preconditions.checkNotNull(emailAddress);
		this.emailAddress = emailAddress;
	}

	public void setPassword(String password) {
		Preconditions.checkNotNull(password);
		this.password = password;
	}

	public void setPhoneNummber(String phoneNummber) {
		Preconditions.checkNotNull(phoneNummber);
		this.phoneNummber = phoneNummber;
	}

	public void setStreet(String street) {
		Preconditions.checkNotNull(street);
		this.street = street;
	}

	public void setCity(String city) {
		Preconditions.checkNotNull(city);
		this.city = city;
	}

	public void setZip(int zip) {
		Preconditions.checkArgument(zip >= 0);
		this.zip = zip;
	}

	public void setLanguage(Language language) {
		Preconditions.checkNotNull(language);
		this.language = language;
	}

	public void setCountry(Country country) {
		Preconditions.checkNotNull(country);
		this.country = country;
	}

}
