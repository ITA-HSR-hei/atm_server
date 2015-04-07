package ch.cnlab.atm.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Serverconfiguration")
public class ServerConfiguration extends DomainObject<Long> {
	private static final long serialVersionUID = -4751148762105709362L;

	@Column
	@Embedded
	private SMTPServerConfiguration emailServerConfiguration;

}
