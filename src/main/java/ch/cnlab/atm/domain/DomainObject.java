package ch.cnlab.atm.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.common.base.Objects;

/**
 * Base class for a domain object.
 * 
 * Two domain objects are equal, when the types and ids of the domain objects
 * are equal.
 * 
 * @param <K>
 *            the type of the key.
 */
@MappedSuperclass
public abstract class DomainObject<K extends Serializable> implements Serializable {
	private static final long serialVersionUID = 9005250828638487245L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private K id;

	/**
	 * Returns the id of this domain object. The id is null, if the domain
	 * object is not persisted.
	 * 
	 * @return the id or {@code null}
	 */
	public K getId() {
		return id;
	}

	/**
	 * Sets the id for this domain object.
	 * 
	 * @param id
	 *            the id to set.
	 */
	public void setId(K id) {
		this.id = id;
	}

	/**
	 * Indicator if the domain object is persisted or not.
	 * 
	 * @return {@code true} if the domain object is not persisted.
	 */
	public boolean isVolatile() {
		return id == null;
	}

	@Override
	public int hashCode() {
		if (isVolatile()) {
			return super.hashCode();
		}
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (isVolatile()) {
			return super.equals(obj);
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		DomainObject<?> other = (DomainObject<?>) obj;

		return Objects.equal(id, other.id);
	}
}
