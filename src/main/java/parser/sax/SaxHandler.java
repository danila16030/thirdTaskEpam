package parser.sax;

import entity.Bank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import parser.field.XmlFields;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger(SaxHandler.class);
    private List<Bank> bankList;
    private Bank bank;
    private String information = null;

    public List<Bank> getBankList() {
        return bankList;
    }

    public void characters(char[] ch, int start, int length) {
        information = String.copyValueOf(ch, start, length).trim();//trim-удалляет пробелы в начале и конце строки
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        switch (qName) {
            case XmlFields.BANKS:
                bankList = new ArrayList<>();
                break;
            case XmlFields.BANK:
                bank = new Bank();
                bank.setId(Integer.parseInt(attributes.getValue(XmlFields.ID)));
                bank.setType(attributes.getValue(XmlFields.TYPE));
                break;
        }
    }

    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case XmlFields.BANK:
                bankList.add(bank);
                break;
            case XmlFields.NAME:
                bank.setName(information);
                break;
            case XmlFields.COUNTRY:
                bank.setCountry(information);
                break;
            case XmlFields.DEPOSITOR:
                bank.setDepositor(information);
                break;
            case XmlFields.ACCOUNT_ID:
                bank.setAccountId(Integer.parseInt(information));
                break;
            case XmlFields.AMOUNT_OF_DEPOSIT:
                bank.setAmountOfDeposit(Integer.parseInt(information));
                break;
            case XmlFields.PROFITABILITY:
                bank.setProfitability(Float.parseFloat(information));
                break;
            case XmlFields.TIME_CONSTRAINTS:
                bank.setTimeConstraints(Integer.parseInt(information));
                break;
        }
    }
}

