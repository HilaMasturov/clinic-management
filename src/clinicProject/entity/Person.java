package clinicProject.entity;

public abstract class Person {
	String ID;
	String Name;
	int age;
	
//------------------------------constructors-------------------------------------	 
	
	public Person(String ID, String Name, int age) {
		setID(ID);
		setName(Name);
		setAge(age);
	}
	
	@Override
	public String toString() {
		return "person [ID=" + ID + ", Name=" + Name + ", age=" + age + "]";
	}

	public Person() {
		setID(null);
		setName(null);
		setAge(0);
	}
	
//---------------------------------methods-----------------------------------
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

}
