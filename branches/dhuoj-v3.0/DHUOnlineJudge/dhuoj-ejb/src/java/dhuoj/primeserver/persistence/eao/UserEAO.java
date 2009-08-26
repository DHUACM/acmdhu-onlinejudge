package dhuoj.primeserver.persistence.eao;

import java.util.List;
import dhuoj.primeserver.persistence.entity.User;

public interface UserEAO {

	public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(User user);

	public User findUser(String uid);

    public User chkLogin(String userId, String password);

    public List<User> findUsersInRange(int first, int max);

}