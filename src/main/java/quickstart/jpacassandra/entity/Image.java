package quickstart.jpacassandra.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images", schema = "oneplatform@oneplatform_pu")
public class Image {

	@Id
	@Column(name = "image_id")
	private UUID imageId;
	
	private String content;

	@Column(name = "type")
	private String type;

	@Column(name = "fetch_start_time")
	private Date fetchStartTime;

	private int width;

	private int height;

	public Image() {
		imageId = UUID.randomUUID();
		fetchStartTime = new Date();
	}

	public UUID getImageId() {
		return imageId;
	}

	public void setImageId(UUID imageId) {
		this.imageId = imageId;
	}

	public Date getFetchStartTime() {
		return fetchStartTime;
	}

	public void setFetchStartTime(Date fetchStartTime) {
		this.fetchStartTime = fetchStartTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getDataUri() {
		if (content != null && type != null) {
			return "data:image/" + type + ";base64," + content; 
		}
		return null;
	}

}
