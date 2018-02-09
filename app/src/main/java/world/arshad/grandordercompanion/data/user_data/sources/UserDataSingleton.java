package world.arshad.grandordercompanion.data.user_data.sources;

/**
 * Created by arshad on 04/01/2018.
 */

public class UserDataSingleton {

    // Singleton boilerplate
    private static final UserDataSingleton ourInstance = new UserDataSingleton();

    public static UserDataSingleton getInstance() {
        return ourInstance;
    }

    private UserDataSingleton() {
    }

    // Room DB

    private UserDataDatabase roomDB;

    public UserDataDatabase getRoomDB() {
        return roomDB;
    }

    public void setRoomDB(UserDataDatabase roomDB) {
        this.roomDB = roomDB;
    }

}
