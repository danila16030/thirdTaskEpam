package test.epam.thirdtask.parser.stax;

import entity.Bank;
import exeption.ParserException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parser.stax.StaxParser;

import java.util.List;

public class StaxParserTest {
    private static final String FILE_PATH = "test\\resource\\data\\banks.xml";

    @DataProvider(name = "data")
    public Object[][] createData() {
        return new Object[][]{{"National", "Belarus", "Danila", "Estimated", 1234, 12365, 1.0, 15, 1}};
    }

    @Test(dataProvider = "data")
    public void testParse(String name, String county, String depositor, String type, int accountId, int amountOfDeposit,
                          double profitability, int timeConstraints, int id) throws ParserException {
        Bank expectedBank = new Bank();
        expectedBank.setName(name);
        expectedBank.setCountry(county);
        expectedBank.setDepositor(depositor);
        expectedBank.setAccountId(accountId);
        expectedBank.setAmountOfDeposit(amountOfDeposit);
        expectedBank.setProfitability(profitability);
        expectedBank.setTimeConstraints(timeConstraints);
        expectedBank.setId(id);
        expectedBank.setType(type);
        StaxParser staxParser = new StaxParser();
        List<Bank> bankList = staxParser.parse(FILE_PATH);
        Bank actualBank = bankList.get(0);
        Assert.assertEquals(actualBank, expectedBank);
    }
}
