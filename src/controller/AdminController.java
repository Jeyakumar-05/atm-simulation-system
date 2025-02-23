package controller;

import model.AccountDAO;
import model.CardDAO;
import model.AdminDAO;
import resources.AccountDTO;
import resources.CardDTO;
import resources.AdminDTO;

public class AdminController {
    private AdminDAO adminDAO = new AdminDAO();
    private AccountDAO accountDAO = new AccountDAO();
    private CardDAO cardDAO = new CardDAO();

    public boolean login(String email, String password) {
        AdminDTO admin = adminDAO.verifyAdmin(email, password);
        return admin != null;
    }

    public void addAccount(AccountDTO account) {
        accountDAO.addAccount(account);
    }

    public void addCard(CardDTO card) {
        cardDAO.addCard(card);
    }
}