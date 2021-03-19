package haksa;

public class MajorDTO {
	private String code;
	private String department;
	private String major;
	
	public MajorDTO(String code, String department, String major) {
		super();
		this.code = code;
		this.department = department;
		this.major = major;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Override
	public String toString() {
		return code+","+department+","+major;
	}
	
}
