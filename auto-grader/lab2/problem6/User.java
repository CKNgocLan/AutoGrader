
public class User {
    private String id;
    private String name;
    private String email;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean equals(User user) {
        return this.id.equals(user.id);
    }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		
		return this.equals((User)obj);
	}

    @Override
    public String toString() {
        return "ID: %s - Name: %s".formatted(id, name);
    }
}