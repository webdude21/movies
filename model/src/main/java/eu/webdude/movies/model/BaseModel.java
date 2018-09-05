package eu.webdude.movies.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = 182576774230216530L;

	@Version
	@Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
	private long version = 0L;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	@PreUpdate
	public void preSave() {
		preSaveImpl();
	}

	protected void preSaveImpl() {
		LocalDateTime now = LocalDateTime.now();
		if (getCreatedAt() == null) {
			setCreatedAt(now);
		}
		setUpdatedAt(now);
	}

	public Long getVersion() {
		return version;
	}

	private void setVersion(Long version) {
		this.version = version;
	}
}
