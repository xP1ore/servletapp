package servlet.dao;

import servlet.common.ROLE;
import servlet.model.ServiceUser;

import java.util.ArrayList;
import java.util.List;

public class ServiceUserDao {
    // DB emulation
    private final List<ServiceUser> store = new ArrayList<>();

    public ServiceUser getById(int id) {
        ServiceUser result = new ServiceUser();
        result.setId(-1);

        for (ServiceUser user : store) {
            if (user.getId() == id) {
                result = user;
            }
        }
        return result;
    }

    public ServiceUser getUserByLoginPassword(final String login, final String password) {
        ServiceUser result = new ServiceUser();
        result.setId(-1);

        for (ServiceUser user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user;
            }
        }

        return result;
    }

    public boolean addUser(final ServiceUser sUser) {
        for (ServiceUser user : store) {
            if (user.getLogin().equals(sUser.getLogin()) && user.getPassword().equals(sUser.getPassword())) {
                return false;
            }
        }
        return store.add(sUser);
    }

    public ROLE getRoleByLoginPassword(final String login, final String password) {
        ROLE result = ROLE.UNKNOWN;

        for (ServiceUser user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }

        return result;
    }

    public boolean userExists(final String login, final String password) {
        boolean result = false;

        for (ServiceUser user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
