package GUI.Server;

import DAO.AccountDAOImpl;
import DAO.Interface.IAccountDAO;
import Utils.Helper;
import Utils.ServiceBuilder;
import service.AccountService;

public class Main {
    public static void main(String[] args) {
        Helper.initUI();
        ServiceBuilder.getInstance().register(IAccountDAO.class, AccountDAOImpl.class)
                .register(AccountService.class, AccountService.class)
                .build();
        // run server below
    }
}
