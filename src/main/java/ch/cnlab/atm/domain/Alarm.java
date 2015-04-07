package ch.cnlab.atm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Entity
@Table(name = "alarms")
public class Alarm extends DomainObject<Long> {

	// --------------------------------------
	// Constants
	// --------------------------------------
	private static final long serialVersionUID = 7347608186149578706L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int delayInMinutes;

	@Column(nullable = false)
	private int priority;

	@ManyToOne
	private User user;

	@ManyToOne
	private MeasureStation measureStation;

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

	public int getDelayInMinutes() {
		return delayInMinutes;
	}

	public void setDelayInMinutes(int delayInMinutes) {
		Preconditions.checkArgument(delayInMinutes >= 0);
		this.delayInMinutes = delayInMinutes;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		Preconditions.checkArgument(priority >= 0);
		this.priority = priority;
	}

	public Optional<User> getUser() {
		return Optional.of(user);
	}

	public void setUser(User user) {
		Preconditions.checkNotNull(user);
		this.user = user;
	}

	public MeasureStation getMeasureStation() {
		return this.measureStation;
	}

	public void setMeasureStation(MeasureStation measureStation) {
		Preconditions.checkNotNull(measureStation);
		this.measureStation = measureStation;
	}

}
