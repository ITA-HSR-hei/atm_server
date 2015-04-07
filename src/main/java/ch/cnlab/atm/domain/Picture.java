package ch.cnlab.atm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Entity
@Table(name = "pictures")
public class Picture extends DomainObject<Long> {

	// --------------------------------------
	// Constants
	// --------------------------------------
	private static final long serialVersionUID = 3421573198668982620L;

	// --------------------------------------
	// Members
	// --------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(nullable = false)
	private String uri;

	@ManyToOne
	private MeasureStation measureStation;

	// --------------------------------------
	// Getters / Setters
	// --------------------------------------

	public Optional<Date> getDate() {
		return Optional.of(date);
	}

	public String getUri() {
		return Strings.nullToEmpty(uri);
	}

	public void setDate(Date date) {
		Preconditions.checkNotNull(date);
		this.date = date;
	}

	public void setUri(String uri) {
		Preconditions.checkNotNull(uri);
		this.uri = uri;
	}

}
