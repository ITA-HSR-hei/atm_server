package ch.cnlab.atm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Entity
@Table(name = "Roles")
public class Role extends DomainObject<Long> {

	// --------------------------------------
	// Constants
	// --------------------------------------

	private static final long serialVersionUID = 7347608186149578706L;

	// --------------------------------------
	// Members
	// --------------------------------------
	@Column(nullable = false)
	private String name;

	@ManyToOne
	private User user;

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public String getName() {
		return Strings.nullToEmpty(name);
	}

	public void setName(String name) {
		Preconditions.checkNotNull(name);
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
