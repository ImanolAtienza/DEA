package dsa;

import java.util.ArrayList;
import java.util.Date;

public class Person {
	String idperson;
	String name;
	String lastname;
	Date date;
	char gender;
	String birthplace;
	String home;
	String studiedat;
	String workplaces;
	String films;
	String groupcode;
	ArrayList<String> friends = new ArrayList<>();

	public ArrayList<String> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<String> friends) {
		this.friends = friends;
	}

	public String getIdperson() {
		return idperson;
	}

	public void setIdperson(String idperson) {
		this.idperson = idperson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getStudiedat() {
		return studiedat;
	}

	public void setStudiedat(String studiedat) {
		this.studiedat = studiedat;
	}

	public String getWorkplaces() {
		return workplaces;
	}

	public void setWorkplaces(String workplaces) {
		this.workplaces = workplaces;
	}

	public String getFilms() {
		return films;
	}

	public void setFilms(String films) {
		this.films = films;
	}

	public String getGroupcode() {
		return groupcode;
	}
	
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	@Override
	public String toString() {
		return idperson + "," + name
				+ "," + lastname + "," + date + ","
				+ gender + "," + birthplace + "," + home
				+ "," + studiedat + "," + workplaces
				+ "," + films + "," + groupcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (birthplace == null) {
			if (other.birthplace != null)
				return false;
		} else if (!birthplace.equals(other.birthplace))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (films == null) {
			if (other.films != null)
				return false;
		} else if (!films.equals(other.films))
			return false;
		if (gender != other.gender)
			return false;
		if (groupcode == null) {
			if (other.groupcode != null)
				return false;
		} else if (!groupcode.equals(other.groupcode))
			return false;
		if (home == null) {
			if (other.home != null)
				return false;
		} else if (!home.equals(other.home))
			return false;
		if (idperson == null) {
			if (other.idperson != null)
				return false;
		} else if (!idperson.equals(other.idperson))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (studiedat == null) {
			if (other.studiedat != null)
				return false;
		} else if (!studiedat.equals(other.studiedat))
			return false;
		if (workplaces == null) {
			if (other.workplaces != null)
				return false;
		} else if (!workplaces.equals(other.workplaces))
			return false;
		return true;
	}

	public Person(String idperson, String name, String lastname, Date tokens,
			char gender, String birthplace, String home, String studiedat,
			String workplaces, String films, String groupCode) {
		super();
		this.idperson = idperson;
		this.name = name;
		this.lastname = lastname;
		this.date = tokens;
		this.gender = gender;
		this.birthplace = birthplace;
		this.home = home;
		this.studiedat = studiedat;
		this.workplaces = workplaces;
		this.films = films;
		this.groupcode = groupCode;
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}	
}
