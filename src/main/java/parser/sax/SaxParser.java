package parser.sax;

import entity.Bank;
import exeption.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxParser {
    private static Logger logger = LogManager.getLogger(SaxParser.class);

    private List<Bank> bankList;

    public List<Bank> parse(String filePath) throws ParserException {
        logger.info("Start parsing Sax");
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            SaxHandler saxHandler = new SaxHandler();
            if (saxHandler != null) {
                saxParser.parse(filePath, saxHandler);
                bankList = saxHandler.getBankList();
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParserException(e.getMessage(),e.getCause());
        }
        logger.info("Xml document is parsed successfully by Sax");
        return bankList;
    }
}
