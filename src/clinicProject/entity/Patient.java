package clinicProject.entity;

public class Patient extends Person{

	public Patient(String ID, String Name, int age) {
		setID(ID);
		setName(Name);
		setAge(age);
	}

	@Override
	public String toString() {
		return "patient [ID=" + ID + ", Name=" + Name + ", age=" + age + "]";
	}

}
