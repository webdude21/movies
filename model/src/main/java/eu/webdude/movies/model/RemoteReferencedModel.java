package eu.webdude.movies.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class RemoteReferencedModel extends BaseModel {
	private static final long serialVersionUID = -4477119923988774999L;

	@Column(name = "external_key", unique = true)
	private String externalKey;

	@Column(name = "sync_date")
	private LocalDateTime syncDate;

	public String getExternalKey() {
		return externalKey;
	}

	public void setExternalKey(String externalKey) {
		this.externalKey = externalKey;
	}

	public Boolean isSynced() {
		return syncDate != null && !StringUtils.isEmpty(externalKey);
	}

	public LocalDateTime getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(LocalDateTime syncDate) {
		this.syncDate = syncDate;
	}
}

