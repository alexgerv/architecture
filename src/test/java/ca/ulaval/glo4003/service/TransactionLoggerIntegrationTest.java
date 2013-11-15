package ca.ulaval.glo4003.service;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class TransactionLoggerIntegrationTest {

    private static final String TRANSACTIONS_LOG = "transactions.log";
    private TestFixture fixture;

    @Before
    public void setUp() {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
    }

    @After
    public void tearDown() {
        fixture.close();
    }

    @Test
    public void transactionGetsLoggedAfterPurchasingATicket() throws IOException {
        int initialSize = getLogSize();
        fixture.navigateToMatchDetails();
        fixture.chooseASectionInMatchDetails();
        fixture.buyATicket();
        int finalSize = getLogSize();

        assertTrue(finalSize > initialSize);
    }

    private int getLogSize() throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader(new File(TRANSACTIONS_LOG)));
        lnr.skip(Long.MAX_VALUE);
        int number = lnr.getLineNumber();
        lnr.close();
        return number;
    }
}
