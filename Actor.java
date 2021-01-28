import java.util.Objects;

public class Actor implements Comparable<Actor> {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return fullName.equals(actor.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    public String getFullName() {
        return fullName;
    }

    private String fullName;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%-20s | %s", fullName, role);
    }

    @Override
    public int compareTo(Actor o) {
        return this.fullName.compareTo(o.getFullName());
    }
}
