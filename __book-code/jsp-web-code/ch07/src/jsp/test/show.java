package jsp.test;

public class show {
 private String name,password,sex,age,hobby;
 private String[] habit;
 public String getAge() {
	return age;
}
public void setAge(String age) {
	
    int age1 = Integer.parseInt(age);
    switch(age1)
    {
     case 10:
    	 this.age ="10 ~ 20";
       break;
     case 20:
    	 this.age ="21 ~ 30";
       break;
     case 30:
    	 this.age ="31 ~ 40";
       break;
     case 40:
    	 this.age ="41 ~ 65";
       break;
     default:
    	 this.age ="error";
       break;
    } 
    
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSex() {
	
	return sex;
}
public void setSex(String sex) {
    if(sex.equals("Male")){
    	this.sex="��";
      }
      else{
    	  this.sex="Ů";
      }
}
public String[] getHabit() {
	return habit;
}
public void setHabit(String[] habit) {
	hobby = "";
	for (int i=0; i<habit.length; i++)
	 {
	   if(habit[i].equals("Read"))
	    {
	     hobby+="���� ";
	    }
	   if(habit[i].equals("Football"))
	    {
		   hobby+="���� ";
	    }
	   if(habit[i].equals("Travel"))
	    {
		   hobby+="���� ";
	    }
	   if(habit[i].equals("Music"))
	    {
		   hobby+="������ ";
	    }
	   if(habit[i].equals("Tv"))
	    {
		   hobby+="������ ";
	    } 
	  }
}
public String getHobby() {
	return hobby;
}

}
