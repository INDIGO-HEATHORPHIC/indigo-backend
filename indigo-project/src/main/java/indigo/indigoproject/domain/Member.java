package indigo.indigoproject.domain;

public class Member {
    private String userId;
    private String id;
    private String password;

    public String getUserId(){return userId;}

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
