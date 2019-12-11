package parser.stax;

import entity.Bank;
import exeption.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.field.XmlFields;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {
    private static Logger logger = LogManager.getLogger(StaxParser.class);
    private List<Bank> bankList;
    private XMLInputFactory xmlInputFactory;
    private XMLStreamReader xmlStreamReader;
    private String information;
    private Bank bank;

    public List<Bank> parse(String filePath) throws ParserException {
        try {
            logger.info("Start parsing StAX");
            xmlInputFactory = XMLInputFactory.newInstance();
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StreamSource(filePath));
            while (xmlStreamReader.hasNext()) {
                int eventType = xmlStreamReader.next();
                switch (eventType) {
                    case XMLStreamReader.START_ELEMENT:
                        startElement(xmlStreamReader);
                        break;
                    case XMLStreamReader.END_ELEMENT:
                        endElement(xmlStreamReader);
                        break;
                    case XMLStreamReader.CHARACTERS:
                        information = xmlStreamReader.getText().trim();
                        break;
                }
            }
        } catch (XMLStreamException e) {
            throw new ParserException(e.getMessage(),e.getCause());
        }
        logger.info("Xml document is parsed successfully by StAX");
        return this.bankList;
    }

    private void startElement(XMLStreamReader xmlStreamReader) {
        switch (xmlStreamReader.getLocalName()) {
            case XmlFields.BANKS:
                bankList = new ArrayList<>();
                break;
            case XmlFields.BANK:
                bank = new Bank();
                bank.setId(Integer.parseInt(xmlStreamReader.getAttributeValue(0)));
                bank.setType(xmlStreamReader.getAttributeValue(1));
                break;
        }
    }

    private void endElement(XMLStreamReader xmlStreamReader) {
        switch (xmlStreamReader.getLocalName()) {
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
