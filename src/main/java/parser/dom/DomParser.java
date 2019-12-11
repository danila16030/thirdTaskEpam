package parser.dom;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import entity.Bank;
import exeption.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import parser.field.XmlFields;
import parser.sax.SaxParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    private static Logger logger = LogManager.getLogger(SaxParser.class);
    private List<Bank> bankList = new ArrayList<>();

    private static String getValue(Element element, String name) {
        NodeList nodeList = element.getElementsByTagName(name);
        Element firstElement = (Element) nodeList.item(0);
        Text text = (Text) firstElement.getFirstChild();
        return text.getNodeValue();
    }

    public List<Bank> parse(String filePath) throws ParserException {
        logger.info("Start parsing Sax");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);
            NodeList nodeList = document.getElementsByTagName(XmlFields.BANK);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Bank bank = new Bank();
                Element element = (Element) node;
                NamedNodeMap attributes = node.getAttributes();
                bank.setType(attributes.getNamedItem(XmlFields.TYPE).getNodeValue());
                bank.setId(Integer.parseInt(attributes.getNamedItem(XmlFields.ID).getNodeValue()));
                bank.setAccountId(Integer.parseInt(getValue(element, XmlFields.ACCOUNT_ID)));
                bank.setTimeConstraints(Integer.parseInt(getValue(element, XmlFields.TIME_CONSTRAINTS)));
                bank.setProfitability(Double.parseDouble(getValue(element, XmlFields.PROFITABILITY)));
                bank.setName(getValue(element, XmlFields.NAME));
                bank.setDepositor(getValue(element, XmlFields.DEPOSITOR));
                bank.setCountry(getValue(element, XmlFields.COUNTRY));
                bank.setAmountOfDeposit(Integer.parseInt(getValue(element, XmlFields.AMOUNT_OF_DEPOSIT)));
                this.bankList.add(bank);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParserException(e.getMessage(),e.getCause());
        }

        return bankList;
    }
}
