package tt.authorization.entity;

public enum Permission {
    READ("read"), CHECK("check"), WRITE("write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
