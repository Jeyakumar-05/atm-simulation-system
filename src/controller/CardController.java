package controller;

import model.CardDAO;
import resources.CardDTO;

public class CardController {
    private CardDAO cardDAO = new CardDAO();

    public CardDTO verifyCard(String cardId, String pin) {
        return cardDAO.verifyCard(cardId, pin);
    }

    public void addCard(CardDTO card) {
        cardDAO.addCard(card);
    }
}