package resources;

public class AdminDTO {
    private String email;
    private String password; // Plain password (no hashing)

    public AdminDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}