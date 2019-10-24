import java.time.LocalDate;

public class Member {

	private Integer id;
	private String name;
	private java.time.LocalDate birthDay;
	private String gender;
	private Integer colorId;

	public Member() {
	}

	public Member(Integer id, String name, LocalDate birthDay, String gender, Integer colorId) {
		this.id = id;
		this.name = name;
		this.birthDay = birthDay;
		this.gender = gender;
		this.colorId = colorId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.time.LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(java.time.LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	@Override
	public String toString() {
		return "Members [id=" + id + ", name=" + name + ", gender=" + gender + ", colorId=" + colorId + "]";
	}

}
